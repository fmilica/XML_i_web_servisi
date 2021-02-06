package com.xml.portal.poverenik.service.soap.zalba_odbijanje;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.xml.portal.poverenik.business.ZalbaOdbijanjeBusiness;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;

@RestController
@RequestMapping(value = "poverenik/soap/zalba-odbijen")
public class ZalbaOdbijenSOAPService {

	@Autowired
	private ZalbaOdbijanjeBusiness zalbaOdbijanjeBusiness;
	
	@PostMapping(value = "/send-zalba", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Void> sendZalbaOdbijen(@RequestBody ZalbaOdbijanje zalba) throws Exception {
		String soapEndpointUrl = "http://localhost:8082/ws/zalbaodbijen";

		// podesi cekanje na true
		String[] list = zalba.getId().split("/");
		String zalbaId = list[list.length - 1];
		boolean uspesno = zalbaOdbijanjeBusiness.cekanjeTrue(zalbaId, zalba);
		System.out.println("Uspesno izmenjeno stanje cekanja - " + uspesno);
		
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		JAXBContext jc = JAXBContext.newInstance(ZalbaOdbijanje.class);

		// Marshal the Object to a Document
		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(zalba, document);

		SOAPBody soapBody = envelope.getBody();
		soapBody.addDocument(document);

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
