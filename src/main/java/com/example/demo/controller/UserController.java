package com.example.demo.controller;

import com.example.demo.entities.AuthUser;
import com.example.demo.entities.User;
import com.example.demo.service.MyJWTService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyJWTService myJWTService;

    @GetMapping("getUsers")
    public List<User> getUsers()
    {
        return userService.fetchUsers().stream().sorted((a,b) -> b.getId()-a.getId()).collect(Collectors.toUnmodifiableList());
    }

    @PostMapping("getUser")
    public ResponseEntity<User> getUser(@RequestParam Integer id)
    {
        return new ResponseEntity<>(userService.fetchUser(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("getVipsUsers")
    public List<User> getVipsUsers()
    {
        return userService.fetchVipsUsers();
    }

    @PostMapping("saveUser")
    public String saveUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @DeleteMapping("deleteUser")
    public String deleteUser(@RequestParam Integer id)
    {
        return userService.deleteUser(id);
    }

    @PostMapping("generateToken")
    public ResponseEntity<String> generateToken(@RequestBody AuthUser authUser)
    {
        System.out.println(authUser.toString());
        Authentication authentication = authenticationManager
                .authenticate( new UsernamePasswordAuthenticationToken(authUser.getUsername(),authUser.getPassword()));

        if(authentication.isAuthenticated())
        {
            return new ResponseEntity<>(myJWTService.generateToken(authUser.getUsername()), HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<>("Failed", HttpStatus.ACCEPTED);
        }

    }
}


