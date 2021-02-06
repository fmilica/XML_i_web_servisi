package com.xml.portal.organvlasti.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OdgovorDTO")
public class OdgovorDTO {
	@XmlElement(name = "id_zalbe", required = true)
	protected String id_zalbe;
	@XmlElement(name = "izjasnjenje", required = true)
	protected String izjasnjenje;
	public String getId_zalbe() {
		return id_zalbe;
	}
	public void setId_zalbe(String id_zalbe) {
		this.id_zalbe = id_zalbe;
	}
	public String getIzjasnjenje() {
		return izjasnjenje;
	}
	public void setIzjasnjenje(String izjasnjenje) {
		this.izjasnjenje = izjasnjenje;
	}
	
	
}
