package com.xml.portal.organvlasti.service.soap.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.organvlasti.service.soap.resenje.ResenjeServiceSoapBindingImpl;


@Configuration
public class ResenjeEndpointConfig {
	 @Autowired
	    private Bus bus;
	 
	 @Autowired
	 ResenjeServiceSoapBindingImpl resenjeServiceSoapBindingImpl;
	 
	 @Bean(name="resenjeEndpointBean")
	    public Endpoint resenjeEndpoint() {
	        EndpointImpl endpoint = new EndpointImpl(bus, resenjeServiceSoapBindingImpl);
	        endpoint.publish("/resenje");
	        return endpoint;
	    }
}
