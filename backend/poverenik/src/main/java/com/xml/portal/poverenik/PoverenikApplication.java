package com.xml.portal.poverenik;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;
import com.xml.portal.poverenik.data.xmldb.api.StoreXML;

@SpringBootApplication
public class PoverenikApplication {

	public static void main(String[] args) throws Exception {
		// STORE
		/*String filePath = "src/main/resources/data/zahtev_gen1.xml";
		JAXBContext context = JAXBContext.newInstance(Zahtev.class.getPackageName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File(filePath));
		*/
		/*DOMParser parser = new DOMParser();
		parser.buildDocument("src/main/resources/data/resenje_gen1.xml");
		Document resenje = parser.getDocument();
		*/
		//StoreXML.store(zahtev);
		// RETRIEVE
		//RetrieveXML.retrieve(ZalbaCutanje.class, "1");
		//RetrieveXML.retrieve(null, "1");
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
