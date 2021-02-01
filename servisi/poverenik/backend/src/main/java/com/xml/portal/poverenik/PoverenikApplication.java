package com.xml.portal.poverenik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoverenikApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PoverenikApplication.class, args);
	}
	
	/*
	 * Konfiguracija apache CXF JAX-RS servleta
	 * Moguce je konfigurasati endpoints dynamicki vezano za same klase
	 */
	
	/*
	 * @Bean public ServletRegistrationBean<CXFServlet> cxfServlet() { CXFServlet
	 * cxfServlet = new CXFServlet(); ServletRegistrationBean<CXFServlet> servletReg
	 * = new ServletRegistrationBean<CXFServlet>(cxfServlet, "/*");
	 * servletReg.setLoadOnStartup(1); return servletReg; }
	 */
}
