package com.dpl.biomedical.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpl.biomedical.entity.Practice;
import com.dpl.biomedical.repository.PracticeRepository;



@Service
public class PracticeService {
	
	@Autowired
	PracticeRepository practiceRepository;
	
	@Autowired
	EmailService emailService;
	
	public boolean createPracticeRequest(String name, String email) {
		
	    try {
	    	Practice practice = new Practice();
	    	practice.setName(name);
	    	practice.setEmail(email);
	    	String sequenceNumber = generateUniqueSequence();
	        practice.setSequence(sequenceNumber);
	    	practiceRepository.save(practice);
	    	
	    	emailService.sendEmail( name, email, "Checkout your Practice", "Your temporary access code is: " + sequenceNumber , sequenceNumber);
	    	
	    	return true;
	    	
	    } catch (Exception ex) {
	    	return false;
	    }	
		
	}
	
	private String generateUniqueSequence() {
	    // Using UUID to generate a unique identifier
	    UUID uuid = UUID.randomUUID();
	    String sequence = uuid.toString().replaceAll("-", "").substring(0, 16).toUpperCase();
	    return "QTC" + sequence;
	}

}
