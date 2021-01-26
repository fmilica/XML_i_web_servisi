package com.xml.portal.poverenik.business;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "email",
    "lozinka"
})
@XmlRootElement(name = "korisnik_dto")
public class KorisnikDTO {

	@XmlElement(required = true)
	@NotEmpty(message = "Email cannot be null or empty.")
    @Email(message = "Email format is not valid.")
    protected String email;
	
    @XmlElement(required = true)
    @NotEmpty(message = "Lozinka cannot be null or empty.")
    protected String lozinka;

	public KorisnikDTO() {
		super();
	}

	public KorisnikDTO(
			@NotEmpty(message = "Email cannot be null or empty.") @Email(message = "Email format is not valid.") String email,
			@NotEmpty(message = "Lozinka cannot be null or empty.") String lozinka) {
		super();
		this.email = email;
		this.lozinka = lozinka;
	}

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
}
