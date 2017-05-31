/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.po;

import com.bridge.entities.assets.FaAdditions;
import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.Client;
import com.bridge.entities.hr.Location;
import com.bridge.entities.inventory.InvMaterialTransaction;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.po.PoLine;
import com.bridge.entities.po.PoLineLocation;
import com.bridge.enums.ClientType;
import com.bridge.enums.PoLineType;
import com.bridge.enums.ListLineTypeCode;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.assets.AssetService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.po.PurchaseOrderService;
import com.bridge.services.clients.ApSupplierService;
import com.bridge.services.clients.ClientService;
import com.bridge.services.hr.LocationsService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.services.physicalStructure.PhysicalLocationService;
import com.bridge.services.po.PoLineLocationService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
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
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Bridge
 */
@Named
@ConversationScoped
public class PoReceiveController implements Serializable {

    @Inject
    private Conversation conversation;

    @EJB
    private PurchaseOrderService purchaseOrderService;

    @EJB
    private MainInvService mainInvService;

    @EJB
    private SubInvService subInvService;

    @EJB
    private ClientService clientService;

    @EJB
    private PoLineLocationService poLineLocationService;
    @EJB
    private AssetService assetService;
    
    @EJB
    private PhysicalLocationService physicalLocationService;
    
    @EJB
    private OrganizationUnitService orgUnitService;

    @EJB
    private GlLedgerService ledgerService ;
    
    private PoLineType poLineType;

    private PoLineType[] lineTypes;

    private String selectedPoCode;

    private Integer currentSelectedClientId;
    
    private List<MainInventory> mainInvList;

    private List<PoLine> polines;

    private List<PoLine> selectedLines;

    private List<PoLineLocation> services;
    
    private List<FaAdditions> assetAdditions;
    
    private List<OrganizationUnit> operatingUnits;

    public List<OrganizationUnit> getOperatingUnits() {
        return operatingUnits;
    }

    
    public List<MainInventory> getMainInvList() {
        return mainInvList;
    }

    public void startConversation() {
       
        conversation.begin();
    }

    public List<MainInventory> completeMainInvByName(String text) {
        
        return mainInvService.findByName(text);

    }

    public List<SecondaryInventory> completeSubInvForItem(String text) {
        
        PoLine line = JSFUtils.evaluateValueExpression("#{line}", PoLine.class) ;
        
        MainInventory mainInventory = line.getTagertMainInv() ;
        
        if(line.getTagertMainInv() == null)
            
            return null ;
        
        return subInvService.findByNameForMain(text,mainInventory);

    }
    
    public List<SecondaryInventory> completeSubInvForAsset(String text) {
        
        FaAdditions line = JSFUtils.evaluateValueExpression("#{asset}", FaAdditions.class) ;
        
        Integer id = line.getMainInventoryId() ;
        
        System.out.println("com.bridge.controllers.po.PoReceiveController.completeSubInvForAsset()"+id);
        
        if(id == null)
            
            return null ;
        
        return subInvService.findByNameAndMainId(text,id);

    }
    

    public List<Client> completeClientByName(String text) {
       
        return clientService.findByName(ClientType.SUPPLIER,text);
    }

    public void resetSubInv() {
        
        PoLine line = JSFUtils.evaluateValueExpression("#{line}", PoLine.class) ;
        
        line.setTargetSecInv(null);
    }

    public void filter() {

        polines = purchaseOrderService.findNotClosedLinesOfApprovedPos(currentSelectedClientId, selectedPoCode, poLineType);
    }

    public String createReciepts() {
        
        switch (poLineType) {
         
            case ASSET:
                
                assetAdditions = selectedLines.stream().map(FaAdditions::new)
                        .collect(Collectors.toList());
                
            return "receiveAsset";
               
                
            case SERVICE:
                
            loadOperatingUnits();
            
            services = selectedLines.stream().map(PoLineLocation::new)
                    .collect(Collectors.toList());

            return "receiveService";
            
            default:
                
             return "receiveItem";

        }

    }

 
    public void loadMainInv(){
        
        mainInvList = mainInvService.findAll();
    }
    
    public void loadOperatingUnits(){
    
        operatingUnits = orgUnitService.findByOrgType(OrganizationUnitType.OPERATING_UNIT);
    }
    
    
    public void validateTxDateIsInOpenPeriod(FacesContext facesContext , UIComponent component , Object value)
    {
        PoLine line = JSFUtils.evaluateValueExpression("#{line}", PoLine.class) ;
        
        SecondaryInventory targetSecondaryInventory = line.getTargetSecInv() ;
        
        if(value == null || targetSecondaryInventory == null)
            
               return;
        
        int ledgerId = targetSecondaryInventory.getGlId() ;
        
        Date txDate = (Date) value ;
        
        if(! ledgerService.isInOpenPeriod(ledgerId, txDate))
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Not In an Open Period", "Not In an Open Period")) ;
    }
    
    
    public PoLineType getPoLineType() {
        return poLineType;
    }

    public void setPoLineType(PoLineType poLineType) {
        this.poLineType = poLineType;
    }

    public PoLineType[] getLineTypes() {
        return PoLineType.values();
    }

    public List<PhysicalLocation> completeLocation(String text) {

        PoLineLocation line =(PoLineLocation)JSFUtils.evaluateValueExpression("#{line}", PoLineLocation.class);
      
        return physicalLocationService.findByOrganizationAndName(line.getOrganizationId(), text);

    }
    
    
    public List<PhysicalLocation> completeAssetLocation(String text) {

        FaAdditions line =(FaAdditions)JSFUtils.evaluateValueExpression("#{line}", FaAdditions.class);
      
        return physicalLocationService.findByOrganizationAndName(line.getOperatingUnit().getOrgUnitId(), text);

    }

    public String save(String type) {
        
        switch (type) {
            
            case "item":
                
                purchaseOrderService.recieveLines(selectedLines);
            
                publishRecieveLines();
            
                break;
                
            case "asset":
                
                purchaseOrderService.receiveAssets(assetAdditions);
                
                break;
                
            case "service":
                
                purchaseOrderService.receiveService(services);
                
                break;
     
        }
        

        conversation.end();

        return "receivePO?faces-redirect=true";
    }

    
    private void publishRecieveLines()
    {
       EventBus eventBus = EventBusFactory.getDefault().eventBus() ;
       
       selectedLines.stream().map(line -> line.getTargetSecInv().getSecondaryInventoryId()).distinct()
                    .forEach(id -> {
                        
                        eventBus.publish("/subInvs", id) ;
                    });
    }
    
    
    public List<PoLine> getPolines() {
        return polines;
    }

    public List<PoLine> getSelectedLines() {
        return selectedLines;
    }

    public void setSelectedLines(List<PoLine> selectedLines) {
        this.selectedLines = selectedLines;
    }

    public Integer getCurrentSelectedClientId() {
        return currentSelectedClientId;
    }

    public void setCurrentSelectedClientId(Integer currentSelectedClientId) {
        this.currentSelectedClientId = currentSelectedClientId;
    }

    public String getSelectedPoCode() {
        return selectedPoCode;
    }

    public void setSelectedPoCode(String selectedPoCode) {
        this.selectedPoCode = selectedPoCode;
    }
    
    public List<PoLineLocation> getServices() {
        return services;
    }

    public void setServices(List<PoLineLocation> services) {
        this.services = services;
    }

    public List<FaAdditions> getAssetAdditions() {
        return assetAdditions;
    }

    public void setAssetAdditions(List<FaAdditions> assetAdditions) {
        this.assetAdditions = assetAdditions;
    }
    
    

}
