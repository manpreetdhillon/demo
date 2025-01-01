package com.example.demo.service;

import com.example.demo.entities.AuthUser;
import com.example.demo.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{

    @Autowired
    AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser= authUserRepository.findByUsername(username);

        if (authUser==null)
        {
            throw new UsernameNotFoundException("user not found");
        }
        return new MyUserDetails(authUser);
    }
}
