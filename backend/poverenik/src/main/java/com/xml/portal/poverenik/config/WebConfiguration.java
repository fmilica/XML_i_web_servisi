package com.xml.portal.poverenik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc
public class WebConfiguration {//implements WebMvcConfigurer{
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
		    public void addCorsMappings(CorsRegistry registry) {
		        registry.addMapping("/**").allowedMethods("OPTIONS, GET", "POST", "PUT", "DELETE").allowedOrigins("http://localhost:4200");
		     
		    }
		};
	}
	/*
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("OPTIONS, GET", "POST", "PUT", "DELETE").allowedOrigins("http://localhost:4200");
    }
    */
	
	
}
