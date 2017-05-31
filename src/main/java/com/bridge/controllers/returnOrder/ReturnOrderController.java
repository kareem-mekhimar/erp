/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.returnOrder;

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
import com.bridge.services.clients.ClientService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.moveorder.MoveOrderService;
import com.bridge.services.salesorder.SalesOrderService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AIA
 */
@Named
@ConversationScoped
public class ReturnOrderController implements Serializable {

    @EJB
    private SalesOrderService salesOrderService;
    @EJB
    private ClientService clientService;

    @EJB
    private MainInvService mainInvService;

    @EJB
    private SubInvService subInvService;

    @EJB
    private MoveOrderService moveOrderService;

    @Inject
    private Conversation conversation;

    private Integer no;

    private Integer customerId;

    private OeOrderHeader selectedOrder;

    private List<OeOrderLine> orderLines;

    private List<OeOrderHeader> orders;

    private Integer newMoveOrderId;

    private MainInventory mainInventory;

    private SecondaryInventory secondaryInventory;
    
    private GlCodeCombination account;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public OeOrderHeader getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(OeOrderHeader selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public List<OeOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OeOrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OeOrderHeader> getOrders() {
        return orders;
    }

    public void setOrders(List<OeOrderHeader> orders) {
        this.orders = orders;
    }

    public void filter() {
        orders = salesOrderService.findByShipment();
    }

    public Integer getNewMoveOrderId() {
        return newMoveOrderId;
    }

    public void setNewMoveOrderId(Integer newMoveOrderId) {
        this.newMoveOrderId = newMoveOrderId;
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

    public MainInventory getMainInventory() {
        return mainInventory;
    }

    public void setMainInventory(MainInventory mainInventory) {
        this.mainInventory = mainInventory;
    }

    public SecondaryInventory getSecondaryInventory() {
        return secondaryInventory;
    }

    public void setSecondaryInventory(SecondaryInventory secondaryInventory) {
        this.secondaryInventory = secondaryInventory;
    }

    public GlCodeCombination getAccount() {
        return account;
    }

    public void setAccount(GlCodeCombination account) {
        this.account = account;
    }
    
    

    public void startConversation() {
        conversation.begin();
    }

    public String endConversation() {

        conversation.end();

        return "returnView?faces-redirect=true";
    }

    public List<Client> completeCustomersByName(String text) {
        return clientService.findByName(ClientType.CUSTOMER, text);
    }

    public void validateQuantity(FacesContext context, UIComponent component, Object value) {

        OeOrderLine line = (OeOrderLine) JSFUtils.evaluateValueExpression("#{line}", OeOrderLine.class);

        BigDecimal quantity = (BigDecimal) value;

        if (quantity == null) {
            return;
        }

        if (quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Value Can't Be Zero", "Value Can't Be Zero"));
        } else if (quantity.compareTo(line.getDeliveredQuantity().subtract(line.getReturnedQuantity())) > 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Shipped qty less than this value", "Shipped qty less than this value"));
        }

    }

    public void loadLines() {

        selectedOrder = salesOrderService.findWithLines(selectedOrder.getHeaderId());

        orderLines = selectedOrder.getLines().stream().filter(l -> 
                l.getDeliveredQuantity().subtract(l.getReturnedQuantity()).compareTo(BigDecimal.ZERO)>0)
                .collect(Collectors.toList());
        
        MtlTxnRequestHeader head=moveOrderService.findBySalesOrder(selectedOrder).stream().findAny().get();
        
        account=head.getToAccountId();

    }

    public String save() {

        List<MtlTxnRequestLine> requestLines = selectedOrder.getLines().stream()
                .filter(l -> l.getQtyToPick() != null)
                .map(MtlTxnRequestLine::new)
                .collect(Collectors.toCollection(ArrayList::new));
        
        

        if (!requestLines.isEmpty()) {
            
            MtlTxnRequestHeader header = new MtlTxnRequestHeader();

            header.setDescription("MoveOrder For ReturnOrder(" + selectedOrder.getHeaderId() + ")");

            header.setTransactionType(MoveOrderTransactionType.RETURN_ORDER);

            header.setMtlTxnRequestLines(requestLines);

            header.setOrganization(selectedOrder.getOrg());
            
            header.setFromAccountId(account);
            
            header.setToMainInventoryId(mainInventory);
            
            header.setToSubinventoryId(secondaryInventory);
            

            requestLines.forEach(l -> {l.setHeader(header);});

            header.setSalesOrder(selectedOrder);

            moveOrderService.create(header);

            newMoveOrderId = header.getHeaderId();

            RequestContext.getCurrentInstance().execute("modal.show()");
            
            return "";
            
        } else {
            
            return endConversation();
            
        }

    }
}
