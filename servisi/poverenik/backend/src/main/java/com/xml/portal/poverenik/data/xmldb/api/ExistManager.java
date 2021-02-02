package com.xml.portal.poverenik.data.xmldb.api;

import java.io.File;

import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

import com.xml.portal.poverenik.data.xmldb.util.AuthenticationUtilities;
import com.xml.portal.poverenik.data.xmldb.util.AuthenticationUtilities.ConnectionProperties;

@Component
public class ExistManager {

	private static ConnectionProperties conn = AuthenticationUtilities.loadProperties();

	public void createConnection() throws Exception {
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
	}

	public void closeConnection(Collection col, XMLResource res) throws XMLDBException {
		if (col != null) {
			col.close();
		}
		if (res != null) {
			((EXistResource) res).freeResources();
		}
	}

	public Collection getOrCreateCollection(String collectionUri, int pathOffset) throws XMLDBException {
		Collection col = DatabaseManager.getCollection(conn.uri+collectionUri, conn.user, conn.password);
		if (col == null) {
			if (collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}
			String pathSegments[] = collectionUri.split("/");
			if (pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();
				for (int i = 0; i <= pathOffset; i++) {
					path.append("/"+pathSegments[i]);
				}
				Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user,
						conn.password);
				if (startCol == null) {
					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath,
							conn.user, conn.password);
					CollectionManagementService service = (CollectionManagementService) parentCol
							.getService("CollectionManagementService", "1.0");
					col = service.createCollection(pathSegments[pathOffset]);
					col.close();
					parentCol.close();
				} else {
					startCol.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathOffset);
		} else {
			return col;
		}
	}

	public void store(String collectionId, String documentId, String filePath) throws Exception {
		createConnection();
		Collection col = null;
		XMLResource res = null;
		try {
			col = getOrCreateCollection(collectionId, 0);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			File f = new File(filePath);

			if (!f.canRead()) {
				return;
			}
			res.setContent(f);
			col.storeResource(res);
		} finally {
			closeConnection(col, res);
		}
	}

	public void storeFromText(String collectionId, String documentId, String xmlString) throws Exception  {
		createConnection();
		Collection col = null;
		XMLResource res = null;
		try {
			col = getOrCreateCollection(collectionId, 0);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			res.setContent(xmlString);
			col.storeResource(res);
		} finally {
			closeConnection(col, res);
		}
	}

	public Collection loadCollection(String collectionId) throws Exception {
		createConnection();
		Collection col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user,
				conn.password);
		col.setProperty(OutputKeys.INDENT, "yes");
		return col;
	}
	
	public XMLResource load(String collectionUri, String documentId) throws Exception  {
		createConnection();
		Collection col = null;
		XMLResource res = null;
		try {
			col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user,
					conn.password);
			col.setProperty(OutputKeys.INDENT, "yes");
			res = (XMLResource) col.getResource(documentId);
			return res;
		} finally {
			if (col != null) {
				col.close();
			}
		}
	}
	
	public XMLResource loadRaw(String collectionUri, String documentId) throws Exception  {
		createConnection();
		Collection col = null;
		XMLResource res = null;
		try {
			col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user,
					conn.password);
			col.setProperty(OutputKeys.INDENT, "yes");
			res = (XMLResource) col.getResource(documentId);
			return res;
		} finally {
			if (col != null) {
				col.close();
			}
		}
	}

	public ResourceSet retrieve(String collectionUri, String xpathExp, String targetNamespace) throws Exception  {
		createConnection();
		Collection col = null;
		ResourceSet result = null;
		try {
			col = DatabaseManager.getCollection(conn.uri + collectionUri);
			col.setProperty(OutputKeys.INDENT, "yes");
			XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			xpathService.setProperty("indent", "yes");
			xpathService.setNamespace("", targetNamespace);
			result = xpathService.query(xpathExp);
		} finally {
			if (col != null) {
				col.close();
			}
		}
		return result;
	}

	public void update(String collectionId, String documentId, String contextXPath, String patch, String update)
			throws Exception {
		createConnection();
		Collection col = null;
		String chosenTemplate = update;

		try {
			col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user,
					conn.password);
			XUpdateQueryService service = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			service.setProperty("indent", "yes");

			System.out.println(String.format(chosenTemplate, contextXPath, patch));
			service.updateResource(documentId, String.format(chosenTemplate, contextXPath, patch));
		} finally {
			if (col != null)
				col.close();
		}
	}
	
	public void append(String collectionId, String documentId, String contextXPath, String patch, String append) throws Exception {
		createConnection();
		Collection col = null;
		String chosenTemplate = append;

		try {
			col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user,
					conn.password);
			XUpdateQueryService service = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			service.setProperty("indent", "yes");

			service.updateResource(documentId, String.format(chosenTemplate, contextXPath, patch));
		} finally {
			if (col != null)
				col.close();
		}
	}
}
