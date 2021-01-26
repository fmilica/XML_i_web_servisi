package com.xml.portal.poverenik;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PoverenikApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PoverenikApplication.class, args);
	}
	 
	/*
	 * Konfiguracija apache CXF JAX-RS servleta
	 * Moguce je konfigurasati endpoints dynamicki vezano za same klase
	 */
	
	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean<CXFServlet> servletReg = new ServletRegistrationBean<CXFServlet>(cxfServlet, "/*");
		servletReg.setLoadOnStartup(1);
		return servletReg;
	}
	
}
