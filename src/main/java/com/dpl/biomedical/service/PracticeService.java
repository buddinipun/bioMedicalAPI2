package com.dpl.biomedical.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpl.biomedical.dto.PracticeDto;
import com.dpl.biomedical.entity.Practice;
import com.dpl.biomedical.repository.PracticeRepository;



@Service
public class PracticeService {
	
	@Autowired
	PracticeRepository practiceRepository;
	
	@Autowired
	EmailService emailService;
	
	public boolean createPracticeRequest(String name, String email, String cName, String cPhone) {
		
	    try {
	    	Practice practice = new Practice();
	    	practice.setName(name);
	    	practice.setEmail(email);
	    	practice.setContactName(cName);
	    	practice.setContactPhone(cPhone);
	    	String sequenceNumber = generateUniqueSequence();
	        practice.setSequence(sequenceNumber);
	    	practiceRepository.save(practice);
	    	
	    	emailService.sendEmail( name, email, "Checkout your Practice", "Your temporary access code is: " + sequenceNumber , sequenceNumber);
	    	
	    	return true;
	    	
	    } catch (Exception ex) {
	    	return false;
	    }	
		
	}
	
	public List<PracticeDto> getAllPractices() {
	    List<Practice> practices = practiceRepository.findAll();
	    return practices.stream()
	            .map(this::convertToPracticeListDto)
	            .collect(Collectors.collectingAndThen(
	                    Collectors.toList(),
	                    list -> {
	                        Collections.reverse(list);
	                        return list;
	                    }
	            ));
	}
	
	private PracticeDto convertToPracticeListDto(Practice practice) {
		PracticeDto dto = new PracticeDto();
        dto.setName(practice.getName());
        dto.setEmail(practice.getEmail());
        dto.setSequence(practice.getSequence());
        dto.setContactName(practice.getContactName());
        dto.setContactPhone(practice.getContactPhone());
        return dto;
    }
	
	private String generateUniqueSequence() {
	    // Using UUID to generate a unique identifier
	    UUID uuid = UUID.randomUUID();
	    String sequence = uuid.toString().replaceAll("-", "").substring(0, 16).toUpperCase();
	    return "QTC" + sequence;
	}

}
