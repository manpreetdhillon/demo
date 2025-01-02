package com.example.demo.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DBConfig
{
    @Value("${demo.datasource.url}")
    private String dbURL;

    @Value("${demo.datasource.username}")
    private String username;

    @Value("${demo.datasource.password}")
    private String password;

    @Value("${demo.datasource.driver-class-name}")
    private String dbDriver;

    @Bean
    public DataSource dataSource() throws Exception
    {
        DataSource dataSource=null;

        try
        {

            dataSource= DataSourceBuilder.create()
                    .url(dbURL)
                    .username(username)
                    .password(password)
                    .driverClassName(dbDriver)
                    .build();
            dataSource.getConnection().isValid(10);
            //log.info("DB Connection is established successfully");
        }
        catch (Exception e)
        {
            System.out.println("DB connection have some issue" + e);
            System.exit(0);
        }
        return dataSource;
    }
}
