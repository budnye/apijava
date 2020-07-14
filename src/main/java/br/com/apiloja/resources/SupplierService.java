/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.resources;

import br.com.apiloja.models.Supplier;
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
@Path("suppliers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SupplierService {
    
    @PersistenceContext(unitName = "lojaDS")
    EntityManager entityManager;
    
    @GET
    public List<Supplier> getSuppliers(){
       return entityManager.createQuery("SELECT s FROM Supplier s", Supplier.class).getResultList();
    }
    
    @POST
    public Response addSupplier(Supplier supplier){
        entityManager.persist(supplier);
        return Response.status(Response.Status.CREATED).entity(supplier).build();
    }
    
    @GET
    @Path("{id}")
    public Response getSupplier(@PathParam("id") UUID id) {
        Supplier supplier = findClient(id);
        return Response.status(Response.Status.CREATED).entity(supplier).build();
    }
    
    @DELETE
    @Path("{id}")
    
    public void deleteSupplier(@PathParam("id") UUID id) {
        Supplier client = findClient(id);
        entityManager.remove(client);
    }
    
    @PUT
    @Path("{id}")
    public Supplier updateSupplier(@PathParam("id") UUID id, Supplier s) {
        s.setId(id);
        entityManager.merge(s);
        return s;
    }
    
    public Supplier findClient(UUID id) {
    return entityManager.find(Supplier.class, id);
    }

}
