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
import org.w3c.dom.Document;

import com.xml.portal.poverenik.business.ResenjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;

@Service
@Path("/resenje")
public class ResenjeService {

	@Autowired
	private ResenjeBusiness resenjeBusiness;
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public Response getZahtev(@PathParam("id") String id) {
		Document resenje = resenjeBusiness.getById(id);
		Response r;
		if (resenje == null) {
			Greska greska = new Greska("Resenje sa prosledjenim identifikatorom ne postoji.");
			r = Response.status(404).type("application/xml").entity(greska).build();
		} else {
			r = Response.ok().type("application/xml").entity(resenje).build();	
		}
		return r;
	}
	
    @POST
    public Response addResenje(String xmlResenje, @Context UriInfo uriInfo) {
    	Document stored = resenjeBusiness.create(xmlResenje);
    	String storedId = resenjeBusiness.storeMetadata(stored);
    	
    	Response r;
    	if (stored == null) {
    		Greska greska = new Greska("Greska u kreiranju Resenja.");
			r = Response.status(500).type("application/xml").entity(greska).build();
    	} else {
    		if (storedId != null) {
			    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			    builder.path(storedId);
			    r = Response.created(builder.build()).type("application/xml").entity(stored).build();
    		} else {
    			Greska greska = new Greska("Greska u kreiranju metapodataka Resenja.");
    			r = Response.status(500).type("application/xml").entity(greska).build();
    		}
    	}
        return r;
    }
    
    @GET
    @Path("/generisiHTML/{id}")
	public Response generisiHTML(@PathParam("id") String id) throws Exception {

		String file_path = resenjeBusiness.generateHTML(id);
		
		try {
			
			return Response.ok().build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	
	}
    
    @GET
    @Path("/generisiPDF/{id}")
	public Response generisiPDF(@PathParam("id") String id) throws Exception {

		String file_path = resenjeBusiness.generatePDF(id);
		
		try {
			
			return Response.ok().build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	
	}
}

