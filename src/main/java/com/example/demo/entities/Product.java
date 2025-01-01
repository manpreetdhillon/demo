package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
@IdClass(ProductId.class)
public class Product
{
    @Id
    int product_id;

    @Id
    int product_category_id;

    @Column
    String product_name;

    @Column
    String manufacture_name;

    public Product()
    {

    }
    public Product(int product_id)
    {
        this.product_id=product_id;
    }
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getManufacture_name() {
        return manufacture_name;
    }

    public void setManufacture_name(String manufacture_name) {
        this.manufacture_name = manufacture_name;
    }
}
