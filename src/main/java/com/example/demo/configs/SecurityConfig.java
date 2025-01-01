package com.example.demo.configs;

import com.example.demo.service.MyJWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
{
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private MyJWTFilter myJWTFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
         httpSecurity
                .csrf(crsf -> crsf
                        .ignoringRequestMatchers("/generateToken","/getUser"))
                .authorizeHttpRequests(auth ->
                auth.requestMatchers("/getUser").hasAnyRole("USER")
                        .requestMatchers("/generateToken").permitAll()
                        .requestMatchers("/getSingletonTest").permitAll()
                        .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
                . sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

         return httpSecurity.addFilterBefore(myJWTFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailService(PasswordEncoder passwordEncoder) throws Exception
//    {
//        UserDetails admin= User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("Admin1234")).roles("ADMIN").build();
//        UserDetails user = User.builder()
//                .username("user1")
//                .password(passwordEncoder.encode("User1234")).roles("USER").build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
