package com.xml.portal.eposta.endpoint;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.sun.mail.util.BASE64DecoderStream;
import com.xml.portal.eposta.data.dao.pismo.Pismo;

@Endpoint
public class PostaEndpoint {
	private static final String NAMESPACE_URI = "http://pismo";
	
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Autowired
    Environment env;
	
	public PostaEndpoint() {}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "pismo")
	public void posaljiPismo(@RequestPayload Pismo pismo) throws MessagingException, IOException {
		System.out.println("Usao");
		mailSender.setUsername(env.getProperty("spring.mail.username"));
		mailSender.setPassword(env.getProperty("spring.mail.password"));
	
		MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "utf-8");
        
        email.setTo(pismo.getPrimalac());
        email.setSubject(pismo.getNaslov());
        email.setText(pismo.getSadrzaj());
        if(pismo.getTipPriloga().equals("pdf")) {
        	 ByteArrayDataSource dataSource = new ByteArrayDataSource(pismo.getPrilog(), "application/pdf");
             email.addAttachment("attachment."+pismo.getTipPriloga(), dataSource);
        }else if (pismo.getTipPriloga().equals("html")) {
        	ByteArrayDataSource dataSource = new ByteArrayDataSource(pismo.getPrilog(), "text/html");
            email.addAttachment("attachment."+pismo.getTipPriloga(), dataSource);
		}
       
        mailSender.send(mimeMessage);
        
	}
}
