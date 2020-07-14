/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.apiloja.models;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author budny
 */
@Entity
@Table(name = "suppliers", schema = "public")
public class Supplier implements Serializable {
    
    @Id
    @GeneratedValue
    UUID id;
    
    @Column(nullable = false)
    String brand;
    
    @Column(nullable = false)
    String sellerName;
    
    @Column(nullable = false)
    String cnpj;

    public Supplier()
    {
        
    }
    
    public Supplier(UUID id, String brand, String sellerName, String cnpj) {
        this.id = id;
        this.brand = brand;
        this.sellerName = sellerName;
        this.cnpj = cnpj;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
    
}
