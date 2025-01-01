package com.example.demo.repositories;

import com.example.demo.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String>
{

    @Query("Select p2.product_name from Purchase p inner join Product p2 ON p.product_id =p2.product_id where p.purchase_id =:uuid")
    public String findProductNameOfOrder(String uuid);
}
