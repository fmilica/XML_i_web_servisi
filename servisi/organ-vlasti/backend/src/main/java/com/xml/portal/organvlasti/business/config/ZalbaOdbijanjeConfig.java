package com.xml.portal.organvlasti.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.xml.portal.organvlasti.business.ZalbaOdbijanjeBusiness;

@Configuration
@ComponentScan(basePackageClasses = ZalbaOdbijanjeBusiness.class)
public class ZalbaOdbijanjeConfig {
	
    @Bean
    @SessionScope
    public ZalbaOdbijanjeBusiness getZalbaOdbijanjeBusiness() {
        return new ZalbaOdbijanjeBusiness();
    }
}