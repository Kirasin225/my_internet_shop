package com.kirasin.config;

import com.kirasin.model.Basket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ComponentScan(basePackages = "com.kirasin")
public class ApplicationConfiguration {
    @Bean
    public Basket basket() {
        return new Basket(new ArrayList<>());
    }
}
