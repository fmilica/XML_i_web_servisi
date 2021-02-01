package com.xml.portal.poverenik.data.dao.zahtev;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zahtev"
})
@XmlRootElement(name = "Lista_zahteva")
public class ListaZahteva {
	
	@XmlElement(name = "Zahtev")
    protected List<Zahtev> zahtev;

	public List<Zahtev> getZahtev() {
		if (zahtev == null) {
			zahtev = new ArrayList<Zahtev>();
		}
		return zahtev;
	}
	
	public void setZahtev(List<Zahtev> zahtev) {
		this.zahtev = zahtev;
	}
}
