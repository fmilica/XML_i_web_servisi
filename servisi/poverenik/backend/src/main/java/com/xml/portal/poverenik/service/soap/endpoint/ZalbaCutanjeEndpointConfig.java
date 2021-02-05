package com.xml.portal.poverenik.service.soap.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.poverenik.service.soap.zalba_cutanje.ZalbaCutanjeServiceSoapBindingImpl;

@Configuration
public class ZalbaCutanjeEndpointConfig {
	@Autowired
	private Bus bus;

	@Autowired
	ZalbaCutanjeServiceSoapBindingImpl zalbaCutanjeServiceSoapBindingImpl;

	@Bean(name = "zalbaCutanjeEndpointBean")
	public Endpoint zalbaCutanjeEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, zalbaCutanjeServiceSoapBindingImpl);
		endpoint.publish("/zalbacutanje");
		return endpoint;
	}
}
