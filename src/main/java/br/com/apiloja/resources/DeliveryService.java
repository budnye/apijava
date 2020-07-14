/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.resources;

import br.com.apiloja.models.Delivery;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author budny
 */
@Stateless
@Path("deliveries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryService {
    
    @PersistenceContext(unitName = "lojaDS")
    EntityManager entityManager;
    
    @GET
    public List<Delivery> getDelivery(){
       return entityManager.createQuery("SELECT d FROM Delivery d", Delivery.class).getResultList();
    }
    
    @POST
    public Response addDelivery(Delivery delivery){
        entityManager.persist(delivery);
        return Response.status(Response.Status.CREATED).entity(delivery).build();
    }
    
    @GET
    @Path("{id}")
    public Response getDelivery(@PathParam("id") UUID id) {
        Delivery delivery = findDelivery(id);
        return Response.status(Response.Status.CREATED).entity(delivery).build();
    }
    
    @DELETE
    @Path("{id}")
    
    public void deleteDelivery(@PathParam("id") UUID id) {
        Delivery delivery = findDelivery(id);
        entityManager.remove(delivery);
    }
    
    @PUT
    @Path("{id}")
    public Delivery updateDelivery(@PathParam("id") UUID id, Delivery d) {
        d.setId(id);
        entityManager.merge(d);
        return d;
    }
    
    public Delivery findDelivery(UUID id) {
    return entityManager.find(Delivery.class, id);
    }

}
