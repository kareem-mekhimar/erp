/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.journal;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "GL_JE_REC_BATCH_STATUS")
public class GlJeRecBatchStatus implements Serializable {

    @Id
    @NotNull
    @Column(name = "PERIOD_ID")
    private int  periodId;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BATCH_ID")
    private int batchId;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JE_HEADER_ID")
    private int jeHeaderId;
  
    @Column(name = "STATUS")
    private int status;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY")
    private int lastUpdateBy;

    public GlJeRecBatchStatus() {
    }

    public GlJeRecBatchStatus(int periodId, int batchId, int jeHeaderId) {
        
        this.periodId = periodId;
        
        this.batchId = batchId;
       
        this.jeHeaderId = jeHeaderId;
        
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.periodId;
        hash = 97 * hash + this.batchId;
        hash = 97 * hash + this.jeHeaderId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GlJeRecBatchStatus other = (GlJeRecBatchStatus) obj;
        if (this.periodId != other.periodId) {
            return false;
        }
        if (this.batchId != other.batchId) {
            return false;
        }
        if (this.jeHeaderId != other.jeHeaderId) {
            return false;
        }
        return true;
    }

  
 
}
