package com.xml.portal.poverenik.dto.pretraga;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ZalbaCutanjeNaprednaPretragaDto")
public class ZalbaCutanjePretraga {

	@XmlElement(name = "VezanGradjanin", required = true)
	protected String vezanGradjanin;
	@XmlElement(name = "PrimalacNaziv", required = true)
	protected String primalacNaziv;
	@XmlElement(name = "PodnosilacIme", required = true)
	protected String podnosilacIme;
	@XmlElement(name = "PodnosilacPrezime", required = true)
	protected String podnosilacPrezime;
	@XmlElement(name = "PodnosilacNaziv", required = true)
	protected String podnosilacNaziv;
	@XmlElement(name = "Operator", required = true)
	protected String operator;
	
	public List<String> createNazivArray() {
		List<String> array = new ArrayList<String>();
		array.add(this.vezanGradjanin);
		array.add(this.primalacNaziv);
		array.add(this.podnosilacNaziv);
		return array;
	}
	
	public List<String> createImePrezimeArray() {
		List<String> array = new ArrayList<String>();
		array.add(this.vezanGradjanin);
		array.add(this.primalacNaziv);
		array.add(this.podnosilacIme);
		array.add(this.podnosilacPrezime);
		return array;
	}
	
	public List<String> createAllArray() {
		List<String> array = new ArrayList<String>();
		array.add(this.vezanGradjanin);
		array.add(this.primalacNaziv);
		array.add(this.podnosilacIme);
		array.add(this.podnosilacPrezime);
		array.add(this.podnosilacNaziv);
		return array;
	}
	
	public List<String> createCommonArray() {
		List<String> array = new ArrayList<String>();
		array.add(this.vezanGradjanin);
		array.add(this.primalacNaziv);
		return array;
	}

	
	public String getVezanGradjanin() {
		return vezanGradjanin;
	}
	public void setVezanGradjanin(String vezanGradjanin) {
		this.vezanGradjanin = vezanGradjanin;
	}
	public String getPrimalacNaziv() {
		return primalacNaziv;
	}
	public void setPrimalacNaziv(String primalacNaziv) {
		this.primalacNaziv = primalacNaziv;
	}
	public String getPodnosilacIme() {
		return podnosilacIme;
	}
	public void setPodnosilacIme(String podnosilacIme) {
		this.podnosilacIme = podnosilacIme;
	}
	public String getPodnosilacPrezime() {
		return podnosilacPrezime;
	}
	public void setPodnosilacPrezime(String podnosilacPrezime) {
		this.podnosilacPrezime = podnosilacPrezime;
	}
	public String getPodnosilacNaziv() {
		return podnosilacNaziv;
	}
	public void setPodnosilacNaziv(String podnosilacNaziv) {
		this.podnosilacNaziv = podnosilacNaziv;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
