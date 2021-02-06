package com.xml.portal.organvlasti.service.soap.zalba_cutanje;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-06T12:27:16.035+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "ZalbaCutanjeService", 
                  wsdlLocation = "classpath:wsdl/zalba-cutanje.wsdl",
                  targetNamespace = "http://zalbacutanje") 
public class ZalbaCutanjeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://zalbacutanje", "ZalbaCutanjeService");
    public final static QName ZalbaCutanjeServiceSoapBinding = new QName("http://zalbacutanje", "ZalbaCutanjeServiceSoapBinding");
    static {
        URL url = ZalbaCutanjeService.class.getClassLoader().getResource("wsdl/zalba-cutanje.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ZalbaCutanjeService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/zalba-cutanje.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public ZalbaCutanjeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZalbaCutanjeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZalbaCutanjeService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ZalbaCutanjeService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ZalbaCutanjeService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ZalbaCutanjeService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ZalbaCutanjeServicePortType
     */
    @WebEndpoint(name = "ZalbaCutanjeServiceSoapBinding")
    public ZalbaCutanjeServicePortType getZalbaCutanjeServiceSoapBinding() {
        return super.getPort(ZalbaCutanjeServiceSoapBinding, ZalbaCutanjeServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZalbaCutanjeServicePortType
     */
    @WebEndpoint(name = "ZalbaCutanjeServiceSoapBinding")
    public ZalbaCutanjeServicePortType getZalbaCutanjeServiceSoapBinding(WebServiceFeature... features) {
        return super.getPort(ZalbaCutanjeServiceSoapBinding, ZalbaCutanjeServicePortType.class, features);
    }

}
