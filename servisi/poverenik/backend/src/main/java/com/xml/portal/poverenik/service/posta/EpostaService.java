package com.xml.portal.poverenik.service.posta;

import java.util.Base64;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.exist.util.Base64Decoder;
import org.exist.util.Base64Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.data.dao.pismo.Pismo;

@RestController
@RequestMapping(value = "poverenik/eposta")
public class EpostaService {
	
	
	@PostMapping(value = "/send-email", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Void> posaljiMejl(@RequestBody Pismo pismo) throws Exception{
		
		String soapEndpointUrl = "http://localhost:8886/ws/eposta";
//        String soapAction = "http://localhost:8886/ws/eposta";
        
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "ps";
        String myNamespaceURI = "http://pismo";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        SOAPBody soapBody = envelope.getBody();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
//        SOAPElement posaljiPismoElem = soapBody.addChildElement("posaljiPismo");
        SOAPElement pismoElem = soapBody.addChildElement("pismo", myNamespace);
        pismoElem.setAttribute("tipPriloga", pismo.getTipPriloga());
        SOAPElement primalacElem = pismoElem.addChildElement("primalac", myNamespace);
        primalacElem.addTextNode(pismo.getPrimalac());
        SOAPElement naslovElem = pismoElem.addChildElement("naslov", myNamespace);
        naslovElem.addTextNode(pismo.getNaslov());
        SOAPElement sadrzajElem = pismoElem.addChildElement("sadrzaj", myNamespace);
        sadrzajElem.addTextNode(pismo.getSadrzaj());
        SOAPElement prilogElem = pismoElem.addChildElement("prilog", myNamespace);
        System.out.println("PRILOG");
       
        String s = Base64.getEncoder().encodeToString(pismo.getPrilog());
        System.out.println(s);
        prilogElem.addTextNode(s);
        
        System.out.println();
        System.out.println(soapBody);
        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");
        // Send SOAP Message to SOAP Server
        SOAPMessage soapResponse = soapConnection.call(soapMessage, soapEndpointUrl);

        // Print the SOAP Response
        System.out.println("Response SOAP Message:");
        soapResponse.writeTo(System.out);
        System.out.println();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


}
