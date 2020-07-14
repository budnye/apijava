/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.models;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author budny
 */
@Entity
@Table(name = "products", schema = "public")
public class Product implements Serializable{
    @Id
    @GeneratedValue
    UUID id;
    
    @NotNull(message = "O campo nome não pode ficar vazio.")
    @Column(nullable = false)
    String name;
    
    @NotNull(message = "O campo descrição não pode ficar vazio.")
    @Column(nullable = false)
    String description;
    
    @NotNull(message = "O campo preço não pode ficar vazio.")
    @Column(nullable = false)
    Double price;
    
    @NotNull(message = "O campo fornecedor não pode ficar vazio.")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    public Product(){
        
    }
    
    public Product(UUID id, String name, String description, Double price, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.supplier = supplier;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
            
    
}
