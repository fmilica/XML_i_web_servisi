package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.poverenik.business.IzjasnjenjeBusiness;

@Configuration
@ComponentScan(basePackageClasses = IzjasnjenjeBusiness.class)
public class IzjasnjenjeBusinessConfig {

	@Bean
	public IzjasnjenjeBusiness getIzjasnjenjeBusiness() {
		return new IzjasnjenjeBusiness();
  }
	
}
