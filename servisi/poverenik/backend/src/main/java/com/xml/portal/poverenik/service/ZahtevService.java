package com.xml.portal.poverenik.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.portal.poverenik.business.ZahtevBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zahtev.Zahtev;

@Service
@Path("/zahtev")
public class ZahtevService {

	@Autowired
	private ZahtevBusiness zahtevBusiness;
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public Response getZahtev(@PathParam("id") String id) {
		Zahtev zahtev = zahtevBusiness.getById(id);
		Response r;
		if (zahtev == null) {
			Greska greska = new Greska("Zahtev sa prosledjenim identifikatorom ne postoji.");
			r = Response.status(404).type("application/xml").entity(greska).build();
		} else {
			r = Response.ok().type("application/xml").entity(zahtev).build();	
		}
		return r;
	}
	
    @POST
    public Response addZahtev(Zahtev zahtev, @Context UriInfo uriInfo) {
    	Zahtev stored = zahtevBusiness.create(zahtev);
    	boolean storedMetadata = zahtevBusiness.storeMetadata(zahtev);
    	
    	Response r;
    	if (stored == null) {
    		Greska greska = new Greska("Greska u kreiranju Zahteva.");
			r = Response.status(500).type("application/xml").entity(greska).build();
    	} else {
    		if (storedMetadata) {
			    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			    builder.path(stored.getId());
			    r = Response.created(builder.build()).type("application/xml").entity(stored).build();
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Zahteva.");
    			r = Response.status(500).type("application/xml").entity(greska).build();
    		}
    	}
        return r;
    }
}
