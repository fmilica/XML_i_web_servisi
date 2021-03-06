package com.xml.portal.organvlasti.service.soap.zalba_cutanje;

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

import com.xml.portal.organvlasti.business.ZalbaCutanjeBusiness;
import com.xml.portal.organvlasti.data.dao.odgovor.Odgovor;
import com.xml.portal.organvlasti.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.organvlasti.dto.OdgovorDTO;

@RestController
@RequestMapping(value = "organvlasti/soap/zalba-cutanje")
public class ZalbaCutanjeSOAPService {

    @Autowired
    private ZalbaCutanjeBusiness zalbaCutanjeBusiness;
	
	@PostMapping(value = "/send-odgovor", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Void> sendOdgovor(@RequestBody OdgovorDTO odgovorDTO) throws Exception {
		
        // postavljanje vrednosti izjasnjenje zahteva na true
        ZalbaCutanje zalbaCutanje = zalbaCutanjeBusiness.getById(odgovorDTO.getId_zalbe());
        zalbaCutanjeBusiness.izjasnjenjeTrue(odgovorDTO.getId_zalbe(), zalbaCutanje);
		
		String soapEndpointUrl = "http://localhost:8081/ws/zalbacutanje";
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		Odgovor odgovor = new Odgovor(odgovorDTO.getIzjasnjenje(), odgovorDTO.getId_zalbe());
		
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		JAXBContext jc = JAXBContext.newInstance(Odgovor.class);

		// Marshal the Object to a Document
		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(odgovor, document);

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
		/*Unmarshaller unmarshaller = jc.createUnmarshaller();
		SOAPBody respBody = soapResponse.getSOAPBody();
		NodeList list = respBody.getElementsByTagNameNS("*", "Zalba_cutanje");
		Element zalbaElem = (Element) list.item(0);
		if(zalbaElem != null) {
//			ZalbaCutanje zalba = (ZalbaCutanje) unmarshaller.unmarshal(zalbaElem);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		Greska greska = new Greska("Doslo je do greske.");
		*/
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(greska);
	}
}
