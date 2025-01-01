package com.example.demo.controller;

import com.example.demo.service.MyBean;
import com.example.demo.service.MyFirstComponent;
import com.example.demo.service.MyFirstService;
import com.example.demo.service.MySingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
public class MyFirstController
{

    RequestScopeBean requestScopeBean;

    @Autowired
    MyFirstService myFirstService;

    @Autowired
    MyBean myBean;

    @Autowired
    private MySingleton mySingleton;

    MyFirstController(RequestScopeBean requestScopeBean)
    {
        this.requestScopeBean=requestScopeBean;
    }

    @GetMapping(path = "getData")
    public String getData(@RequestParam String name)
    {
        return "API is working fine"+name;
    }

    @PostMapping(path = "getData2",consumes = "application/json")
    public ResponseDTO getData2(@RequestBody ResponseDTO req)
    {
        return new ResponseDTO("Hello "+req.msg);
    }

    @GetMapping("getRecords")
    public String getRecords(@RequestHeader("User-Agent") String agent)
    {
        myFirstService.getData();
        myBean.read();
        return "Agent name is "+agent + " "+ requestScopeBean.getId();
    }

    @GetMapping("getSingletonTest")
    public ResponseEntity<String> getSingletonTest()
    {
        mySingleton.setCounter();
        return new ResponseEntity<>(mySingleton+"-"+mySingleton.getCounter(), HttpStatus.ACCEPTED);
    }
}

class RequestDTO
{
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

class ResponseDTO
{
    String msg;

    ResponseDTO(String msg)
    {
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

@Component
@RequestScope
class RequestScopeBean
{
    String id;

    RequestScopeBean()
    {
        id=java.util.UUID.randomUUID().toString();
    }

    String getId()
    {
        return id;
    }
}
