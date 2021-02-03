package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.poverenik.business.IzvestajBusiness;

@Configuration
@ComponentScan(basePackageClasses = IzvestajBusiness.class)
public class IzvestajConfig {
	
	@Bean
	public IzvestajBusiness getIzvestajBusiness() {
		return new IzvestajBusiness();
  }
}
