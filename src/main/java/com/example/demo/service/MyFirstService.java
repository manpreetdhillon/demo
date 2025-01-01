package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService
{

    MyFirstComponent myFirstComponent;

    MyFirstService(MyFirstComponent myFirstComponent)
    {
        this.myFirstComponent=myFirstComponent;
    }
    public void getData()
    {
        myFirstComponent.setAtext("Atext");
        myFirstComponent.setBtext("Btext");
        System.out.println(myFirstComponent.getAtext()+" "+myFirstComponent.getBtext());
    }

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public MyBean myBean()
    {
        return new MyBean();
    }
}
