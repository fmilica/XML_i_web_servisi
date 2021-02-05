package com.xml.portal.organvlasti.data.dao.izvestaj;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.xml.portal.organvlasti.data.dao.obavestenje.Obavestenje;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "izvestaj"
})
@XmlRootElement(name = "Lista_izvestaj")
public class ListaIzvestaj {

	@XmlElement(name = "Izvestaj")
    protected List<Izvestaj> izvestaj;

	public List<Izvestaj> getIzvestaj() {
		if (izvestaj == null) {
			izvestaj = new ArrayList<Izvestaj>();
		}
		return izvestaj;
	}
	
	public void setIzvestaj(List<Izvestaj> izvestaj) {
		this.izvestaj = izvestaj;
	}
}
