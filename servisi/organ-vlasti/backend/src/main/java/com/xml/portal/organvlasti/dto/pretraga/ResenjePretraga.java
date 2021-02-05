package com.xml.portal.organvlasti.dto.pretraga;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ResenjeNaprednaPretragaDto")
public class ResenjePretraga {

	@XmlElement(name = "VezanGradjanin", required = true)
	protected String vezanGradjanin;
	@XmlElement(name = "OptuzeniNaziv", required = true)
	protected String optuzeniNaziv;
	@XmlElement(name = "Operator", required = true)
	protected String operator;
	
	public List<String> createAllArray() {
		List<String> array = new ArrayList<String>();
		array.add(this.vezanGradjanin);
		array.add(this.optuzeniNaziv);
		return array;
	}
	
	public String getVezanGradjanin() {
		return vezanGradjanin;
	}
	public void setVezanGradjanin(String vezanGradjanin) {
		this.vezanGradjanin = vezanGradjanin;
	}
	public String getOptuzeniNaziv() {
		return optuzeniNaziv;
	}
	public void setOptuzeniNaziv(String optuzeniNaziv) {
		this.optuzeniNaziv = optuzeniNaziv;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
