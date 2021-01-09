package com.xml.portal.poverenik.data.xmldb.api;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.portal.poverenik.data.dao.GenericXML;
import com.xml.portal.poverenik.data.xmldb.util.AuthenticationUtilities;
import com.xml.portal.poverenik.data.xmldb.util.AuthenticationUtilities.ConnectionProperties;

public class RetrieveXML {
    
	private static ConnectionProperties conn;
	
    public static Object retrieve(Class xmlClass, String documentId) throws Exception {
       
    	conn = AuthenticationUtilities.loadProperties();
    	
    	// initialize collection and document identifiers
    	String collectionId = "";
    	
    	if (xmlClass != null) {
	    	String className = xmlClass.getSimpleName();
	    	System.out.println("[INFO] " + className);
	    	
	        collectionId = "/db/poverenik/" + className;
	        documentId = className + documentId + ".xml";
    	} else {
    		System.out.println("[INFO] Resenje");
    		collectionId = "/db/poverenik/" + "Resenje";
    		documentId = "Resenje" + documentId + ".xml";
    	}
        
        // initialize database driver
    	System.out.println("[INFO] Loading driver class: " + conn.driver);
        Class<?> cl = Class.forName(conn.driver);
        
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        XMLResource res = null;
        Object loaded = null;
        
        try {    
            // get the collection
        	System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");
            
            System.out.println("[INFO] Retrieving the document: " + documentId);
            res = (XMLResource)col.getResource(documentId);
            
            if(res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' could not be found!");
            } else {
            	
            	if (collectionId != "/db/poverenik/Resenje") {
            		loaded = retrieveJAXB(xmlClass, res);
            	} else {
            		loaded = retrieveDOM(res);
            	}
            	System.out.println(loaded);
            }
        } finally {
            //don't forget to clean up!
            
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
        return loaded;
    }

	private static GenericXML retrieveJAXB(Class<GenericXML> xmlClass, XMLResource res) throws JAXBException, XMLDBException {
    	System.out.println("[INFO] Binding XML resouce to an JAXB instance: ");
        JAXBContext context = JAXBContext.newInstance(xmlClass.getPackageName());
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		GenericXML loaded = (GenericXML) unmarshaller.unmarshal(res.getContentAsDOM());
		
		System.out.println("[INFO] Showing the document as JAXB instance: ");
		return loaded;
    }
    
    private static Object retrieveDOM(XMLResource res) throws XMLDBException {
    	System.out.println("[INFO] Showing the document: ");
    	return res.getContent();
    }
}
