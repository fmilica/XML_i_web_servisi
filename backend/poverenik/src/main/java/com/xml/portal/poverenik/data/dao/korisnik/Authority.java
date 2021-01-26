package com.xml.portal.poverenik.data.dao.korisnik;


import org.springframework.security.core.GrantedAuthority;


public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	String name;
	
	@Override
	public String getAuthority() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authority(String name) {
		super();
		this.name = name;
	}

	public Authority() {
		super();
	}

}
