package com.xml.portal.poverenik.service.soap.zahtev;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xml.portal.poverenik.business.ZahtevBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;

@RestController
@RequestMapping(value = "poverenik/soap/zahtev")
@CrossOrigin(origins = "http://localhost:4200")
public class ZahtevSOAPService {
	
	@Autowired
	private ZahtevBusiness zahtevBusiness;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getZahtev(@PathVariable String id) throws Exception {
		String soapEndpointUrl = "http://localhost:8082/ws/zahtev";

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();

		String myNamespace = "zh";
		String myNamespaceURI = "http://zahtev";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
//	envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

		SOAPBody soapBody = envelope.getBody();
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
		SOAPElement getZahtevElem = soapBody.addChildElement("getZahtev");
//	getZahtevElem.addNamespaceDeclaration(myNamespace, myNamespaceURI);
		getZahtevElem.setAttribute("xmlns", "http://zahtev");
		SOAPElement idElem = getZahtevElem.addChildElement("id", myNamespace);
		idElem.addTextNode(id);

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
		
		JAXBContext context = JAXBContext.newInstance(Zahtev.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		SOAPBody body = soapResponse.getSOAPBody();
		NodeList list = body.getElementsByTagNameNS("*", "Zahtev");
		Element zahtevElem = (Element)list.item(0);
		if (zahtevElem != null) {
			Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(zahtevElem);
			String documentId = zahtevBusiness.saveToDB(zahtev);
			System.out.println(documentId);
			return new ResponseEntity<Object>(soapResponse.getSOAPBody(), HttpStatus.OK);
		} else {
			Greska greska = new Greska("Zahtev sa prosledjenim identifikatorom ne postoji.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greska);
		}
	}
}
