package com.example.demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySingleton
{
    private int counter=0;

    public int getCounter()
    {
        return counter;
    }

    public void setCounter()
    {
        counter++;
    }
}
