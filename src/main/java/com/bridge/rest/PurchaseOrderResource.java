/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.rest;

import com.bridge.enums.PoStatus;
import com.bridge.rest.dto.PoDTO;
import com.bridge.services.po.PurchaseOrderService;
import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Bridge
 */

@Path("pos")
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseOrderResource {
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;
    
    @GET
    @Path("notapproved")
    public List<PoDTO> getNotApprovedPos()
    {
      return entityManager.createQuery("SELECT NEW com.bridge.rest.dto.PoDTO(po.poHeaderId,po.client.clientName,po.clientSite.siteName,po.totalAmount,po.currency.currencyCode) "
               + " FROM PoHeader po WHERE po.poStatus = com.bridge.enums.PoStatus.INCOMPLETE")
               .getResultList();
    }
    
    @PUT
    @Path("approve/{id}")
    @Transactional
    public Response approvePo(@PathParam("id") int id)
    {
        int rowsAffected = entityManager.createQuery("UPDATE PoHeader po SET po.poStatus = com.bridge.enums.PoStatus.APPROVED"
                + " WHERE po.poHeaderId = :id")
                .setParameter("id", id)
                .executeUpdate() ;
        
        if(rowsAffected > 0)
        {
           return Response.ok().build() ;
        }
        
        return Response.noContent().build() ;
    }
    
    @PUT
    @Path("reject/{id}")
    @Transactional
    public Response rejectPo(@PathParam("id") int id)
    {
        int rowsAffected = entityManager.createQuery("UPDATE PoHeader po SET po.poStatus = com.bridge.enums.PoStatus.REJECTED"
                + " WHERE po.poHeaderId = :id")
                .setParameter("id", id)
                .executeUpdate() ;
        
        if(rowsAffected > 0)
        {
           return Response.ok().build() ;
        }
        
        return Response.noContent().build() ;
    }    
    
    @GET
    @Path("approve/count")
    public long getApprovedPosCount()
    {
      return entityManager.createQuery("SELECT count(1) FROM PoHeader po WHERE po.poStatus = com.bridge.enums.PoStatus.APPROVED " ,Long.class)
              .getSingleResult() ;
    }
    
    @GET
    @Path("reject/count")    
    public long getRejectedPosCount()
    {
      return entityManager.createQuery("SELECT count(1) FROM PoHeader po WHERE po.poStatus = com.bridge.enums.PoStatus.REJECTED " ,Long.class)
              .getSingleResult() ;        
    }

    @GET
    @Path("count")    
    public JsonObject getPosCount()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder() ;
        
        long count = entityManager.createQuery("SELECT count(1) FROM PoHeader po" ,Long.class)
              .getSingleResult() ; 
        
        builder.add("count",count ) ;
        
        long approved = getApprovedPosCount() ;
        
        long rejected = getRejectedPosCount() ;
        
        builder.add("approved",approved ) ;
        
        builder.add("rejected",rejected ) ;
        
        builder.add("incomplete", count - (approved +  rejected) ) ;
        
        return builder.build() ;
    }
}
