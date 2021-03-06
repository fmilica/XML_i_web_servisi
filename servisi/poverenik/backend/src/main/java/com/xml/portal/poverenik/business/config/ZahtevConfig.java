package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.poverenik.business.ZahtevBusiness;

@Configuration
@ComponentScan(basePackageClasses = ZahtevBusiness.class)
public class ZahtevConfig {
	
    @Bean
    public ZahtevBusiness getZahtevBusiness() {
        return new ZahtevBusiness();
    }
}
