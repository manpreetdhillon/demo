package com.example.demo.service;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    UserRepository userRepository;
    ProductRepository productRepository;

    UserService(UserRepository userRepository, ProductRepository productRepository)
    {
        this.userRepository=userRepository;
        this.productRepository=productRepository;
    }

    public List<User> fetchUsers()
    {
        Product p=new Product();
        p.setProduct_id(1);
        p.setProduct_category_id(2);
        p.setProduct_name("noname");
        p.setManufacture_name("noname");
        productRepository.save(p);
        return userRepository.findAll();
    }

    public User fetchUser(Integer id)
    {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id-"+id+" not found"));
    }

    public List<User> fetchVipsUsers()
    {
        return userRepository.fetchVIPSUsers();
    }

    public String saveUser(User user)
    {
        userRepository.save(user);
        return "Saved Successfully";
    }

    public String deleteUser(Integer id)
    {
        userRepository.deleteById(id);
        return "deleted seccessfully";
    }

}
