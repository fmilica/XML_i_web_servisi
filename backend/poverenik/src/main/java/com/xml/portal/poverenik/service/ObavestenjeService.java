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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.portal.poverenik.business.ObavestenjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.obavestenje.Obavestenje;

@RestController()
//@RequestMapping(value = "/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
@Path("/obavestenje")
@CrossOrigin()
public class ObavestenjeService {

	@Autowired
	private ObavestenjeBusiness obavestenjeBusiness;
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
//	@GetMapping(path = "/{id}")
	public Response getObavestenje(@PathParam("id") String id) {
		Obavestenje obavestenje = obavestenjeBusiness.getById(id);
		Response r;
		if (obavestenje == null) {
			Greska greska = new Greska("Obavestenje sa prosledjenim identifikatorom ne postoji.");
			r = Response.status(404).type("application/xml").entity(greska).build();
		} else {
//			return Response.ok(entity).header("Content-Disposition", "attachment; filename=\"" + fileName + "\"").build()

			r = Response.ok(obavestenje).type("application/xml").header("Access-Control-Allow-Origin", "*").build();	
		}
		return r;
	}
	
    @POST
    public Response addObavestenje(Obavestenje zahtev, @Context UriInfo uriInfo) {
    	Obavestenje stored = obavestenjeBusiness.create(zahtev);
    	boolean storedMetadata = obavestenjeBusiness.storeMetadata(zahtev);
    	
    	Response r;
    	if (stored == null) {
    		Greska greska = new Greska("Greska u kreiranju Obavestenja.");
			r = Response.status(500).type("application/xml").entity(greska).build();
    	} else {
    		if (storedMetadata) {
			    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			    builder.path(stored.getId());
			    r = Response.created(builder.build()).type("application/xml").entity(stored).build();
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Obavestenja.");
    			r = Response.status(500).type("application/xml").entity(greska).build();
    		}
    	}
        return r;
    }
}
