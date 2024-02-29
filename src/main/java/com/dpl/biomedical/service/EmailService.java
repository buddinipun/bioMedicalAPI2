package com.dpl.biomedical.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


@Service
public class EmailService {
	
	@Value("${baseURL}")
	private String base_url;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendEmail(String name, String to, String subject, String text , String sequenceNumber) throws MessagingException {
		
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	
		String emailHost = "smtp.mailersend.net";
		String emailPort = "587";
		String emailUsername = "MS_oXvDSz@illinoiscourtservices.net";
		String emailPassword = "oSOfaurQRCAwHm1F";
		String emailFrom = "no-reply@illinoiscourtservices.net";
		
    	mailSender.setHost(emailHost);
    	mailSender.setPort(Integer.parseInt(emailPort));
    	mailSender.setUsername(emailUsername);
    	mailSender.setPassword(emailPassword);
    	 
    	Properties properties = new Properties();
    	properties.setProperty("mail.smtp.auth", "true");
    	properties.setProperty("mail.smtp.starttls.enable", "true");
    	 
    	mailSender.setJavaMailProperties(properties);
    	
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
       String verifyURL = base_url + "/practice_list/" + sequenceNumber;
        
     // Create a model with variables
     		Map<String, Object> model = new HashMap<String, Object>();
     		model.put("name", name);
     		model.put("verifyURL", verifyURL);
     		String template = "singin_practice_email_template";
     		// create email body
     		Context context = new Context();
     		context.setVariables(model);

     		String body = templateEngine.process(template, context);
     //  String htmlContent = subject.concat("<p>" + text + "</p><p>Click <a href=\"" + verifyURL + "\">here</a> to verify.</p>");

        helper.setTo(to);
        
        helper.setText(body, true);
        helper.setSubject(subject);
        helper.setFrom(emailFrom);
        mailSender.send(message);
       
    }


}
