package com.xml.portal.organvlasti.data.dao.zalba_cutanje;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zalbaCutanje"
})
@XmlRootElement(name = "Lista_zalbi_cutanje")
public class ListaZalbiCutanje {
	
	@XmlElement(name = "Zalba_cutanje")
    protected List<ZalbaCutanje> zalbaCutanje;

	public List<ZalbaCutanje> getObavestenje() {
		if (zalbaCutanje == null) {
			zalbaCutanje = new ArrayList<ZalbaCutanje>();
		}
		return zalbaCutanje;
	}
	
	public void setZalbaCutanje(List<ZalbaCutanje> zalbaCutanje) {
		this.zalbaCutanje = zalbaCutanje;
	}
}