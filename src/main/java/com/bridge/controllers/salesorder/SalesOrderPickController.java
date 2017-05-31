/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.salesorder;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.clients.Client;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.entities.moveorder.MtlTxnRequestLine;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.ClientType;
import com.bridge.enums.MoveOrderTransactionType;
import com.bridge.enums.MoveOrderType;
import com.bridge.enums.SalesLineStatus;
import com.bridge.enums.SalesOrderStatus;
import com.bridge.services.clients.ClientService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.moveorder.MoveOrderService;
import com.bridge.services.salesorder.SalesOrderService;
import com.bridge.services.setup.ConfigurationService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kareem
 */
@Named
@ConversationScoped
public class SalesOrderPickController implements Serializable {

    @EJB
    private SalesOrderService salesOrderService;

    @EJB
    private TransactionService transactionService;

    @EJB
    private MainInvService mainInvService;

    @EJB
    private SubInvService subInvService;

    @EJB
    private COAAccountService accountService;

    @EJB
    private ClientService clientService;

    @EJB
    private MoveOrderService moveOrderService;

    @EJB
    private ConfigurationService configService;

    @Inject
    private Conversation conversation;
    
    @Inject
    private NotificationController notificationController ;

    private MainInventory mainInventory;

    private SecondaryInventory secondaryInventory;

    private SecondaryInventory shippedInventory;

    private GlCodeCombination account;

    private OeOrderHeader selectedOrder;

    private Integer no;

    private Integer customerId;

    private List<OeOrderHeader> orders;

    private Integer newMoveOrderId;

    private Integer linesCount;

    private Integer notClosedLines;

    private boolean transactFlag = true;

    public boolean getTransactFlag() {
        return transactFlag;
    }

    public void setTransactFlag(boolean transactFlag) {
        this.transactFlag = transactFlag;
    }

    public List<Client> completeCustomersByName(String text) {
        return clientService.findByName(ClientType.CUSTOMER, text);
    }

    public void startConversation() {
        conversation.begin();
    }

    public void loadWithLines() {
        selectedOrder = salesOrderService.findWithLines(selectedOrder.getHeaderId());

    }

    public List<MainInventory> completeMainInvByName(String text) {
        return mainInvService.findByName(text);
    }

    public List<SecondaryInventory> completeSecondaryByName(String text) {
        if (mainInventory == null) {
            return null;
        }

        return subInvService.findByNameForMain(text, mainInventory);
    }

    public void filter() {
        orders = salesOrderService.findByIdOrCustomerForPick(no, customerId);
    }

    public void resetSubInvSource() {
        secondaryInventory = null;

        selectedOrder.getLines().forEach(l -> {
            l.setQtyToPick(null);
        });
    }

    public void onSourceSubInvItemSelect() {
        selectedOrder.getLines().forEach(l -> {

            l.setQtyToPick(null);

            l.setActualQtyInInventory(transactionService.findActualQuantityInInventory(l.getInventoryItem(),
                    secondaryInventory));
        });
    }

    public void validateShippingInventory(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        MainInventory mainInv = (MainInventory) value;

        if (mainInv.getShippingSubInventory() == null) {

            throw new ValidatorException(new FacesMessage("Main Inventory Not Have Shipping Inventory !"));
        }
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {
        
        UIInput quantityInput = (UIInput) component.findComponent("txQuantity");

        BigDecimal quantity = (BigDecimal) quantityInput.getValue();

        if (quantity != null && quantity.compareTo(BigDecimal.ZERO) > 0) {
            Date val = (Date) value;

            if (val == null) {
                throw new ValidatorException(new FacesMessage("Date Can't be Null"));
            }
            if(val.compareTo(new Date())>0){
                throw new ValidatorException(new FacesMessage("Date Can't be after today"));
            }
        }
    }

    public void validateQtyGtZeroOrNotAvailableInInv(FacesContext context , UIComponent component , Object value)
    {
        
        
        OeOrderLine line  =(OeOrderLine) JSFUtils.evaluateValueExpression("#{line}", OeOrderLine.class) ;
        
       BigDecimal actualQty= line.getActualQtyInInventory();
                
       BigDecimal quantity = (BigDecimal) value;
       
       if(quantity == null)
           
           return ;
       
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero", "Value Can't Be Zero")) ;
       }   

    
       if(transactFlag && quantity.compareTo(actualQty) > 0){
           
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Exceed Inventory Quantity", "Value Can't Exceed Inventory Value")) ;
           
       }

    }

    public void onChangeCheckQuantityCover(OeOrderLine line) {
               
        BigDecimal qtyNeeded = line.getQtyToPick();

        UIComponent comp = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());

        if (qtyNeeded == null) {
            return;
        }

        BigDecimal actualQtyInInv = line.getActualQtyInInventory();

        if (qtyNeeded.compareTo(actualQtyInInv) > 0) {
            String clientId = comp.getClientId();

            RequestContext.getCurrentInstance().addCallbackParam("warn", true);

            FacesContext.getCurrentInstance().addMessage(clientId,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Current Quantity Doesn't Cover This Quantity", null));
        }

    }
    
    public void onDateChange(OeOrderLine line)
    {
        if(secondaryInventory==null){
            return;
        }
        LocalDate newTxDate = line.getRequestDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
        
        BigDecimal qtyInThisDate = transactionService.findNotReservedQuantityUntilDate(line.getInventoryItem().getInventoryItemId(),
                secondaryInventory.getSecondaryInventoryId(), newTxDate) ;

        if(qtyInThisDate == null) // No Records To SUM
            
            qtyInThisDate = BigDecimal.ZERO ;
        
        line.setActualQtyInInventory(qtyInThisDate);
    }
    
