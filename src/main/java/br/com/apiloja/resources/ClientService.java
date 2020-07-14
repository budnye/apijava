/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.resources;

import br.com.apiloja.models.Client;
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
@Path("clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientService {
    
    @PersistenceContext(unitName = "lojaDS")
    EntityManager entityManager;
    
    @GET
    public List<Client> getClients(){
       return entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }
    
    @POST
    public Response addClient(Client client){
        entityManager.persist(client);
        return Response.status(Response.Status.CREATED).entity(client).build();
    }
    
    @GET
    @Path("{id}")
    public Response getClient(@PathParam("id") UUID id) {
        Client client = findClient(id);
        return Response.status(Response.Status.CREATED).entity(client).build();
    }
    
    @DELETE
    @Path("{id}")
    
    public void deleteClient(@PathParam("id") UUID id) {
        Client client = findClient(id);
        entityManager.remove(client);
    }
    
    @PUT
    @Path("{id}")
    public Client updateClient(@PathParam("id") UUID id, Client c) {
        c.setId(id);
        entityManager.merge(c);
        return c;
    }
    
    public Client findClient(UUID id) {
    return entityManager.find(Client.class, id);
    }

}
