package com.xml.portal.poverenik.service.posta;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml.portal.poverenik.data.dao.pismo.Pismo;

public class EpostaClient extends WebServiceGatewaySupport{
	
	public void posaljiPismo(Pismo pismo) {
		String uri = "http://localhost:8886/ws/eposta";
        
		getWebServiceTemplate().marshalSendAndReceive(uri, pismo, new SoapActionCallback("http://eposta/ws/posaljiPoruku"));
	}

}
