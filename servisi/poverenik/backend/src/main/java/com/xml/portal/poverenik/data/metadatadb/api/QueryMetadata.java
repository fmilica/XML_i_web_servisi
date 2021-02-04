package com.xml.portal.poverenik.data.metadatadb.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;

import com.xml.portal.poverenik.data.metadatadb.util.AuthenticationUtilities;
import com.xml.portal.poverenik.data.metadatadb.util.AuthenticationUtilities.ConnectionProperties;
import com.xml.portal.poverenik.data.metadatadb.util.FileUtil;
import com.xml.portal.poverenik.data.metadatadb.util.SparqlUtil;

public class QueryMetadata {

	private static ConnectionProperties conn;
	
	public static void main(String[] args) throws IOException {
		//query("/poverenik/Zahtev", 
		//		"src/main/resources/data/sparql/napredna/ksenija.rq", 
		//		new ArrayList<String>());
		generisiJSON("/poverenik/Zahtev", "http://zahtev", "bf1c000a-20fb-49d6-98e1-d070e63c9a3f");
	}
	
	public static List<String> query(String graphUri, String sparqlFilePath, String queryParam) throws IOException {
		conn = AuthenticationUtilities.loadProperties();
		
		// Querying the named graph with a referenced SPARQL query
		System.out.println("[INFO] Loading SPARQL query from file \"" + sparqlFilePath + "\"");
		String sparqlQuery = String.format(FileUtil.readFile(sparqlFilePath, StandardCharsets.UTF_8), 
				conn.dataEndpoint + graphUri, queryParam);
		
		System.out.println(sparqlQuery);
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the SPARQL endpoint, iterate over the result set...
		System.out.println("[INFO] Showing the results for SPARQL query using the result handler.\n");
		ResultSet results = query.execSelect();

		String varName;
		RDFNode varValue;

		List<String> result = new ArrayList<String>();
		while (results.hasNext()) {

			// A single answer from a SELECT query
			QuerySolution querySolution = results.next();
			Iterator<String> variableBindings = querySolution.varNames();

			// Retrieve variable bindings
			while (variableBindings.hasNext()) {

				varName = variableBindings.next();
				varValue = querySolution.get(varName);
				
				result.add(varValue.toString().substring(varValue.toString().lastIndexOf("/")+1));
			}
		}

		// Issuing the same query once again...

		// Create a QueryExecution that will access a SPARQL service over HTTP
		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the collection, dump output response as XML
		System.out.println("[INFO] Showing the results for SPARQL query in native SPARQL XML format.\n");
		results = query.execSelect();

		//ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");
		return result;
	}
	
	public static String generisiJSON(String graphUri, String targetNamespace, String id) throws IOException {
		conn = AuthenticationUtilities.loadProperties();
		
		String jsonFilePath = "src/main/resources/data/gen/jsonExportTemp";
		
		String condition = "<" + targetNamespace + "/" + id + "> ?p ?o";
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + graphUri, condition);
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		ResultSetFormatter.outputAsJSON(new FileOutputStream(new File(jsonFilePath)), results);
		query.close();
		return jsonFilePath;
	}
}
