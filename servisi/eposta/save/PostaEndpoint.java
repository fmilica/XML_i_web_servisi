package com.xml.portal.eposta.soap.endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;

import com.xml.portal.eposta.soap.EpostaServiceSoapBindingImpl;

@Configuration
public class PostaEndpoint {
	
	@Autowired 
	private Bus bus;
	
	@Autowired
	EpostaServiceSoapBindingImpl epostaServiceSoapBindingImpl;
	
	
	@Bean(name="epostaEndpointBean")
	public Endpoint epostaEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, epostaServiceSoapBindingImpl);
		endpoint.publish("/eposta");
		return endpoint;
	}

}
