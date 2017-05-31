/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hzPartiesTypes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.bridge.entities.hzPartiesTypes.HzPartiesTypes;
import com.bridge.services.hzPartiesTypes.HzPartiesTypesService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class HzPartiesTypesController implements Serializable {
    
    @EJB
    private HzPartiesTypesService hzPartiesTypesService;
    private HzPartiesTypes hzPartiesTypes;
    private List<HzPartiesTypes> hzPartiesTypeslist;
    
    private Integer typeStatus;
    private Integer typeid;
    private String typename;
    private Integer enabledflag;
    
    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getEnabledflag() {
        return enabledflag;
    }

    public void setEnabledflag(Integer enabledflag) {
        this.enabledflag = enabledflag;
    }
    
    public List<HzPartiesTypes> getHzPartiesTypeslist(){
        
        return hzPartiesTypeslist;
    }
    
    public void filter(){
        hzPartiesTypeslist  = hzPartiesTypesService.findHzPartiesType(typeid, typename, typeStatus, enabledflag);        
    }

}
