/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.resources;

import br.com.apiloja.models.Delivery;
import br.com.apiloja.models.Order;
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
@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {
    
    @PersistenceContext(unitName = "lojaDS")
    EntityManager entityManager;
    
    @GET
    public List<Order> getOrder(){
       return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
    
    @POST
    public Response addOrder(Delivery delivery){
        entityManager.persist(delivery);
        return Response.status(Response.Status.CREATED).entity(delivery).build();
    }
    
    @GET
    @Path("{id}")
    public Response getOrder(@PathParam("id") UUID id) {
        Order order = findOrder(id);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }
    
    @DELETE
    @Path("{id}")
    
    public void deleteOrder(@PathParam("id") UUID id) {
        Order order = findOrder(id);
        entityManager.remove(order);
    }
    
    @PUT
    @Path("{id}")
    public Order updateOrder(@PathParam("id") UUID id, Order o) {
        o.setId(id);
        entityManager.merge(o);
        return o;
    }
    
    public Order findOrder(UUID id) {
    return entityManager.find(Order.class, id);
    }

}
