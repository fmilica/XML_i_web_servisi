package com.xml.portal.poverenik.service.soap.posta.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.portal.poverenik.service.soap.posta.PoverenikPostaServiceSoapBindingImpl;

@Configuration
public class PoverenikPostaEndpointConfig {
	@Autowired
    private Bus bus;

    @Autowired
    PoverenikPostaServiceSoapBindingImpl poverenikPostaServiceSoapBindingImpl;

    @Bean(name="poverenikPostaEndpointBean")
    public Endpoint zahtevEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, poverenikPostaServiceSoapBindingImpl);
        endpoint.publish("/poverenik-posta");
        return endpoint;
    }
}
