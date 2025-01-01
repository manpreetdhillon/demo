package com.example.demo.entities;


public class ProductId
{
    int product_id;
    int product_category_id;

    public ProductId()
    {

    }
    public ProductId(int product_id, int product_category_id)
    {
        this.product_id=product_id;
        this.product_category_id=product_category_id;
    }
}
