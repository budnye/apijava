/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author budny
 */
@Entity
@Table(name = "products", schema = "public")
public class Delivery implements Serializable{
    
    @Id
    @GeneratedValue
    UUID id;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "delivery_product", schema = "public",
            joinColumns = { @JoinColumn(name = "delivery_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")} 
    )
    private List<Product> products = new ArrayList<>();
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    Client client;

    public Delivery() {
        
    }

    public Delivery(UUID id, Client client) {
        this.id = id;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   
    
   
    
    
    
}
