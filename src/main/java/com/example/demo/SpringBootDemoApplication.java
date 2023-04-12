package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // If you want to use transactional annotation, see https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#transactions
public class SpringBootDemoApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
