
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.xml.portal.poverenik.service.soap.izvestaj;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.stereotype.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-04T10:29:01.905+01:00
 * Generated source version: 3.2.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "IzvestajService",
                      portName = "IzvestajServiceSoapBinding",
                      targetNamespace = "http://izvestaj-service",
                      wsdlLocation = "src/main/resources/data/wsdl/izvestaj.wsdl",
                      endpointInterface = "com.xml.portal.poverenik.service.soap.izvestaj.IzvestajServicePortType")
@Service                     
public class IzvestajServiceSoapBindingImpl implements IzvestajServicePortType {

    private static final Logger LOG = Logger.getLogger(IzvestajServiceSoapBindingImpl.class.getName());

    /* (non-Javadoc)
     * @see izvestaj_service.IzvestajServicePortType#sendIzvestaj(izvestaj.Izvestaj izvestaj)*
     */
    public void sendIzvestaj(com.xml.portal.poverenik.data.dao.izvestaj.Izvestaj izvestaj) { 
        LOG.info("Executing operation sendIzvestaj");
        System.out.println(izvestaj);
        try {
        	System.out.println("UPISATI U BAZU");
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
