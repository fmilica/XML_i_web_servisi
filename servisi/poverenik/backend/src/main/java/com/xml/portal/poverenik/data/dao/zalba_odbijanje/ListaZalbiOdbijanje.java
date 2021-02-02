package com.xml.portal.poverenik.data.dao.zalba_odbijanje;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zalbaOdbijanje"
})
@XmlRootElement(name = "Lista_zalbi_odbijanje")
public class ListaZalbiOdbijanje {
	
	@XmlElement(name = "Zalba_odbijanje")
    protected List<ZalbaOdbijanje> zalbaOdbijanje;

	public List<ZalbaOdbijanje> getObavestenje() {
		if (zalbaOdbijanje == null) {
			zalbaOdbijanje = new ArrayList<ZalbaOdbijanje>();
		}
		return zalbaOdbijanje;
	}
	
	public void setZalbaOdbijanje(List<ZalbaOdbijanje> zalbaOdbijanje) {
		this.zalbaOdbijanje = zalbaOdbijanje;
	}
}