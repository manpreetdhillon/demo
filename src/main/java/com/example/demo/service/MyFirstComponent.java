package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class MyFirstComponent
{
    private String atext;
    private String btext;

    public String getAtext() {
        return atext;
    }

    public void setAtext(String atext) {
        this.atext = atext;
    }

    public String getBtext() {
        return btext;
    }

    public void setBtext(String btext) {
        this.btext = btext;
    }
}
