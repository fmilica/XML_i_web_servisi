package com.xml.portal.poverenik.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UserRegisterDto")
public class KorisnikRegistracijaDTO {
	
	@XmlElement(name = "email", required = true)
	protected String email;
	@XmlElement(name = "password", required = true)
	protected String lozinka;
	@XmlElement(name = "name", required = true)
	protected String ime;
	@XmlElement(name = "surname", required = true)
	protected String prezime;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
