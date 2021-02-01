package com.xml.portal.poverenik.data.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/** 
 * Klasa koja definiše custom pravila mapiranja 
 * namespaceova na prefikse.
 *
 */
@XmlTransient
public class NSPrefixMapper extends NamespacePrefixMapper {

	private HashMap<String, String> mappings;

	public NSPrefixMapper() { 
		
		// Inicijalizacija mape prefiksa
		mappings = new LinkedHashMap<String, String>(); 
		setDefaultMappings(); 
	}

	protected void setDefaultMappings() { 
		
		// Poništava prethodna mapiranja
		clear();

		// Za default namespace prefiks postaviti na "" 
		addMapping("http://www.w3.org/ns/rdfa#", "");
		addMapping("http://www.xml.com/predicate/", "pred");
		addMapping("http://www.w3.org/2001/XMLSchema-instance", "xsi");
		addMapping("http://zahtev", "zahtev");
		addMapping("http://obavestenje", "obv");
		addMapping("http://zalba_odbijen", "zoz");
		addMapping("http://zalbacutanje", "zoc");
		addMapping("http://resenje", "res");
		addMapping("http://tipovi", "tipovi");
	}

	public void addMapping(String uri, String prefix){
		mappings.put(uri, prefix);
	} 
	
	public String getMapping(String uri){
		return (String)mappings.get(uri);
	} 
	public HashMap<String, String> getMappings(){
		return mappings;
	} 
	public void clear(){
		mappings.clear();
	}

	/**
	 * Metoda vraća preferirani prefiks za zadati namespace. 
	 */
	public String getPreferredPrefix(String namespaceURI, String suggestion, boolean requirePrefix) { 
		String preferredPrefix = getMapping(namespaceURI); 
		if(preferredPrefix != null)
			return preferredPrefix;
		return suggestion; 
	} 

}
