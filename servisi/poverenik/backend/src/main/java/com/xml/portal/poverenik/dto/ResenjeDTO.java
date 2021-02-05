package com.xml.portal.poverenik.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ResenjeDto")
public class ResenjeDTO {

	@XmlElement(name = "ResenjeId", required = true)
	protected String resenjeId;
	@XmlElement(name = "ZahtevId", required = true)
	protected String zahtevId;
	@XmlElement(name = "ZalbaId", required = true)
	protected String zalbaId;
	@XmlElement(name = "UserEmail", required = true)
	protected String userEmail;

	public ResenjeDTO() {}
	
	public ResenjeDTO(String resenjeId, String zahtevId, String zalbaId, String userEmail) {
		this.resenjeId = resenjeId;
		this.zahtevId = zahtevId;
		this.zalbaId = zalbaId;
		this.userEmail = userEmail;
	}
	
	public String getSadrzaj() {
		return resenjeId;
	}
	public void setSadrzaj(String sadrzaj) {
		this.resenjeId = sadrzaj;
	}
	public String getZahtevId() {
		return zahtevId;
	}
	public void setZahtevId(String zahtevId) {
		this.zahtevId = zahtevId;
	}
	public String getZalbaId() {
		return zalbaId;
	}
	public void setZalbaId(String zalbaId) {
		this.zalbaId = zalbaId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