        public void onMessageRecieved()
    { 
        FacesContext facesContext = FacesContext.getCurrentInstance() ;
        
        ExternalContext context = facesContext.getExternalContext() ;
       
        String msg = context.getRequestParameterMap().get("data") ;
        
        int sourceId = 0 , targetId = 0 ;
        
        if(msg.contains(","))
        {    
           String [] ids = msg.split(",") ;
        
           sourceId = Integer.parseInt(ids[0]) ;
        
           targetId = Integer.parseInt(ids[1]) ;
        }
        else  // RECIEVE ONLY
        {
           targetId = Integer.parseInt(msg) ;
        }
        
        if(selectedOrder == null) // TO BE CHANGED
            
            return; 
        
        List<OeOrderLine> lines = selectedOrder.getLines() ;
        
        if(secondaryInventory != null && ! lines.isEmpty() && 
                (secondaryInventory.getSecondaryInventoryId() == sourceId || secondaryInventory.getSecondaryInventoryId() == targetId))
        {
             lines.stream().forEach(q -> {
             
                 Date txDate = q.getRequestDate() ;
                 
                 if(txDate == null)
                     
                     return ;
                 
                 LocalDate localDate = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
                 
                 BigDecimal qty = transactionService.findNotReservedQuantityUntilDate(q.getInventoryItem().getInventoryItemId(),
                        secondaryInventory.getSecondaryInventoryId(),localDate) ;
                 
                 if(qty == null)
                     
                     qty = BigDecimal.ZERO ;
                 
                 q.setActualQtyInInventory(qty);
             });
             
             notificationController.showNotification("Some Quantities has been updated by another user...", msg);
             
        }
    }

    public String save() {

        linesCount = 0;
        notClosedLines = 0;

        shippedInventory = subInvService.findById(mainInventory.getShippingSubInventory());
        account = selectedOrder.getInvoiceToSite().getCustomerAcc().getSalesAccountId();

        selectedOrder.getLines().forEach(l -> {

            if (l.getQtyToPick() != null) {

                l.setPickedQuantity(l.getPickedQuantity().add(l.getQtyToPick()));

                if (l.getPickedQuantity().compareTo(l.getOrderedQuantity()) >= 0) {
                    l.setStatus(SalesLineStatus.CLOSED);
                } else {
                    l.setStatus(SalesLineStatus.PICKED);
                }
                linesCount += 1;
            }
            if (l.getStatus() != SalesLineStatus.CLOSED) {
                notClosedLines += 1;
            }
        });

        if (linesCount == 0) {
            return onDialogOk();
        }
        if (notClosedLines == 0) {
            selectedOrder.setFlowStatusCode(SalesOrderStatus.CLOSED);
        } else {
            selectedOrder.setFlowStatusCode(SalesOrderStatus.PICKED);
        }

        List<MtlTxnRequestLine> requestLines = selectedOrder.getLines().stream()
                .filter(l -> l.getQtyToPick() != null)
                .map(MtlTxnRequestLine::new)
                .collect(Collectors.toCollection(ArrayList::new));

        if (!requestLines.isEmpty()) {
            MtlTxnRequestHeader header = new MtlTxnRequestHeader();

            header.setDescription("MoveOrder For SalesOrder(" + selectedOrder.getHeaderId() + ")");

            header.setTransactionType(MoveOrderTransactionType.SALES_ORDER);

            header.setMtlTxnRequestLines(requestLines);

            header.setOrganization(selectedOrder.getOrg());

            header.setToAccountId(account);

            header.setToMainInventoryId(mainInventory);

            header.setFromMainInventoryId(mainInventory);

            header.setToSubinventoryId(shippedInventory);

            header.setFromSubinventoryId(secondaryInventory);

            if (transactFlag) {
                header.setMoveOrderType(MoveOrderType.PICKWAVE);
            }

            requestLines.forEach(l -> {
                l.setHeader(header);
            });

            header.setSalesOrder(selectedOrder);

            moveOrderService.create(header);
            
//             if (transactFlag) {
//                 
//             moveOrderService.transferMoveOrderToShipping(requestLines,TxnSourceType.SALESORDER,
//                            "Move Order FOR Sales Order No "+selectedOrder.getHeaderId());
//             }
            

            newMoveOrderId = header.getHeaderId();

            RequestContext.getCurrentInstance().execute("modal.show()");

            return "";

        } else {

            return onDialogOk();

        }

    }

    public String onDialogOk() {
        conversation.end();

        return "pickView?faces-redirect=true";
    }

    public GlCodeCombination getAccount() {
        return account;
    }

    public MainInventory getMainInventory() {
        return mainInventory;
    }

    public SecondaryInventory getSecondaryInventory() {
        return secondaryInventory;
    }

    public void setAccount(GlCodeCombination account) {
        this.account = account;
    }

    public void setMainInventory(MainInventory mainInventory) {
        this.mainInventory = mainInventory;
    }

    public void setSecondaryInventory(SecondaryInventory secondaryInventory) {
        this.secondaryInventory = secondaryInventory;
    }

    public void setSelectedOrder(OeOrderHeader selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public OeOrderHeader getSelectedOrder() {
        return selectedOrder;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return no;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OeOrderHeader> getOrders() {
        return orders;
    }

    public Integer getNewMoveOrderId() {
        return newMoveOrderId;
    }

}
