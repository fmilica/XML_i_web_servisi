package com.xml.portal.organvlasti.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.organvlasti.business.ResenjeBusiness;

@Configuration
@ComponentScan(basePackageClasses = ResenjeBusiness.class)
public class ResenjeConfig {
	
    @Bean
    public ResenjeBusiness getResenjeBusiness() {
        return new ResenjeBusiness();
    }
}