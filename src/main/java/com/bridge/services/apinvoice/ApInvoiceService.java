/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.apinvoice;

import com.bridge.controllers.CurrentUser;
import com.bridge.entities.clients.Client;
import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.payment.ApInvoicePayment;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.journal.GlJeHeader;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.po.PoLine;
import com.bridge.enums.InvoiceStatus;
import com.bridge.services.AbstractService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.modules.PayableModuleConfigurationService;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.bridge.controllers.qualifiers.UserPushNotification;
import com.bridge.entities.apinvoice.ApInvoiceLine;
import com.bridge.entities.cashmanagement.BankAccountTransaction;
import com.bridge.entities.cashmanagement.CashTransaction;
import com.bridge.entities.clients.ClPrepaymentsTransaction;
import com.bridge.entities.po.PoHeader;
import com.bridge.enums.InvoicePaymentType;
import com.bridge.enums.InvoiceType;
import com.bridge.enums.PoStatus;
import com.bridge.services.cashmanagement.BankTxService;
import com.bridge.services.cashmanagement.CashTxService;
import com.bridge.services.clients.PrePaymentService;
import com.bridge.services.hr.UserNotificationService;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Bridge
 */

@Stateless
public class ApInvoiceService extends AbstractService<ApInvoice>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    @EJB
    private JournalBatchService journalBatchService ;
    
    @EJB
    private PayableModuleConfigurationService payableModuleConfigurationService ;
    
    @EJB
    private CurrencyService currencyService ;
   
    @EJB
    private BankTxService bankTxService ;
    
    @EJB
    private CashTxService cashTxService ;
    
    @EJB
    private PrePaymentService prePaymentService ;
    
    @Inject
    @UserPushNotification
    private Event<String> notificationEvent ;
    
    @EJB
    private UserNotificationService notificationService ;
    
    @Inject
    private CurrentUser currentUser ;
       
    public ApInvoiceService() {
    
        super(ApInvoice.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }

    @Override
    public void create(ApInvoice entity) {
    
        super.create(entity);
        
        if(entity.getInvoiceType() == InvoiceType.PREPAYMENT)
        {
            ApInvoicePayment invoicePayment = new ApInvoicePayment(entity) ;
            
            invoicePayment.setInvoicePaymentType(InvoicePaymentType.CASH);
            
            entity.setInvoiceStatus(InvoiceStatus.VALIDATED);
            
            createPayment(invoicePayment);
            
            ClPrepaymentsTransaction transaction = new ClPrepaymentsTransaction(entity) ;
            
            prePaymentService.create(transaction);
        }
    }
    
    
    public ApInvoice findWithLinesById(int id)
    {
       try{  
       
           return entityManager.createQuery("SELECT i FROM ApInvoice i "
               + "LEFT JOIN FETCH i.lines "
               + "WHERE i.invoiceId = :id",ApInvoice.class)
               .setParameter("id", id)
               .getSingleResult() ;
       
       }catch(Exception e){
           
       }
       
       return null ;
    }
    
    
    public List<ApInvoice> findValidatedHavePaymentsByIdOrClientId(Integer id , Integer clientId)
    {
       StringBuilder builder = new StringBuilder("SELECT a FROM ApInvoice a "
               + "WHERE a.paymentAmountTotal > 0 "
               + "AND a.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED ") ;
       
       if(id != null)
           
           builder.append(" AND a.invoiceId = :id ") ;
       
       if(clientId != null)
           
           builder.append(" AND a.client.clientId = :clientId ") ;
       
      TypedQuery<ApInvoice> query = entityManager.createQuery(builder.toString(),ApInvoice.class);
               
      Optional.ofNullable(id).ifPresent( i -> query.setParameter("id", id));
      
      Optional.ofNullable(clientId).ifPresent( i -> query.setParameter("clientId", clientId));
      
      return query.getResultList() ;
              
    }
    
    
    public List<ApInvoice> findNotAccountedAndValidatedById(Integer id)
    {
         StringBuilder builder = new StringBuilder("SELECT a FROM ApInvoice a "
               + "WHERE a.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED "
               + "AND a.accounted IS FALSE ") ;
       
        if(id != null)
           
            builder.append(" AND a.invoiceId = :id ") ;
       
        TypedQuery<ApInvoice> query = entityManager.createQuery(builder.toString(),ApInvoice.class);
               
        Optional.ofNullable(id).ifPresent( i -> query.setParameter("id", id));

        return query.getResultList() ;
                    
    }
   
    
    public boolean isExists(int id)
    {
       return ! entityManager.createQuery("SELECT 1 FROM ApInvoice a WHERE a.invoiceId = :id")
               .setParameter("id", id)
               .getResultList().isEmpty() ;
    }
    
    
    public void create(List<ApInvoice> invoices)
    {
       invoices.forEach(invoice -> {
           
           invoice.getLines().forEach(line -> {
           
               PoLine poLine = line.getPoLine() ;
               
               poLine.setInvoicedQuantity(poLine.getInvoicedQuantity().add(line.getQuantityInvoiced()));
               
               entityManager.merge(poLine) ;
               
               PoHeader header = poLine.getPoHeader() ;
               
               if(header.getPoStatus() != PoStatus.PROCESSING)
               {
                  header.setPoStatus(PoStatus.PROCESSING);
             
                  entityManager.merge(header) ;
               } 
               
           });
           
           entityManager.persist(invoice);
           
       });
    }
    
    public void createLines(ApInvoice invoice , List<ApInvoiceLine> invoiceLines)
    {
       update(invoice) ;
        
       invoiceLines.stream()
               .forEach(l -> entityManager.persist(l));
    }
    
    public void createPayments(List<ApInvoicePayment> payments)
    {
       payments.forEach(p -> {
       
           createPayment(p);
       });
    }
    
    
    public void createPayment(ApInvoicePayment payment)
    {
        ApInvoice invoice = payment.getInvoice() ;
           
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
    
    
    public void cancelInvoice(ApInvoice invoice)
    {
       invoice.setInvoiceStatus(InvoiceStatus.CANCELLED);

        Integer headerId = invoice.getJournalHeaderId() ;
       
        if(headerId != null)
           
            journalBatchService.reverseHeader(journalBatchService.findHeaderById(headerId));

        update(invoice) ;

    }
    
    
    public void cancelPayment(ApInvoicePayment payment)
    {
        ApInvoice invoice = payment.getInvoice() ;
       
        invoice.setPaymentAmountTotal(invoice.getPaymentAmountTotal().subtract(payment.getAmount()));
            
        payment.setCancelled(true);
      
        Integer headerId = payment.getJournalHeaderId() ;
       
        if(headerId != null)
           
            journalBatchService.reverseHeader(journalBatchService.findHeaderById(headerId));
        
        entityManager.merge(invoice);
        
        entityManager.merge(payment) ;
    }
   
    
    private void createInvoicesJournals(List<ApInvoice> invoices,FndCurrency toCurrency)
    { 
        Map<OrganizationUnit,GlCodeCombination> accMap = new HashMap() ;

        invoices.forEach(i -> {
       
            OrganizationUnit operatingUnit = i.getOperatingUnit() ;
           
            if(! accMap.containsKey(operatingUnit))
            {
               accMap.put(operatingUnit, payableModuleConfigurationService.findChargingAccountByOperating(operatingUnit)) ;
            }
            
            GlCodeCombination debit = accMap.get(operatingUnit) ;
            
            GlCodeCombination credit = i.getClientSite().getSupplierAcc().getLiabilityAccountId() ;
            
            FndCurrency from = i.getInvoiceCurrency() ;
            
            BigDecimal journalAmount = i.getInvoiceAmount() ;
            
            if(! from.equals(toCurrency))
            {
               journalAmount = currencyService.convert(journalAmount, from, toCurrency) ;
            }    
       
            GlJeHeader header = journalBatchService.createJournalsForDate(operatingUnit.getGlId(), 
                    "Supplier Invoice No."+i.getInvoiceId(),
                     i.getInvoiceDate(),journalAmount, debit, credit);
            
            i.setAccounted(true);
            
            i.setJournalHeaderId(header.getJeHeaderId());
            
            update(i) ;
       });
       
    }

    
    private void createPaymentsJournals(List<ApInvoicePayment> payments ,FndCurrency toCurrency )
    {
        Map<OrganizationUnit,GlCodeCombination> accMap = new HashMap()   ;
        
        payments.forEach(p -> {
        
             ApInvoice invoice = p.getInvoice() ;
             
             OrganizationUnit operatingUnit = invoice.getOperatingUnit() ;
           
             if(! accMap.containsKey(operatingUnit))
             {
               accMap.put(operatingUnit, payableModuleConfigurationService.findCashClearingAccountByOperating(operatingUnit)) ;
             }
              
             GlCodeCombination debit = invoice.getClientSite().getSupplierAcc().getLiabilityAccountId() ;
             
             GlCodeCombination credit = accMap.get(operatingUnit) ;
             
             FndCurrency from = invoice.getInvoiceCurrency() ;
            
             BigDecimal journalAmount = p.getAmount() ;
            
             if(! from.equals(toCurrency))
             {
                journalAmount = currencyService.convert(journalAmount, from, toCurrency)  ;
             }    
             
             String desc = "Supplier Payment For Invoice No."+invoice.getInvoiceId() ;
             
             GlJeHeader header = journalBatchService.createJournalsForDate(operatingUnit.getGlId(),desc, 
                    p.getPaymentDate(),journalAmount, debit, credit);
                              
             p.setAccounted(true);
            
             p.setJournalHeaderId(header.getJeHeaderId());
             
             entityManager.merge(p) ;
        });
    }
    
    
    @Asynchronous
    public void createInvoicesJournalsForPeriod(GlPeriodStatus period)
    {
       long invoicesCountInPeriod = countNotAccountedValidatedInvoicesInPeriod(period) ;
       
       int pageCount = (int) Math.ceil((float) invoicesCountInPeriod  / 10) ;
       
       for(int page = 0 ; page < pageCount ; page ++)
       {
           List<ApInvoice> invoices = findNotAccountedValidatedInvoicesInPeriod(period, page * 10, 10) ;
           
           createInvoicesJournals(invoices,period.getPeriodSet().getLedger().getCurrency());
           
           entityManager.flush();
           
           entityManager.clear();
       }
       
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ApInvoiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EventBus eventBus = EventBusFactory.getDefault().eventBus() ;
        
        eventBus.publish("/notification/180","Invoices Journals Created Successfully") ;
        //notificationEvent.fire("Invoices Journals Created Successfully");
    }
    
  
        
    @Asynchronous
    public void createPaymentJournalsForPeriod(GlPeriodStatus period)
    {
       long paymentsCountInPeriod = countNotAccountedPaymentsInPeriod(period) ;
       
       int pageCount = (int) Math.ceil((float) paymentsCountInPeriod  / 20) ;
       
       for(int page = 0 ; page < pageCount ; page ++)
       {
           List<ApInvoicePayment> payments = findNotAccountedPaymentsInPeriod(period, page * 20,20 ) ;
           
           createPaymentsJournals(payments,period.getPeriodSet().getLedger().getCurrency());
           
           entityManager.flush();
           
           entityManager.clear();
       }
       
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ApInvoiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public long countNotAccountedValidatedInvoicesInPeriod(GlPeriodStatus period)
    {
       return entityManager.createQuery("SELECT count(1) FROM ApInvoice i "
               + "WHERE i.invoiceDate BETWEEN :start AND :end "
               + "AND i.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED "
               + "AND i.accounted IS FALSE",Long.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .getSingleResult() ;        
    }
         
    
    
    public long countNotAccountedPaymentsInPeriod(GlPeriodStatus period)
    {
       return entityManager.createQuery("SELECT count(1) FROM ApInvoicePayment p "
               + "WHERE p.paymentDate BETWEEN :start AND :end "
               + "AND p.accounted IS FALSE ",Long.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .getSingleResult() ;            
    }
    
    
    
    public List<ApInvoicePayment> findNotAccountedPaymentsInPeriod(GlPeriodStatus period , int start , int max)
    {
       return entityManager.createQuery("SELECT p FROM ApInvoicePayment p "
               + "WHERE p.paymentDate BETWEEN :start AND :end "
               + "AND p.accounted IS FALSE "
               + "ORDER BY p",ApInvoicePayment.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setFirstResult(start)
               .setMaxResults(max)
               .getResultList() ;              
    }
    
    
    private List<ApInvoice> findNotAccountedValidatedInvoicesInPeriod(GlPeriodStatus period , int start , int max)
    {
       return entityManager.createQuery("SELECT i FROM ApInvoice i "
               + "WHERE i.invoiceDate BETWEEN :start AND :end "
               + "AND i.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED "
               + "AND i.accounted IS FALSE "
               + "ORDER BY i",ApInvoice.class)
               .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
               .setFirstResult(start)
               .setMaxResults(max)
               .getResultList() ;
    }
    
    
    public List<ApInvoicePayment> findPaymentsByInvoiceId(int invoiceId)
    {
       return entityManager.createQuery("SELECT p FROM ApInvoicePayment p "
               + "WHERE p.invoice.invoiceId = :id")
               .setParameter("id", invoiceId)
               .getResultList() ;
    }
    
    
    public List<ApInvoice> findStandardByIdOrStatusOrSupplierId(Integer id, InvoiceStatus invoiceStatus,Integer supplierId)
    {
        
       StringBuilder builder = new StringBuilder("SELECT a FROM ApInvoice a WHERE a.invoiceType = com.bridge.enums.InvoiceType.STANDARD ") ;
       
       if(id != null)
           
           builder.append(" AND a.invoiceId = :id") ;
       
       if(invoiceStatus != null) 
           
           builder.append(" AND a.invoiceStatus = :status") ;
       
       if(supplierId != null)
           
           builder.append(" AND a.client.clientId = :clientId") ;
       
       TypedQuery<ApInvoice> typedQuery = entityManager.createQuery(builder.toString() , ApInvoice.class) ;
       
       Optional.ofNullable(id).ifPresent(i -> typedQuery.setParameter("id", i));
            
       Optional.ofNullable(invoiceStatus).ifPresent(i -> typedQuery.setParameter("status", i));
       
       Optional.ofNullable(supplierId).ifPresent(i -> typedQuery.setParameter("clientId", i));
       
       return typedQuery.getResultList() ;
    }

    
    public void validateInvoices(List<ApInvoice> invoices)
    {
        String delimitedIds = invoices.stream()
                .map(i -> i.getInvoiceId()+"")
                .collect(Collectors.joining(",")) ;
        
        entityManager.createQuery("UPDATE ApInvoice i SET i.invoiceStatus = :status "
                + "WHERE i.invoiceId in ("+delimitedIds+")")
                .setParameter("status", InvoiceStatus.VALIDATED)
                .executeUpdate();
        
    }
    
    
    public List<ApInvoice> findValidatedInvoicesToPay(Client client,Integer invoiceId)
    {
        StringBuilder builder = new StringBuilder("SELECT i FROM ApInvoice i "
                + "WHERE i.invoiceStatus = com.bridge.enums.InvoiceStatus.VALIDATED "
                + "AND i.paymentAmountTotal < i.invoiceAmount") ;
        
        if(client != null)
            
            builder.append(" AND i.client = :client") ;
        
        if(invoiceId != null)
            
            builder.append(" AND i.invoiceId = :id") ; 
        
        TypedQuery<ApInvoice> query = entityManager.createQuery(builder.toString(),ApInvoice.class) ;
        
        Optional.ofNullable(client).ifPresent(c -> query.setParameter("client", client));
        
        Optional.ofNullable(invoiceId).ifPresent(c -> query.setParameter("id", invoiceId));
        
        return query.getResultList() ;
        
    }
    

}

