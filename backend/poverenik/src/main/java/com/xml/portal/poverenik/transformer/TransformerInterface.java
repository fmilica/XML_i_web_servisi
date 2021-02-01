package com.xml.portal.poverenik.transformer;

public interface TransformerInterface {

	String generatePDF(String xmlPath, String OUTPUT_FILE) throws Exception;
	
	String generateHTML(String xmlPath, String OUTPUT_FILE);
}
