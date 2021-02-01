package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.xml.portal.poverenik.business.ZahtevBusiness;

@Configuration
@ComponentScan(basePackageClasses = ZahtevBusiness.class)
public class ZahtevConfig {
	
    @Bean
    @SessionScope
    public ZahtevBusiness getZahtevBusiness() {
        return new ZahtevBusiness();
    }
}