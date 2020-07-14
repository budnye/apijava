/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.resources;

import br.com.apiloja.models.Client;
import br.com.apiloja.models.Product;
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
@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
    
    @PersistenceContext(unitName = "lojaDS")
    EntityManager entityManager;
    
    @GET
    public List<Product> getProduct(){
       return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
    
    @POST
    public Response addProduct(Product product){
        entityManager.persist(product);
        return Response.status(Response.Status.CREATED).entity(product).build();
    }
    
    @GET
    @Path("{id}")
    public Response getProduct(@PathParam("id") UUID id) {
        Product product = findProduct(id);
        return Response.status(Response.Status.CREATED).entity(product).build();
    }
    
    @DELETE
    @Path("{id}")
    
    public void deleteProduct(@PathParam("id") UUID id) {
        Product product = findProduct(id);
        entityManager.remove(product);
    }
    
    @PUT
    @Path("{id}")
    public Product updateClient(@PathParam("id") UUID id, Product p) {
        p.setId(id);
        entityManager.merge(p);
        return p;
    }
    
    public Product findProduct(UUID id) {
    return entityManager.find(Product.class, id);
    }

}
