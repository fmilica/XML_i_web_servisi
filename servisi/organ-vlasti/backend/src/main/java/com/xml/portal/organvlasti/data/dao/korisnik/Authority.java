package com.xml.portal.organvlasti.data.dao.korisnik;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	String name;
	
	public Authority(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
}
