package com.xml.portal.poverenik.data.dao.korisnik;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accessToken",
    "expiresIn"
})
@XmlRootElement(name = "korisnik_token")
public class KorisnikToken {

	@XmlElement(required = true)
	private String accessToken;
	
	@XmlElement(required = true)
	private Long expiresIn;

	public KorisnikToken(String accessToken, Long expiresIn) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
}
