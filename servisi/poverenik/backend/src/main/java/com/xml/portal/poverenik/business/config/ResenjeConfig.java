package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.xml.portal.poverenik.business.ResenjeBusiness;

@Configuration
@ComponentScan(basePackageClasses = ResenjeBusiness.class)
public class ResenjeConfig {
	
    @Bean
    @SessionScope
    public ResenjeBusiness getResenjeBusiness() {
        return new ResenjeBusiness();
    }
}