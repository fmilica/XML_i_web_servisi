package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.w3c.dom.Document;

import resenje.DOMParser;
import resenje.DOMWriter;

public class ResenjeTest {
	
	private String readFilePath;
	private String writeFilePath;
	
	public ResenjeTest(String readFilePath, String writeFilePath) {
		this.readFilePath = readFilePath;
		this.writeFilePath = writeFilePath;
	}
	
	public Document readFile() {
		System.out.println("[INFO] DOM Parser");

		DOMParser handler = new DOMParser();

		// Kreiranje DOM stabla na osnovu XML fajla
		handler.buildDocument(this.readFilePath);

		// Prikaz sadrzaja koriscenjem DOM API-ja 
		handler.printElement();
		
		System.out.println("[INFO] End of file");
		
		return handler.getDocument();
	}
	
	public void writeFile(Document document) {
		System.out.println("\n\n[INFO] DOM Writer");
		
		DOMWriter handler = new DOMWriter(document);

		try {
			handler.transform(new FileOutputStream(new File(this.writeFilePath)));
			System.out.println("[INFO] File succesfully written.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		String readFilePath = "./data/resenje/resenje_gen1.xml";
		String writeFilePath = "./data/marshalled/resenje_marsh.xml";
		ResenjeTest test = new ResenjeTest(readFilePath, writeFilePath);
		Document document = test.readFile();
		test.writeFile(document);
	}
}
