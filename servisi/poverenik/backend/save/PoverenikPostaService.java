package com.xml.portal.poverenik.service.soap.posta;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-03T14:02:23.338+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "PoverenikPostaService", 
                  wsdlLocation = "classpath:wsdl/poverenik-posta.wsdl",
                  targetNamespace = "http://poverenik-posta") 
public class PoverenikPostaService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://poverenik-posta", "PoverenikPostaService");
    public final static QName PoverenikPostaServiceSoapBinding = new QName("http://poverenik-posta", "PoverenikPostaServiceSoapBinding");
    static {
        URL url = PoverenikPostaService.class.getClassLoader().getResource("wsdl/poverenik-posta.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(PoverenikPostaService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/poverenik-posta.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public PoverenikPostaService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PoverenikPostaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PoverenikPostaService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public PoverenikPostaService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public PoverenikPostaService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public PoverenikPostaService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns PoverenikPostaServicePortType
     */
    @WebEndpoint(name = "PoverenikPostaServiceSoapBinding")
    public PoverenikPostaServicePortType getPoverenikPostaServiceSoapBinding() {
        return super.getPort(PoverenikPostaServiceSoapBinding, PoverenikPostaServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PoverenikPostaServicePortType
     */
    @WebEndpoint(name = "PoverenikPostaServiceSoapBinding")
    public PoverenikPostaServicePortType getPoverenikPostaServiceSoapBinding(WebServiceFeature... features) {
        return super.getPort(PoverenikPostaServiceSoapBinding, PoverenikPostaServicePortType.class, features);
    }

}
