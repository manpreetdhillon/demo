package com.example.demo.controller;

import com.example.demo.dto.PurchaseRequest;
import com.example.demo.entities.Purchase;
import com.example.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController
{
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("purchase")
    public String purchase(@RequestBody PurchaseRequest purchase)
    {
        return purchaseService.savePurchase(purchase);
    }
}
