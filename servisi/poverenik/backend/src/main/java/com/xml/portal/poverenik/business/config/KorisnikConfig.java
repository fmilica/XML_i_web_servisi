package com.xml.portal.poverenik.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.poverenik.business.KorisnikBusiness;

@Configuration
@ComponentScan(basePackageClasses = KorisnikBusiness.class)
public class KorisnikConfig {
	
  @Bean
  public KorisnikBusiness getKorisnikBusiness() {
      return new KorisnikBusiness();
  }

}
