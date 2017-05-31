/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.moveorder;

import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.enums.MoveHeaderStatus;
import com.bridge.enums.MoveOrderTransactionType;
import com.bridge.enums.MoveOrderType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "MTL_TXN_REQUEST_HEADERS")
@NamedQueries({
    @NamedQuery(name = "MtlTxnRequestHeader.findAll", query = "SELECT m FROM MtlTxnRequestHeader m")})
public class MtlTxnRequestHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "gen")
    @TableGenerator(name = "gen",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "MoveHeader",valueColumnName = "CURRENT_VALUE")
    @NotNull
    @Column(name = "HEADER_ID")
    private Integer headerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REQUEST_NUMBER")
    private String requestNumber = "1";
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private MoveOrderTransactionType transactionType ;
   
    @Enumerated(EnumType.STRING)
    @Column(name = "MOVE_ORDER_TYPE")
    private MoveOrderType moveOrderType;
    
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationUnit organization;
    
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "DATE_REQUIRED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRequired;
   
 
    @Enumerated(EnumType.STRING)
    @Column(name = "HEADER_STATUS")
    private MoveHeaderStatus headerStatus = MoveHeaderStatus.INCOMPELETE ;
    
    @Column(name = "STATUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "GROUPING_RULE_ID")
    private Integer groupingRuleId;
    @Column(name = "SHIP_TO_LOCATION_ID")
    private Integer shipToLocationId;
    @Size(max = 25)
    @Column(name = "FREIGHT_CODE")
    private String freightCode;
    @Size(max = 30)
    @Column(name = "SHIPMENT_METHOD")
    private String shipmentMethod;
    @Size(max = 1)
    @Column(name = "AUTO_RECEIPT_FLAG")
    private String autoReceiptFlag;
   
    @ManyToOne
    @JoinColumn(name = "REFERENCE_ID")
    private GmeBatchHeader reference ;
    
    @Column(name = "REFERENCE_DETAIL_ID")
    private Integer referenceDetailId;
    @Size(max = 240)
    @Column(name = "ASSIGNMENT_ID")
    private String assignmentId;

    @Size(max = 50)
    @Column(name = "HEADER_CODE")
    private String headerCode;
    @OneToOne
    @JoinColumn(name="SALES_HEADER_ID")
    private OeOrderHeader salesOrder;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "header")
    private List<MtlTxnRequestLine> mtlTxnRequestLines;
    @OneToOne
    @JoinColumn(name = "TO_ACCOUNT_ID")
    private GlCodeCombination toAccountId;
    @OneToOne
    @JoinColumn(name = "FROM_ACCOUNT_ID")
    private GlCodeCombination fromAccountId;
    @OneToOne
    @JoinColumn(name = "FROM_MAIN_INVENTORY_ID")
    private MainInventory fromMainInventoryId;
    @OneToOne
    @JoinColumn(name = "TO_MAIN_INVENTORY_ID")
    private MainInventory toMainInventoryId;
    @OneToOne
    @JoinColumn(name = "FROM_SUBINVENTORY_ID")
    private SecondaryInventory fromSubinventoryId;
    @OneToOne
    @JoinColumn(name = "TO_SUBINVENTORY_ID")
    private SecondaryInventory toSubinventoryId;
    
    public MtlTxnRequestHeader() {
  
    }

    public MtlTxnRequestHeader(GmeBatchHeader reference,OrganizationUnit orgUnit)
    {
        this.description = "MoveOrder For Batch("+reference.getBatchId()+")" ;

        this.reference = reference;
        
        this.transactionType = MoveOrderTransactionType.JOB_SCHEDULE ;
        
        this.moveOrderType = MoveOrderType.MANUFACTURING ;
        
        this.organization = orgUnit ;
    }
    
    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setMoveOrderType(MoveOrderType moveOrderType) {
        this.moveOrderType = moveOrderType;
    }

    public MoveOrderType getMoveOrderType() {
        return moveOrderType;
    }

    public MoveOrderTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(MoveOrderTransactionType transactionType) {
        this.transactionType = transactionType;
    }


    public OrganizationUnit getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationUnit organization) {
        this.organization = organization;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(Date dateRequired) {
        this.dateRequired = dateRequired;
    }



    public MoveHeaderStatus getHeaderStatus() {
        return headerStatus;
    }

    public void setHeaderStatus(MoveHeaderStatus headerStatus) {
        this.headerStatus = headerStatus;
    }

    
    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramApplicationId(Integer programApplicationId) {
        this.programApplicationId = programApplicationId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public Integer getGroupingRuleId() {
        return groupingRuleId;
    }

    public void setGroupingRuleId(Integer groupingRuleId) {
        this.groupingRuleId = groupingRuleId;
    }

    public Integer getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Integer shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public String getFreightCode() {
        return freightCode;
    }

    public void setFreightCode(String freightCode) {
        this.freightCode = freightCode;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public String getAutoReceiptFlag() {
        return autoReceiptFlag;
    }

    public void setAutoReceiptFlag(String autoReceiptFlag) {
        this.autoReceiptFlag = autoReceiptFlag;
    }

    public GmeBatchHeader getReference() {
        return reference;
    }

    public void setReference(GmeBatchHeader reference) {
        this.reference = reference;
    }

    
    public Integer getReferenceDetailId() {
        return referenceDetailId;
    }

    public void setReferenceDetailId(Integer referenceDetailId) {
        this.referenceDetailId = referenceDetailId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public SecondaryInventory getFromSubinventoryId() {
        return fromSubinventoryId;
    }

    public void setFromSubinventoryId(SecondaryInventory fromSubinventoryId) {
        this.fromSubinventoryId = fromSubinventoryId;
    }

    public SecondaryInventory getToSubinventoryId() {
        return toSubinventoryId;
    }

    public void setToSubinventoryId(SecondaryInventory toSubinventoryId) {
        this.toSubinventoryId = toSubinventoryId;
    }

    public GlCodeCombination getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(GlCodeCombination fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    
        public GlCodeCombination getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(GlCodeCombination toAccountId) {
        this.toAccountId = toAccountId;
    }

    public MainInventory getFromMainInventoryId() {
        return fromMainInventoryId;
    }

    public void setFromMainInventoryId(MainInventory fromMainInventoryId) {
        this.fromMainInventoryId = fromMainInventoryId;
    }

    public MainInventory getToMainInventoryId() {
        return toMainInventoryId;
    }

    public void setToMainInventoryId(MainInventory toMainInventoryId) {
        this.toMainInventoryId = toMainInventoryId;
    }
    
    

    public String getHeaderCode() {
        return headerCode;
    }

    public void setHeaderCode(String headerCode) {
        this.headerCode = headerCode;
    }

    public List<MtlTxnRequestLine> getMtlTxnRequestLines() {
        return mtlTxnRequestLines;
    }

    public void setMtlTxnRequestLines(List<MtlTxnRequestLine> mtlTxnRequestLines) {
        this.mtlTxnRequestLines = mtlTxnRequestLines;
    }

    public OeOrderHeader getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(OeOrderHeader salesOrder) {
        this.salesOrder = salesOrder;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (headerId != null ? headerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MtlTxnRequestHeader)) {
            return false;
        }
        MtlTxnRequestHeader other = (MtlTxnRequestHeader) object;
        if ((this.headerId == null && other.headerId != null) || (this.headerId != null && !this.headerId.equals(other.headerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.batch.MtlTxnRequestHeader[ headerId=" + headerId + " ]";
    }
    
    @PrePersist
    private void onPrePersist()
    {
       this.createdBy = 1 ;
       
       this.creationDate = new Date() ;
       
       this.lastUpdateDate = new Date() ;
       
       this.lastUpdatedBy = 1 ;
    }
}
