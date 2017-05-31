/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.arinvoice;
 
import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.arinvoice.ArInvoiceLine;
import com.bridge.entities.arinvoice.payment.ArInvoicePayment;
import com.bridge.entities.cashmanagement.BankAccountTransaction;
import com.bridge.entities.cashmanagement.CashTransaction;
import com.bridge.entities.clients.ClPrepaymentsTransaction;
import com.bridge.entities.clients.Client;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.journal.GlJeHeader;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.InvoicePaymentType;
import com.bridge.enums.InvoiceStatus;
import com.bridge.enums.InvoiceType;
import com.bridge.services.AbstractService;
import com.bridge.services.cashmanagement.BankTxService;
import com.bridge.services.cashmanagement.CashTxService;
import com.bridge.services.clients.PrePaymentService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.modules.ReciveablesConfigurationService;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */


@Stateless
public class ArInvoiceService extends AbstractService<ArInvoice>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ; 
    
    @EJB
    private JournalBatchService journalBatchService ;
    
    @EJB    
    private CurrencyService currencyService ;
    
    @EJB
    private BankTxService bankTxService ;
    
    @EJB
    private CashTxService cashTxService ;
     
    @EJB
    private PrePaymentService prePaymentService ;            
   
    @EJB
    private ReciveablesConfigurationService reciveablesConfigurationService ;
    
    public ArInvoiceService() {
    
        super(ArInvoice.class);
    }
  
    
    @Override
    public void create(ArInvoice entity) {
    
        super.create(entity);
        
       if(entity.getInvoiceType() == InvoiceType.PREPAYMENT)
        {
            ArInvoicePayment invoicePayment = new ArInvoicePayment(entity) ;
            
            invoicePayment.setInvoicePaymentType(InvoicePaymentType.CASH);
            
            entity.setInvoiceStatus(InvoiceStatus.VALIDATED);
            
            createPayment(invoicePayment);
            
            ClPrepaymentsTransaction transaction = new ClPrepaymentsTransaction(entity) ;
            
            prePaymentService.create(transaction);
        }
    }
       
    
    public void create(List<ArInvoice> invoices)
    {
       invoices.forEach(invoice -> {
           
           invoice.getInvoiceLines().forEach(line -> {
           
               OeOrderLine soLine = line.getSalesOrderLine() ;
               
               soLine.setInvoicedQuantity(soLine.getInvoicedQuantity().add(line.getQuantityInvoiced()));
               
               entityManager.merge(soLine) ;
               
           });
           
           entityManager.persist(invoice);
           
       });
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public List<ArInvoice> findStandardByIdOrStatusOrCustomerId(Integer id, InvoiceStatus invoiceStatus,Integer supplierId)
    {
       StringBuilder builder = new StringBuilder("SELECT a FROM ArInvoice a WHERE a.invoiceType = com.bridge.enums.InvoiceType.STANDARD ") ;
       
       if(id != null)
           
           builder.append(" AND a.invoiceId = :id") ;
       
       if(invoiceStatus != null) 
           
           builder.append(" AND a.invoiceStatus = :status") ;
       
       if(supplierId != null)
           
           builder.append(" AND a.client.clientId = :clientId") ;
       
       TypedQuery<ArInvoice> typedQuery = entityManager.createQuery(builder.toString() , ArInvoice.class) ;
       
       Optional.ofNullable(id).ifPresent(i -> typedQuery.setParameter("id", i));
            
       Optional.ofNullable(invoiceStatus).ifPresent(i -> typedQuery.setParameter("status", i));
       
       Optional.ofNullable(supplierId).ifPresent(i -> typedQuery.setParameter("clientId", i));
       
       return typedQuery.getResultList() ;
    }
  
    
    public void validateInvoices(List<ArInvoice> invoices)
    {
        String delimitedIds = invoices.stream()
                .map(i -> i.getInvoiceId()+"")
                .collect(Collectors.joining(",")) ;
        
        entityManager.createQuery("UPDATE ArInvoice i SET i.invoiceStatus = :status "
                + "WHERE i.invoiceId in ("+delimitedIds+")")
                .setParameter("status", InvoiceStatus.VALIDATED)
                .executeUpdate();
        
    }    
    
  
//    public ArInvoice findWithLinesById(int id)
//    {
//       try{  
//       
//           return entityManager.createQuery("SELECT i FROM ArInvoice i "
//               + "LEFT JOIN FETCH i.invoiceLines "
//               + "WHERE i.invoiceId = :id",ArInvoice.class)
//               .setParameter("id", id)
//               .getSingleResult() ;
//       
//       }catch(Exception e){
//           
//       }
//       
//       return null ;
//    }
    
  
    public List<ArInvoiceLine> findLinesById(int id)
    {
       return entityManager.createQuery("SELECT l FROM ArInvoiceLine l "
               + "WHERE l.invoice.invoiceId = :id ")
               .setParameter("id", id)
               .getResultList();
    }
    
    
    public void cancelInvoice(ArInvoice invoice)
    {
        invoice.setInvoiceStatus(InvoiceStatus.CANCELLED);
        
        Integer headerId = invoice.getJournalHeaderId() ;
       
        if(headerId != null)
           
            journalBatchService.reverseHeader(journalBatchService.findHeaderById(headerId));
      
        update(invoice) ;
    } 
    

    public List<ArInvoice> findValidatedInvoicesToPay(Client client,Integer invoiceId)
    {
        StringBuilder builder = new StringBuilder("SELECT i FROM ArInvoice i "
                + "WHERE i.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED "
                + "AND i.paymentAmountTotal < i.invoiceAmount") ;
        
        if(client != null)
            
            builder.append(" AND i.client = :client") ;
        
        if(invoiceId != null)
            
            builder.append(" AND i.invoiceId = :id") ; 
        
        TypedQuery<ArInvoice> query = entityManager.createQuery(builder.toString(),ArInvoice.class) ;
        
        Optional.ofNullable(client).ifPresent(c -> query.setParameter("client", client));
        
        Optional.ofNullable(invoiceId).ifPresent(c -> query.setParameter("id", invoiceId));
        
        return query.getResultList() ;
        
    }   
    
    public void createPayments(List<ArInvoicePayment> payments)
    {
       payments.forEach(p -> {
       
           createPayment(p);
       });
    }   
    
    public void createPayment(ArInvoicePayment payment)
    {
        ArInvoice invoice = payment.getInvoice() ;
           
        invoice.setPaymentAmountTotal(invoice.getPaymentAmountTotal().add(payment.getAmount()));
           
        entityManager.persist(payment) ;
           
        entityManager.merge(invoice) ;       
        
        if(payment.getInvoicePaymentType() == InvoicePaymentType.CASH)
            
            cashTxService.create(new CashTransaction(payment));
        
        else if(payment.getInvoicePaymentType() == InvoicePaymentType.CHECK)
            
            bankTxService.create(new BankAccountTransaction(payment));
        
        else // PRE PAY
           
            prePaymentService.create(new ClPrepaymentsTransaction(payment));
    }
        
    
   public List<ArInvoice> findValidatedHavePaymentsByIdOrClientId(Integer id , Integer clientId)
    {
       StringBuilder builder = new StringBuilder("SELECT a FROM ArInvoice a "
               + "WHERE a.paymentAmountTotal > 0 "
               + "AND a.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED ") ;
       
       if(id != null)
           
           builder.append(" AND a.invoiceId = :id ") ;
       
       if(clientId != null)
           
           builder.append(" AND a.client.clientId = :clientId ") ;
       
      TypedQuery<ArInvoice> query = entityManager.createQuery(builder.toString(),ArInvoice.class);
               
      Optional.ofNullable(id).ifPresent( i -> query.setParameter("id", id));
      
      Optional.ofNullable(clientId).ifPresent( i -> query.setParameter("clientId", clientId));
      
      return query.getResultList() ;
              
    }    
   

   public boolean isExists(int id)
    {
       return ! entityManager.createQuery("SELECT 1 FROM ArInvoice a WHERE a.invoiceId = :id")
               .setParameter("id", id)
               .getResultList().isEmpty() ;
    }
    
   
    public List<ArInvoicePayment> findPaymentsByInvoiceId(int invoiceId)
    {
       return entityManager.createQuery("SELECT p FROM ArInvoicePayment p "
               + "WHERE p.invoice.invoiceId = :id")
               .setParameter("id", invoiceId)
               .getResultList() ;
    }   
    
    public void cancelPayment(ArInvoicePayment payment)
    {
        ArInvoice invoice = payment.getInvoice() ;
       
        invoice.setPaymentAmountTotal(invoice.getPaymentAmountTotal().subtract(payment.getAmount()));
            
        payment.setCancelled(true);
       
        Integer headerId = payment.getJournalHeaderId() ;
       
        if(headerId != null)
           
            journalBatchService.reverseHeader(journalBatchService.findHeaderById(headerId));
        
        update(invoice) ;
        
        entityManager.merge(payment) ;
    }
    
    
   private void createInvoicesJournals(List<ArInvoice> invoices,FndCurrency toCurrency)
    { 
        Map<OrganizationUnit,GlCodeCombination> accMap = new HashMap() ;

        invoices.forEach(i -> {
       
            OrganizationUnit operatingUnit = i.getOperatingUnit() ;
           
            if(! accMap.containsKey(operatingUnit))
            {
               accMap.put(operatingUnit, reciveablesConfigurationService.findCashAccountByOperating(operatingUnit)) ;
            }
            
            GlCodeCombination debit = accMap.get(operatingUnit) ;
            
            GlCodeCombination credit = i.getClientSite().getCustomerAcc().getReceivableAcc() ;
            
            FndCurrency from = i.getInvoiceCurrency() ;
            
            BigDecimal journalAmount = i.getInvoiceAmount() ;
            
            if(! from.equals(toCurrency))
            {
               journalAmount = currencyService.convert(journalAmount, from, toCurrency) ;
            }    
       
            GlJeHeader header = journalBatchService.createJournalsForDate(operatingUnit.getGlId(), 
                    "Customer Invoice No."+i.getInvoiceId(),
                     i.getInvoiceDate(),journalAmount, debit, credit);
            
            i.setAccounted(true);
            
            i.setJournalHeaderId(header.getJeHeaderId());
            
            update(i) ;
       });
       
    }

    
    private void createPaymentsJournals(List<ArInvoicePayment> payments ,FndCurrency toCurrency )
    {
        Map<OrganizationUnit,GlCodeCombination> accMap = new HashMap()   ;
        
        payments.forEach(p -> {
        
             ArInvoice invoice = p.getInvoice() ;
             
             OrganizationUnit operatingUnit = invoice.getOperatingUnit() ;
           
             if(! accMap.containsKey(operatingUnit))
             {
               accMap.put(operatingUnit, reciveablesConfigurationService.findRevenueAccountByOperating(operatingUnit) ) ;
             }
              
             GlCodeCombination debit = invoice.getClientSite().getCustomerAcc().getReceivableAcc();
             
             GlCodeCombination credit = accMap.get(operatingUnit) ;
             
             FndCurrency from = invoice.getInvoiceCurrency() ;
            
             BigDecimal journalAmount = p.getAmount() ;
            
             if(! from.equals(toCurrency))
             {
                journalAmount = currencyService.convert(journalAmount, from, toCurrency)  ;
             }    
             
             String desc = "Customer Payment For Invoice No."+invoice.getInvoiceId() ;
             
             GlJeHeader header = journalBatchService.createJournalsForDate(operatingUnit.getGlId(),desc, 
                    p.getPaymentDate(),journalAmount, debit, credit);
                              
             p.setAccounted(true);
            
             p.setJournalHeaderId(header.getJeHeaderId());
             
             entityManager.merge(p) ;
        });
    }
    
    

    public void createInvoicesJournalsForPeriod(GlPeriodStatus period)
    {
       long invoicesCountInPeriod = countNotAccountedValidatedInvoicesInPeriod(period) ;
       
       int pageCount = (int) Math.ceil((float) invoicesCountInPeriod  / 10) ;
       
       for(int page = 0 ; page < pageCount ; page ++)
       {
           List<ArInvoice> invoices = findNotAccountedValidatedInvoicesInPeriod(period, page * 10, 10) ;
           
           createInvoicesJournals(invoices,period.getPeriodSet().getLedger().getCurrency());
           
           entityManager.flush();
           
           entityManager.clear();
       }
       
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ApInvoiceService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        EventBus eventBus = EventBusFactory.getDefault().eventBus() ;
//        
//        eventBus.publish("/notification/180","Invoices Journals Created Successfully") ;
        //notificationEvent.fire("Invoices Journals Created Successfully");
    }
    
  
        
    public void createPaymentJournalsForPeriod(GlPeriodStatus period)
    {
       long paymentsCountInPeriod = countNotAccountedPaymentsInPeriod(period) ;
       
       int pageCount = (int) Math.ceil((float) paymentsCountInPeriod  / 20) ;
       
       for(int page = 0 ; page < pageCount ; page ++)
       {
           List<ArInvoicePayment> payments = findNotAccountedPaymentsInPeriod(period, page * 20, 20) ;
           
           createPaymentsJournals(payments,period.getPeriodSet().getLedger().getCurrency());
           
           entityManager.flush();
           
           entityManager.clear();
       }
       
 
    }
    
    
    
    public long countNotAccountedValidatedInvoicesInPeriod(GlPeriodStatus period)
    {
       return entityManager.createQuery("SELECT count(1) FROM ArInvoice i "
               + "WHERE i.invoiceDate BETWEEN :start AND :end "
               + "AND i.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED "
               + "AND i.accounted IS FALSE",Long.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .getSingleResult() ;        
    }
         
    
    
    public long countNotAccountedPaymentsInPeriod(GlPeriodStatus period)
    {
       return entityManager.createQuery("SELECT count(1) FROM ArInvoicePayment p "
               + "WHERE p.paymentDate BETWEEN :start AND :end "
               + "AND p.accounted IS FALSE ",Long.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .getSingleResult() ;            
    }
    
    
    
    public List<ArInvoicePayment> findNotAccountedPaymentsInPeriod(GlPeriodStatus period , int start , int max)
    {
       return entityManager.createQuery("SELECT p FROM ArInvoicePayment p "
               + "WHERE p.paymentDate BETWEEN :start AND :end "
               + "AND p.accounted IS FALSE "
               + "ORDER BY p",ArInvoicePayment.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setFirstResult(start)
               .setMaxResults(max)
               .getResultList() ;              
    }
    
    
    private List<ArInvoice> findNotAccountedValidatedInvoicesInPeriod(GlPeriodStatus period , int start , int max)
    {
       return entityManager.createQuery("SELECT i FROM ArInvoice i "
               + "WHERE i.invoiceDate BETWEEN :start AND :end "
               + "AND i.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED "
               + "AND i.accounted IS FALSE "
               + "ORDER BY i",ArInvoice.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setFirstResult(start)
               .setMaxResults(max)
               .getResultList() ;
    }
    
        
          
}
