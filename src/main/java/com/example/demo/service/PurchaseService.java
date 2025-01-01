package com.example.demo.service;

import com.example.demo.dto.PurchaseRequest;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductId;
import com.example.demo.entities.Purchase;
import com.example.demo.entities.User;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.PurchaseRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseService
{
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public String savePurchase(PurchaseRequest purchaseRequest)
    {
        if(productRepository.existsById(new ProductId(purchaseRequest.getProduct_id(),1)))
        {
            User user = userRepository.findById(purchaseRequest.getUser_id()).get();
            Purchase p= new Purchase();
            p.setProduct_id(purchaseRequest.getProduct_id());
            p.setUser(user);
            purchaseRepository.save(p);
            //System.out.println("Product Name is "+ findProductNameOfOrder("aedf1031-1184-4765-8145-dfb4d523dd6a"));
            return "Save successfully";
        }
        else
        {
            return "No Product Info Available";
        }


    }

    public String findProductNameOfOrder(String UUID)
    {
        return purchaseRepository.findProductNameOfOrder(UUID);
    }
}
