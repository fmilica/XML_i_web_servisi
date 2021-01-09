package com.xml.portal.poverenik.data.dao.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "greska")
public class Greska {

    protected String razlog;
	
    public Greska() {}
    
    public Greska(String razlog) {
    	this.razlog = razlog;
    }
	
    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String value) {
        this.razlog = value;
    }
}
