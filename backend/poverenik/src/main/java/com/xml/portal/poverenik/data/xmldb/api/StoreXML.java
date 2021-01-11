package com.xml.portal.poverenik.data.xmldb.api;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.exist.xmldb.EXistResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import com.xml.portal.poverenik.data.dao.GenericXML;
import com.xml.portal.poverenik.data.xmldb.util.AuthenticationUtilities;
import com.xml.portal.poverenik.data.xmldb.util.AuthenticationUtilities.ConnectionProperties;

public class StoreXML {

	private static ConnectionProperties conn;
    
    public static Object store(Object xmlToStore) throws Exception {
    	
    	conn = AuthenticationUtilities.loadProperties();
    	
    	// initialize collection and document identifiers
    	String collectionId = "";
    	String documentId = "";
    	
    	if (xmlToStore instanceof GenericXML) {
	    	String className = xmlToStore.getClass().getSimpleName();
	    	System.out.println("[INFO] " + className);
	    	
	        collectionId = "/db/poverenik/" + className;
			documentId = className;
    	} else {
    		System.out.println("[INFO] Resenje");
    		collectionId = "/db/poverenik/" + "Resenje";
			documentId = "Resenje";
    	}
        
        // initialize database driver
    	System.out.println("[INFO] Loading driver class: " + conn.driver);
    	Class<?> cl = Class.forName(conn.driver);
        
        
        // encapsulation of the database driver functionality
    	Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);
        
        // a collection of Resources stored within an XML database
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();
        
        try { 
        	
        	System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = getOrCreateCollection(collectionId);

            String id = "ID" + (col.getResourceCount() + 1);
            documentId = documentId + id + ".xml";
            
            System.out.println("\t- collection ID: " + collectionId);
        	System.out.println("\t- document ID: " + documentId);
        	
            /*
             *  create new XMLResource with a given id
             *  an id is assigned to the new resource if left empty (null)
             */
        	
            System.out.println("[INFO] Inserting the document: " + documentId);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE); 
            
            Object[] ret;
            if (xmlToStore instanceof GenericXML) {
            	ret = storeJAXB((GenericXML)xmlToStore, res, os, id);
            } else {
            	ret = storeDOM((Document)xmlToStore, res, os, id);
            }
            
            System.out.println("[INFO] Storing the document: " + documentId);
            
            res = (XMLResource)ret[0];
            xmlToStore = ret[1];
        	
            col.storeResource(res);
            System.out.println("[INFO] Done.");
            
        } finally {
            
        	//don't forget to cleanup
            if(res != null) {
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
            
            if(col != null) {
                try { 
                	col.close(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
        return xmlToStore;
    }

    private static Object[] storeJAXB(GenericXML xmlToStore, XMLResource res, OutputStream os, String id) throws JAXBException, XMLDBException {
    	System.out.println("[INFO] Marshalling JAXB instance to an XML document: ");
    	String packageName = xmlToStore.getClass().getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(packageName);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		// set id
		xmlToStore.setId(id);
		
		// marshal the contents to an output stream
		marshaller.marshal(xmlToStore, os);
		
		// link the stream to the XML resource
        res.setContent(os);
        return new Object[]{ res, xmlToStore };
    }
    
    private static Object[] storeDOM(Document document, XMLResource res, OutputStream os, String id) throws TransformerException, XMLDBException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	// Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
		Transformer transformer = transformerFactory.newTransformer();

		// Indentacija serijalizovanog izlaza
		transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		// set id
		NodeList nodeList = document.getChildNodes();
		Element resenje = (Element)nodeList.item(0);
		resenje.setAttribute("id", id);
		
		// Nad "source" objektom (DOM stablo) vrši se transformacija
		DOMSource source = new DOMSource(document);

		// Rezultujući stream (argument metode) 
		StreamResult result = new StreamResult(os);

		// Poziv metode koja vrši opisanu transformaciju
		transformer.transform(source, result);
		
		res.setContent(result.getOutputStream());
		return new Object[]{ res, document };
    }
    
	private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }
    
    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
        
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        
        // create the collection if it does not exist
         if(col == null) {
        
         	if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            
        	String pathSegments[] = collectionUri.split("/");
            
        	if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
            
                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }
                
                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);
                
                if (startCol == null) {
                	
                	// child collection does not exist
                    
                	String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);
                    
                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
                    
                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                    
                    col.close();
                    parentCol.close();
                    
                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }
}
