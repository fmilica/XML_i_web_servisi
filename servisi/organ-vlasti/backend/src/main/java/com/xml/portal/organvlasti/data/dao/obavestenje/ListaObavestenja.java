package com.xml.portal.organvlasti.data.dao.obavestenje;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "obavestenje"
})
@XmlRootElement(name = "Lista_obavestenje")
public class ListaObavestenja {
	
	@XmlElement(name = "Obavestenje")
    protected List<Obavestenje> obavestenje;

	public List<Obavestenje> getObavestenje() {
		if (obavestenje == null) {
			obavestenje = new ArrayList<Obavestenje>();
		}
		return obavestenje;
	}
	
	public void setObavestenje(List<Obavestenje> obavestenje) {
		this.obavestenje = obavestenje;
	}
}