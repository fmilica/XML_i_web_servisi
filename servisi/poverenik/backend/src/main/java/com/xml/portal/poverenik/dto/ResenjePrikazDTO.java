package com.xml.portal.poverenik.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ResenjePrikazDto")
public class ResenjePrikazDTO {

	@XmlElement(name = "ResenjeId", required = true)
	protected String resenjeId;
	@XmlElement(name = "ResenjeDatum", required = true)
	protected String resenjeDatum;
	@XmlElement(name = "ZahtevId", required = true)
	protected String zahtevId;
	@XmlElement(name = "ZalbaId", required = true)
	protected String zalbaId;
	@XmlElement(name = "UserEmail", required = true)
	protected String userEmail;
	@XmlElement(name = "ZalilacIme")
	protected String zalilacIme;
	@XmlElement(name = "ZalilacPrezime")
	protected String zalilacPrezime;
	@XmlElement(name = "ZalilacNaziv")
	protected String zalilacNaziv;
	@XmlElement(name = "OptuzeniOrganVlasti", required = true)
	protected String optuzeniOrganVlasti;
	@XmlElement(name = "IshodResenja", required = true)
	protected String ishodResenja;

	public String getResenjeId() {
		return resenjeId;
	}
	public void setResenjeId(String resenjeId) {
		this.resenjeId = resenjeId;
	}
	public String getResenjeDatum() {
		return resenjeDatum;
	}
	public void setResenjeDatum(String resenjeDatum) {
		this.resenjeDatum = resenjeDatum;
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
	public String getZalilacIme() {
		return zalilacIme;
	}
	public void setZalilacIme(String zalilacIme) {
		this.zalilacIme = zalilacIme;
	}
	public String getZalilacPrezime() {
		return zalilacPrezime;
	}
	public void setZalilacPrezime(String zalilacPrezime) {
		this.zalilacPrezime = zalilacPrezime;
	}
	public String getZalilacNaziv() {
		return zalilacNaziv;
	}
	public void setZalilacNaziv(String zalilacNaziv) {
		this.zalilacNaziv = zalilacNaziv;
	}
	public String getOptuzeniOrganVlasti() {
		return optuzeniOrganVlasti;
	}
	public void setOptuzeniOrganVlasti(String optuzeniOrganVlasti) {
		this.optuzeniOrganVlasti = optuzeniOrganVlasti;
	}
	public String getIshodResenja() {
		return ishodResenja;
	}
	public void setIshodResenja(String ishodResenja) {
		this.ishodResenja = ishodResenja;
	}
	
}
