/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.inventory.UomLine;
import com.bridge.enums.ListLineTypeCode;
import com.bridge.enums.PriceListApplicationMethod;
import com.bridge.enums.ProductAttributes;
import com.bridge.enums.ListLineType;
import com.bridge.enums.SystemItemType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "QP_LIST_LINES")
@NamedQueries({
    @NamedQuery(name = "PriceListLines.findAll", query = "SELECT ql FROM PriceListLines ql")
})
public class PriceListLines implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "QpL")
    @TableGenerator(name = "QpL", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "QpListLine", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIST_LINE_ID")
    private BigDecimal  listLineId;
    
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private BigInteger createdBy;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private BigInteger lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private BigInteger lastUpdateLogin;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private BigInteger programApplicationId;
    @Column(name = "PROGRAM_ID")
    private BigInteger programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "REQUEST_ID")
    private BigInteger requestId;
    
    @Column(name = "LIST_LINE_TYPE_CODE")
    private ListLineTypeCode listLineTypeCode;
    
    
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Column(name = "AUTOMATIC_FLAG")
    private BigInteger automaticFlag;
    @Size(max = 30)
    @Column(name = "MODIFIER_LEVEL_CODE")
    private String modifierLevelCode;
    @Column(name = "PRICE_BY_FORMULA_ID")
    private BigInteger priceByFormulaId;
    @Column(name = "LIST_PRICE")
    private BigDecimal listPrice;
    @Size(max = 3)
    @Column(name = "LIST_PRICE_UOM_CODE")
    private String listPriceUomCode;
    @Column(name = "PRIMARY_UOM_FLAG")
    private boolean primaryUomFlag;
    @Column(name = "ORGANIZATION_ID")
    private BigInteger organizationId;
    @Column(name = "RELATED_ITEM_ID")
    private BigInteger relatedItemId;
    @Column(name = "RELATIONSHIP_TYPE_ID")
    private BigInteger relationshipTypeId;
    @Column(name = "SUBSTITUTION_ATTRIBUTE")
    private ProductAttributes substitutionAttribute;
    @Size(max = 30)
    @Column(name = "QUALIFIER_ATTRIBUTE")
    private String qualifierAttribute;
    @Size(max = 30)
    @Column(name = "SUBSTITUTION_VALUE")
    private String substitutionValue;
    @Size(max = 50)
    @Column(name = "REVISION")
    private String revision;
    @Column(name = "REVISION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date revisionDate;
    @Size(max = 30)
    @Column(name = "REVISION_REASON_CODE")
    private String revisionReasonCode;
    @Size(max = 30)
    @Column(name = "PRICE_BREAK_TYPE_CODE")
    private String priceBreakTypeCode;
    @Column(name = "PERCENT_PRICE")
    private BigInteger percentPrice;
    @Column(name = "NUMBER_EFFECTIVE_PERIODS")
    private BigInteger numberEffectivePeriods;
    @Size(max = 3)
    @Column(name = "EFFECTIVE_PERIOD_UOM")
    private String effectivePeriodUom;
    @Size(max = 30)
    @Column(name = "ARITHMETIC_OPERATOR")
    private String arithmeticOperator;
    @Column(name = "OPERAND")
    private BigInteger operand;
    @Column(name = "OVERRIDE_FLAG")
    private BigInteger overrideFlag;
    @Column(name = "PRINT_ON_INVOICE_FLAG")
    private BigInteger printOnInvoiceFlag;
    @Size(max = 30)
    @Column(name = "REBATE_TRANSACTION_TYPE_CODE")
    private String rebateTransactionTypeCode;
    @Column(name = "ESTIM_ACCRUAL_RATE")
    private BigInteger estimAccrualRate;
    @Size(max = 2000)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "GENERATE_USING_FORMULA_ID")
    private BigInteger generateUsingFormulaId;
    @Column(name = "REPRICE_FLAG")
    private BigInteger repriceFlag;
    @Size(max = 30)
    @Column(name = "LIST_LINE_NO")
    private String listLineNo;
    @Column(name = "ESTIM_GL_VALUE")
    private BigInteger estimGlValue;
    @Column(name = "BENEFIT_PRICE_LIST_LINE_ID")
    private BigInteger benefitPriceListLineId;
    @Column(name = "EXPIRATION_PERIOD_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationPeriodStartDate;
    @Column(name = "NUMBER_EXPIRATION_PERIODS")
    private BigInteger numberExpirationPeriods;
    @Size(max = 3)
    @Column(name = "EXPIRATION_PERIOD_UOM")
    private String expirationPeriodUom;
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Column(name = "ACCRUAL_FLAG")
    private BigInteger accrualFlag;
    @Column(name = "PRICING_PHASE_ID")
    private BigInteger pricingPhaseId;
    @Column(name = "PRICING_GROUP_SEQUENCE")
    private BigInteger pricingGroupSequence;
    @Size(max = 30)
    @Column(name = "INCOMPATIBILITY_GRP_CODE")
    private String incompatibilityGrpCode;
    @Column(name = "PRODUCT_PRECEDENCE")
    private BigInteger productPrecedence;
    @Size(max = 30)
    @Column(name = "PRORATION_TYPE_CODE")
    private String prorationTypeCode;
    @Column(name = "ACCRUAL_CONVERSION_RATE")
    private BigInteger accrualConversionRate;
    @Column(name = "BENEFIT_QTY")
    private BigInteger benefitQty;
    @Size(max = 3)
    @Column(name = "BENEFIT_UOM_CODE")
    private String benefitUomCode;
    @Column(name = "BENEFIT_LIMIT")
    private BigInteger benefitLimit;
    @Size(max = 30)
    @Column(name = "CHARGE_TYPE_CODE")
    private String chargeTypeCode;
    @Size(max = 30)
    @Column(name = "CHARGE_SUBTYPE_CODE")
    private String chargeSubtypeCode;
    @Column(name = "INCLUDE_ON_RETURNS_FLAG")
    private BigInteger includeOnReturnsFlag;
    @Column(name = "QUALIFICATION_IND")
    private BigInteger qualificationInd;
    @Size(max = 100)
    @Column(name = "SUBSTITUTION_DESC")
    private String substitutionDesc;

    @Column(name = "APPLICATION_METHOD")
    private PriceListApplicationMethod applicationMethod;
    @Column(name = "LINE_TYPE")
    private ListLineType lineType ;
   
    @JoinColumn(name = "LIST_HEADER_ID", referencedColumnName = "LIST_HEADER_ID")
    @ManyToOne(optional = false)
    private PriceListHeaders priceListHeader;

    @OneToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID",referencedColumnName = "INVENTORY_ITEM_ID")
    private SystemItem systemItem;
    
    @OneToOne
    @JoinColumn(name = "LIST_PRICE_UOM_ID",referencedColumnName = "UOM_ID")
    private UomLine listPriceUOM ;

    public UomLine getListPriceUOM() {
        return listPriceUOM;
    }

    public void setListPriceUOM(UomLine listPriceUOM) {
        this.listPriceUOM = listPriceUOM;
    }
    
    public SystemItem getSystemItem() {
        return systemItem;
    }

    public void setSystemItem(SystemItem systemItem) {
        this.systemItem = systemItem;
    }
    
    public PriceListHeaders getPriceListHeader() {
        return priceListHeader;
    }

    public void setPriceListHeader(PriceListHeaders priceListHeader) {
        this.priceListHeader = priceListHeader;
    }
    
    public PriceListLines() {
    }

    public PriceListLines(BigDecimal listLineId) {
        this.listLineId = listLineId;
    }

    public BigDecimal getListLineId() {
        return listLineId;
    }

    public void setListLineId(BigDecimal listLineId) {
        this.listLineId = listLineId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigInteger getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigInteger lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public BigInteger getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigInteger lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public BigInteger getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramApplicationId(BigInteger programApplicationId) {
        this.programApplicationId = programApplicationId;
    }

    public BigInteger getProgramId() {
        return programId;
    }

    public void setProgramId(BigInteger programId) {
        this.programId = programId;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public BigInteger getRequestId() {
        return requestId;
    }

    public void setRequestId(BigInteger requestId) {
        this.requestId = requestId;
    }

    public ListLineTypeCode getListLineTypeCode() {
        return listLineTypeCode;
    }

    public void setListLineTypeCode(ListLineTypeCode listLineTypeCode) {
        this.listLineTypeCode = listLineTypeCode;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public BigInteger getAutomaticFlag() {
        return automaticFlag;
    }

    public void setAutomaticFlag(BigInteger automaticFlag) {
        this.automaticFlag = automaticFlag;
    }

    public String getModifierLevelCode() {
        return modifierLevelCode;
    }

    public void setModifierLevelCode(String modifierLevelCode) {
        this.modifierLevelCode = modifierLevelCode;
    }

    public BigInteger getPriceByFormulaId() {
        return priceByFormulaId;
    }

    public void setPriceByFormulaId(BigInteger priceByFormulaId) {
        this.priceByFormulaId = priceByFormulaId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public String getListPriceUomCode() {
        return listPriceUomCode;
    }

    public void setListPriceUomCode(String listPriceUomCode) {
        this.listPriceUomCode = listPriceUomCode;
    }

    public boolean getPrimaryUomFlag() {
        return primaryUomFlag;
    }

    public void setPrimaryUomFlag(boolean primaryUomFlag) {
        this.primaryUomFlag = primaryUomFlag;
    }

    public BigInteger getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigInteger organizationId) {
        this.organizationId = organizationId;
    }

    public BigInteger getRelatedItemId() {
        return relatedItemId;
    }

    public void setRelatedItemId(BigInteger relatedItemId) {
        this.relatedItemId = relatedItemId;
    }

    public BigInteger getRelationshipTypeId() {
        return relationshipTypeId;
    }

    public void setRelationshipTypeId(BigInteger relationshipTypeId) {
        this.relationshipTypeId = relationshipTypeId;
    }

    public ProductAttributes getSubstitutionAttribute() {
        return substitutionAttribute;
    }

    public void setSubstitutionAttribute(ProductAttributes substitutionAttribute) {
        this.substitutionAttribute = substitutionAttribute;
    }

    public String getQualifierAttribute() {
        return qualifierAttribute;
    }

    public void setQualifierAttribute(String qualifierAttribute) {
        this.qualifierAttribute = qualifierAttribute;
    }

    public String getSubstitutionValue() {
        return substitutionValue;
    }

    public void setSubstitutionValue(String substitutionValue) {
        this.substitutionValue = substitutionValue;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRevisionReasonCode() {
        return revisionReasonCode;
    }

    public void setRevisionReasonCode(String revisionReasonCode) {
        this.revisionReasonCode = revisionReasonCode;
    }

    public String getPriceBreakTypeCode() {
        return priceBreakTypeCode;
    }

    public void setPriceBreakTypeCode(String priceBreakTypeCode) {
        this.priceBreakTypeCode = priceBreakTypeCode;
    }

    public BigInteger getPercentPrice() {
        return percentPrice;
    }

    public void setPercentPrice(BigInteger percentPrice) {
        this.percentPrice = percentPrice;
    }

    public BigInteger getNumberEffectivePeriods() {
        return numberEffectivePeriods;
    }

    public void setNumberEffectivePeriods(BigInteger numberEffectivePeriods) {
        this.numberEffectivePeriods = numberEffectivePeriods;
    }

    public String getEffectivePeriodUom() {
        return effectivePeriodUom;
    }

    public void setEffectivePeriodUom(String effectivePeriodUom) {
        this.effectivePeriodUom = effectivePeriodUom;
    }

    public String getArithmeticOperator() {
        return arithmeticOperator;
    }

    public void setArithmeticOperator(String arithmeticOperator) {
        this.arithmeticOperator = arithmeticOperator;
    }

    public BigInteger getOperand() {
        return operand;
    }

    public void setOperand(BigInteger operand) {
        this.operand = operand;
    }

    public BigInteger getOverrideFlag() {
        return overrideFlag;
    }

    public void setOverrideFlag(BigInteger overrideFlag) {
        this.overrideFlag = overrideFlag;
    }

    public BigInteger getPrintOnInvoiceFlag() {
        return printOnInvoiceFlag;
    }

    public void setPrintOnInvoiceFlag(BigInteger printOnInvoiceFlag) {
        this.printOnInvoiceFlag = printOnInvoiceFlag;
    }

    public String getRebateTransactionTypeCode() {
        return rebateTransactionTypeCode;
    }

    public void setRebateTransactionTypeCode(String rebateTransactionTypeCode) {
        this.rebateTransactionTypeCode = rebateTransactionTypeCode;
    }

    public BigInteger getEstimAccrualRate() {
        return estimAccrualRate;
    }

    public void setEstimAccrualRate(BigInteger estimAccrualRate) {
        this.estimAccrualRate = estimAccrualRate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigInteger getGenerateUsingFormulaId() {
        return generateUsingFormulaId;
    }

    public void setGenerateUsingFormulaId(BigInteger generateUsingFormulaId) {
        this.generateUsingFormulaId = generateUsingFormulaId;
    }

    public BigInteger getRepriceFlag() {
        return repriceFlag;
    }

    public void setRepriceFlag(BigInteger repriceFlag) {
        this.repriceFlag = repriceFlag;
    }

    public String getListLineNo() {
        return listLineNo;
    }

    public void setListLineNo(String listLineNo) {
        this.listLineNo = listLineNo;
    }

    public BigInteger getEstimGlValue() {
        return estimGlValue;
    }

    public void setEstimGlValue(BigInteger estimGlValue) {
        this.estimGlValue = estimGlValue;
    }

    public BigInteger getBenefitPriceListLineId() {
        return benefitPriceListLineId;
    }

    public void setBenefitPriceListLineId(BigInteger benefitPriceListLineId) {
        this.benefitPriceListLineId = benefitPriceListLineId;
    }

    public Date getExpirationPeriodStartDate() {
        return expirationPeriodStartDate;
    }

    public void setExpirationPeriodStartDate(Date expirationPeriodStartDate) {
        this.expirationPeriodStartDate = expirationPeriodStartDate;
    }

    public BigInteger getNumberExpirationPeriods() {
        return numberExpirationPeriods;
    }

    public void setNumberExpirationPeriods(BigInteger numberExpirationPeriods) {
        this.numberExpirationPeriods = numberExpirationPeriods;
    }

    public String getExpirationPeriodUom() {
        return expirationPeriodUom;
    }

    public void setExpirationPeriodUom(String expirationPeriodUom) {
        this.expirationPeriodUom = expirationPeriodUom;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigInteger getAccrualFlag() {
        return accrualFlag;
    }

    public void setAccrualFlag(BigInteger accrualFlag) {
        this.accrualFlag = accrualFlag;
    }

    public BigInteger getPricingPhaseId() {
        return pricingPhaseId;
    }

    public void setPricingPhaseId(BigInteger pricingPhaseId) {
        this.pricingPhaseId = pricingPhaseId;
    }

    public BigInteger getPricingGroupSequence() {
        return pricingGroupSequence;
    }

    public void setPricingGroupSequence(BigInteger pricingGroupSequence) {
        this.pricingGroupSequence = pricingGroupSequence;
    }

    public String getIncompatibilityGrpCode() {
        return incompatibilityGrpCode;
    }

    public void setIncompatibilityGrpCode(String incompatibilityGrpCode) {
        this.incompatibilityGrpCode = incompatibilityGrpCode;
    }

    public BigInteger getProductPrecedence() {
        return productPrecedence;
    }

    public void setProductPrecedence(BigInteger productPrecedence) {
        this.productPrecedence = productPrecedence;
    }

    public String getProrationTypeCode() {
        return prorationTypeCode;
    }

    public void setProrationTypeCode(String prorationTypeCode) {
        this.prorationTypeCode = prorationTypeCode;
    }

    public BigInteger getAccrualConversionRate() {
        return accrualConversionRate;
    }

    public void setAccrualConversionRate(BigInteger accrualConversionRate) {
        this.accrualConversionRate = accrualConversionRate;
    }

    public BigInteger getBenefitQty() {
        return benefitQty;
    }

    public void setBenefitQty(BigInteger benefitQty) {
        this.benefitQty = benefitQty;
    }

    public String getBenefitUomCode() {
        return benefitUomCode;
    }

    public void setBenefitUomCode(String benefitUomCode) {
        this.benefitUomCode = benefitUomCode;
    }

    public BigInteger getBenefitLimit() {
        return benefitLimit;
    }

    public void setBenefitLimit(BigInteger benefitLimit) {
        this.benefitLimit = benefitLimit;
    }

    public String getChargeTypeCode() {
        return chargeTypeCode;
    }

    public void setChargeTypeCode(String chargeTypeCode) {
        this.chargeTypeCode = chargeTypeCode;
    }

    public String getChargeSubtypeCode() {
        return chargeSubtypeCode;
    }

    public void setChargeSubtypeCode(String chargeSubtypeCode) {
        this.chargeSubtypeCode = chargeSubtypeCode;
    }

    public BigInteger getIncludeOnReturnsFlag() {
        return includeOnReturnsFlag;
    }

    public void setIncludeOnReturnsFlag(BigInteger includeOnReturnsFlag) {
        this.includeOnReturnsFlag = includeOnReturnsFlag;
    }

    public BigInteger getQualificationInd() {
        return qualificationInd;
    }

    public void setQualificationInd(BigInteger qualificationInd) {
        this.qualificationInd = qualificationInd;
    }

    public String getSubstitutionDesc() {
        return substitutionDesc;
    }

    public void setSubstitutionDesc(String substitutionDesc) {
        this.substitutionDesc = substitutionDesc;
    }

    public PriceListApplicationMethod getApplicationMethod() {
        return applicationMethod;
    }

    public void setApplicationMethod(PriceListApplicationMethod applicationMethod) {
        this.applicationMethod = applicationMethod;
    }

    public ListLineType getLineType() {
        return lineType;
    }

    public void setLineType(ListLineType lineType) {
        this.lineType = lineType;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listLineId != null ? listLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceListLines)) {
            return false;
        }
        PriceListLines other = (PriceListLines) object;
        if ((this.listLineId == null && other.listLineId != null) || (this.listLineId != null && !this.listLineId.equals(other.listLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.QpListLines[ listLineId=" + listLineId + " ]";
    }
    
}
