package com.xml.portal.organvlasti.data.metadatadb.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.xml.portal.organvlasti.data.dao.GenericXML;
import com.xml.portal.organvlasti.data.dao.NSPrefixMapper;
import com.xml.portal.organvlasti.data.dao.zahtev.Zahtev;
import com.xml.portal.organvlasti.data.metadatadb.util.AuthenticationUtilities;
import com.xml.portal.organvlasti.data.metadatadb.util.AuthenticationUtilities.ConnectionProperties;
import com.xml.portal.organvlasti.data.metadatadb.util.MetadataExtractor;
import com.xml.portal.organvlasti.data.metadatadb.util.SparqlUtil;

public class StoreMetadata {

	private static String SPARQL_NAMED_GRAPH_URI = "";
	
	private static ConnectionProperties conn;
	
	public static void main(String[] args) throws Exception {
		
		
		JAXBContext context = JAXBContext.newInstance(Zahtev.class.getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		//SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		//Schema schema = schemaFactory.newSchema(new File("src/main/resources/data/schema/zahtev/zahtev.xsd"));
        
		//unmarshaller.setSchema(schema);

		Zahtev zahtev = (Zahtev) 
				unmarshaller.unmarshal(new File("src/main/resources/data/zahtev_gen1.xml"));
		
		store(zahtev);
	}
	
	public static void store(Object xmlToStore) throws IOException, SAXException, TransformerException, JAXBException {
		conn = AuthenticationUtilities.loadProperties();
		
		String rdfFilePath = "src/main/resources/data/gen/temp";
		InputStream xmlContent = null;
		
		if (xmlToStore instanceof GenericXML) {
			xmlContent = convertToInputStream((GenericXML) xmlToStore);
			SPARQL_NAMED_GRAPH_URI = "/organvlasti/" + xmlToStore.getClass().getSimpleName();
		} else {
			xmlContent = convertToInputStream((Document) xmlToStore);
			SPARQL_NAMED_GRAPH_URI = "/organvlasti/Resenje";
		}
		
		// Automatic extraction of RDF triples from XML file
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		
		System.out.println("[INFO] Extracting metadata from RDFa attributes...");
		metadataExtractor.extractMetadata(
				xmlContent, 
				new FileOutputStream(new File(rdfFilePath)));
		xmlContent.close();
		
		// Loading a default model with extracted metadata
		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		model.write(out, SparqlUtil.NTRIPLES);
		
		System.out.println("[INFO] Extracted metadata as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);
		
		// Writing the named graph
		System.out.println("[INFO] Populating named graph \"" + SPARQL_NAMED_GRAPH_URI + "\" with extracted metadata.");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));
		System.out.println(sparqlUpdate);
		
		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
		
		// Read the triples from the named graph
		System.out.println();
		System.out.println("[INFO] Retrieving triples from RDF store.");
		System.out.println("[INFO] Using \"" + SPARQL_NAMED_GRAPH_URI + "\" named graph.");

		System.out.println("[INFO] Selecting the triples from the named graph \"" + SPARQL_NAMED_GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		
		// Query the collection, dump output response as XML
		ResultSet results = query.execSelect();
		
		ResultSetFormatter.out(System.out, results);
		
		query.close() ;
		
		System.out.println("[INFO] End.");
	}
	
	public static String generisiRDF(Object xmlToGenerate) throws Exception {
		conn = AuthenticationUtilities.loadProperties();
		InputStream xmlContent = null;
		
		if (xmlToGenerate instanceof GenericXML) {
			xmlContent = convertToInputStream((GenericXML) xmlToGenerate);
		} else {
			xmlContent = convertToInputStream((Document) xmlToGenerate);
		}
		
		String rdfFilePath = "src/main/resources/data/gen/rdfExportTemp";
		// Automatic extraction of RDF triples from XML file
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		
		System.out.println("[INFO] Extracting metadata from RDFa attributes...");
		metadataExtractor.extractMetadata(
				xmlContent, 
				new FileOutputStream(new File(rdfFilePath)));
		xmlContent.close();
		return rdfFilePath;
	}
	
	// Helper methods
	private static InputStream convertToInputStream(GenericXML xmlToStore) throws IOException, JAXBException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JAXBContext context = JAXBContext.newInstance(xmlToStore.getClass().getPackage().getName());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(xmlToStore, out);
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private static InputStream convertToInputStream(Document xmlToStore) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(xmlToStore);
		Result outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
