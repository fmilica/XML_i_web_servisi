package com.xml.portal.organvlasti.service.soap.zalba_cutanje;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.xml.portal.organvlasti.data.dao.zalba_cutanje.ObjectFactory;
import com.xml.portal.organvlasti.data.dao.zalba_cutanje.ZalbaCutanje;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-06T14:17:55.587+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://zalbacutanje", name = "ZalbaCutanjeServicePortType")
@XmlSeeAlso({ObjectFactory.class, com.xml.portal.organvlasti.data.dao.tipovi.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ZalbaCutanjeServicePortType {

    @WebMethod(action = "http://zalbacutanje/ws/sendZalbaCutanje")
    public void sendZalbaCutanje(
        @WebParam(partName = "Zalba_cutanje", name = "Zalba_cutanje")
        ZalbaCutanje zalbaCutanje
    );
}
