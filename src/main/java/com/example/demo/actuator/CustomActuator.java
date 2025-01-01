package com.example.demo.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "customEndpoint")
@Component
public class CustomActuator
{
    @ReadOperation
    public String customMethod()
    {
        return "Custom Endpoint is working";
    }
}
