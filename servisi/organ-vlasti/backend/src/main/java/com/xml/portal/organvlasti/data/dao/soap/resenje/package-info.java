

@XmlSchema( 
		   namespace = "http://www.w3.org/ns/rdfa#",
		   elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
		   xmlns = {
			@javax.xml.bind.annotation.XmlNs(prefix = "", namespaceURI="http://www.w3.org/ns/rdfa#"),
			@javax.xml.bind.annotation.XmlNs(prefix = "res", namespaceURI="http://resenje"),
  			@javax.xml.bind.annotation.XmlNs(prefix="tipovi", namespaceURI="http://tipovi"),
  			@javax.xml.bind.annotation.XmlNs(prefix="xsi", namespaceURI="http://www.w3.org/2001/XMLSchema-instance"),
  			@javax.xml.bind.annotation.XmlNs(prefix="pred", namespaceURI="http://www.xml.com/predicate/"),
  			}
    )
package com.xml.portal.organvlasti.data.dao.soap.resenje;

import javax.xml.bind.annotation.XmlSchema;