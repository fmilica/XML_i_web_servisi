package com.xml.portal.poverenik.service.soap.zalba_odbijanje;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-05T11:45:01.711+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://zalbaodbijanje", name = "ZalbaOdbijenServicePortType")
@XmlSeeAlso({com.xml.portal.poverenik.data.dao.odgovor.ObjectFactory.class, com.xml.portal.poverenik.data.dao.tipovi.ObjectFactory.class, com.xml.portal.poverenik.data.dao.zalba_odbijanje.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ZalbaOdbijenServicePortType {

    @WebMethod(action = "http://zalbaodbijanje/ws/sendOdgovor")
    public void sendOdgovor(
        @WebParam(partName = "Odgovor", name = "Odgovor")
        com.xml.portal.poverenik.data.dao.odgovor.Odgovor odgovor
    );

    @WebMethod(action = "http://zalbaodbijanje/ws/sendZalbaOdbijen")
    public void sendZalbaOdbijen(
        @WebParam(partName = "ZalbaOdbijanje", name = "ZalbaOdbijanje")
        com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje zalbaOdbijanje
    );
}
