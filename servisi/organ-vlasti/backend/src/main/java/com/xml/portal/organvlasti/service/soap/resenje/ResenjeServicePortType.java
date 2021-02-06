package com.xml.portal.organvlasti.service.soap.resenje;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.w3c.dom.Document;

import com.xml.portal.organvlasti.data.dao.resenje.DOMParser;

//import resenje.Resenje;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-06T08:44:31.393+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://resenje", name = "ResenjeServicePortType")
@XmlSeeAlso({DOMParser.class, com.xml.portal.organvlasti.data.dao.tipovi.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ResenjeServicePortType {

    @WebMethod(action = "http://resenje/ws/receiveResenje")
    public void receiveResenje(
        @WebParam(partName = "Resenje", name = "Resenje")
        Document resenje
    );
}
