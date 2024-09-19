package com.example.webDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
public class Product {
    @Id
    private int prodId;
    private String prodName;
    private int price;

//    public Product(int price, String prodName, int prodId) {
//        this.price = price;
//        this.prodName = prodName;
//        this.prodId = prodId;
//    }

    public int getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public int getPrice() {
        return price;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                '}';
    }
}
