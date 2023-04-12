package com.example.demo.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.DaoService;

@Configuration
class DbDataLoader
{
    private static final Logger log = LoggerFactory.getLogger(DbDataLoader.class);

    @Bean
    CommandLineRunner initData(DaoService daoService)
    {
        return args -> {
            // TODO you can do something after application is started.
        };
    }
}
