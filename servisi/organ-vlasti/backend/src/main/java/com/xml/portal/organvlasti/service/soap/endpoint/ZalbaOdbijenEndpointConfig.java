package com.xml.portal.organvlasti.service.soap.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.organvlasti.service.soap.zalba_odbijanje.ZalbaOdbijenServiceSoapBindingImpl;

@Configuration
public class ZalbaOdbijenEndpointConfig {
	@Autowired
	private Bus bus;
	
	@Autowired
	ZalbaOdbijenServiceSoapBindingImpl zalbaOdbijenServiceSoapBindingImpl;
	
	@Bean(name = "zalbaOdbijenEndpointBean")
	public Endpoint zalbaOdbijenEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, zalbaOdbijenServiceSoapBindingImpl);
		endpoint.publish("/zalbaodbijen");
		return endpoint;
	}
}
