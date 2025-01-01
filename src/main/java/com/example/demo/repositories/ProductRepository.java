package com.example.demo.repositories;

import com.example.demo.entities.Product;
import com.example.demo.entities.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId>
{
}
