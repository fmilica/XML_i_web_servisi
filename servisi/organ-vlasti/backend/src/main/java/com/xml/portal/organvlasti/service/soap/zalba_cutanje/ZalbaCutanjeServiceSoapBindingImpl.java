
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.xml.portal.organvlasti.service.soap.zalba_cutanje;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xml.portal.organvlasti.data.dao.zalba_cutanje.ZalbaCutanje;
import com.xml.portal.organvlasti.business.ZalbaCutanjeBusiness;


/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-06T14:17:55.578+01:00
 * Generated source version: 3.2.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "ZalbaCutanjeService",
                      portName = "ZalbaCutanjeServiceSoapBinding",
                      targetNamespace = "http://zalbacutanje",
                      wsdlLocation = "src/main/resources/data/wsdl/zalba-cutanje.wsdl",
                      endpointInterface = "com.xml.portal.organvlasti.service.soap.zalba_cutanje.ZalbaCutanjeServicePortType")
@Service                 
public class ZalbaCutanjeServiceSoapBindingImpl implements ZalbaCutanjeServicePortType {

    private static final Logger LOG = Logger.getLogger(ZalbaCutanjeServiceSoapBindingImpl.class.getName());
    @Autowired
    private ZalbaCutanjeBusiness zalbaCutanjeBusiness;
    /* (non-Javadoc)
     * @see zalbacutanje.ZalbaCutanjeServicePortType#sendZalbaCutanje(zalbacutanje.ZalbaCutanje zalbaCutanje)*
     */
    public void sendZalbaCutanje(ZalbaCutanje zalbaCutanje) { 
        LOG.info("Executing operation sendZalbaCutanje");
        System.out.println(zalbaCutanje);
        try {
        	String documentId = zalbaCutanjeBusiness.saveToDB(zalbaCutanje);
        	System.out.println(documentId);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
