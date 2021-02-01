package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.xml.portal.poverenik.business.ZalbaOdbijanjeBusiness;

@Configuration
@ComponentScan(basePackageClasses = ZalbaOdbijanjeBusiness.class)
public class ZalbaOdbijanjeConfig {
	
    @Bean
    @SessionScope
    public ZalbaOdbijanjeBusiness getZalbaOdbijanjeBusiness() {
        return new ZalbaOdbijanjeBusiness();
    }
}