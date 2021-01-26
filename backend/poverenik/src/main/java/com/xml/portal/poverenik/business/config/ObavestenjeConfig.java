package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.xml.portal.poverenik.business.ObavestenjeBusiness;

@Configuration
@ComponentScan(basePackageClasses = ObavestenjeBusiness.class)
public class ObavestenjeConfig {
	
    @Bean
//    @SessionScope
    public ObavestenjeBusiness getObavestenjeBusiness() {
        return new ObavestenjeBusiness();
    }
}
