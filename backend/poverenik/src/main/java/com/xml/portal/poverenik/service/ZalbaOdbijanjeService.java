package com.xml.portal.poverenik.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.portal.poverenik.business.ZalbaOdbijanjeBusiness;
import com.xml.portal.poverenik.data.dao.exception.Greska;
import com.xml.portal.poverenik.data.dao.zalba_odbijanje.ZalbaOdbijanje;

@Service
@Path("/zalba-odbijanje")
public class ZalbaOdbijanjeService {

	@Autowired
	private ZalbaOdbijanjeBusiness zalbaOdbijanjeBusiness;
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public Response getZalbaOdbijanje(@PathParam("id") String id) {
		ZalbaOdbijanje zalbaOdbijanje = zalbaOdbijanjeBusiness.getById(id);
		Response r;
		if (zalbaOdbijanje == null) {
			Greska greska = new Greska("Zalba zbog odbijanja sa prosledjenim identifikatorom ne postoji.");
			r = Response.status(404).type("application/xml").entity(greska).build();
		} else {
			r = Response.ok().type("application/xml").entity(zalbaOdbijanje).build();	
		}
		return r;
	}
	
    @POST
    public Response addZalbaOdbijanje(ZalbaOdbijanje zalbaOdbijanje, @Context UriInfo uriInfo) {
    	ZalbaOdbijanje stored = zalbaOdbijanjeBusiness.create(zalbaOdbijanje);
    	boolean storedMetadata = zalbaOdbijanjeBusiness.storeMetadata(zalbaOdbijanje);
    	
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
    			Greska greska = new Greska("Greska u kreiranju metapodataka Zalbe zbog odbijanja.");
    			r = Response.status(500).type("application/xml").entity(greska).build();
    		}
    	}
        return r;
    }
    
    @GET
    @Path("/generisiHTML/{id}")
	public Response generisiHTML(@PathParam("id") String id) throws Exception {

		String path = zalbaOdbijanjeBusiness.generateHTML(id);
		
		try {
			File file = new File(path);
			FileInputStream stream = new FileInputStream(file);
			return Response.ok().entity(IOUtils.toByteArray(stream)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	
	}
    
    @GET
    @Path("/generisiPDF/{id}")
	public Response generisiPDF(@PathParam("id") String id) throws Exception {

		String path = zalbaOdbijanjeBusiness.generatePDF(id);
		
		try {
			File file = new File(path);
			FileInputStream stream = new FileInputStream(file);
			return Response.ok().entity(IOUtils.toByteArray(stream)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	
	}
}
