package com.xml.portal.poverenik.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.xml.portal.poverenik.data.dao.odgovor.Odgovor;
import com.xml.portal.poverenik.data.repository.IzjasnjenjeRepository;

public class IzjasnjenjeBusiness {

	@Autowired
	private IzjasnjenjeRepository izjasnjenjerRepository;
	
	public Odgovor getById(String idZalbe) {
		Odgovor loaded = null;
		try {
			loaded = izjasnjenjerRepository.findById(idZalbe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loaded;
	}
	
	public String crete(Odgovor odgovor) {
		String id = izjasnjenjerRepository.save(odgovor);
		return id;
	}
}
