package com.xml.portal.organvlasti.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.organvlasti.business.KorisnikBusiness;

@Configuration
@ComponentScan(basePackageClasses = KorisnikBusiness.class)
public class KorisnikConfig {
	
  @Bean
  public KorisnikBusiness getKorisnikBusiness() {
      return new KorisnikBusiness();
  }

}
