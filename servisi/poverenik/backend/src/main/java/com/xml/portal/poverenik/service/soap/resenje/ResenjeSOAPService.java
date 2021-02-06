package com.xml.portal.poverenik.service.soap.resenje;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;


@RestController
@RequestMapping(value = "poverenik/soap/resenje")
public class ResenjeSOAPService {
	
	@PostMapping(value = "/send-resenje", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Void> sendResenje(@RequestBody Document resenje) throws Exception{
		String soapEndpointUrl = "http://localhost:8082/ws/resenje";
		
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		
		SOAPBody soapBody = envelope.getBody();
		soapBody.addDocument(resenje);

		System.out.println();
		System.out.println(soapBody);

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");
		// Send SOAP Message to SOAP Server
		SOAPMessage soapResponse = soapConnection.call(soapMessage, soapEndpointUrl);

		System.out.println("Response SOAP Message:");
		soapResponse.writeTo(System.out);
		System.out.println();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}


