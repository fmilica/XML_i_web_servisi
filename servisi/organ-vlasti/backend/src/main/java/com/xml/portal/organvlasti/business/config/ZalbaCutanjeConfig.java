package com.xml.portal.organvlasti.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.organvlasti.business.ZalbaCutanjeBusiness;

@Configuration
@ComponentScan(basePackageClasses = ZalbaCutanjeBusiness.class)
public class ZalbaCutanjeConfig {
	
    @Bean
    public ZalbaCutanjeBusiness getZalbaCutanjeBusiness() {
        return new ZalbaCutanjeBusiness();
    }
}
