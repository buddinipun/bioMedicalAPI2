package com.dpl.biomedical.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
	    	practice.setContactEmail(email);
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
	
	public boolean savePracticeRequest(Practice practice) {

		try {
			practiceRepository.save(practice);

//	    	emailService.sendEmail( name, email, "Checkout your Practice", "Your temporary access code is: " + sequenceNumber , sequenceNumber);

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
	
	public List<Practice> getPracticeBySequenceNumber(String sequenceNumber) {
		return practiceRepository.getPracticeBySequenceNumber(sequenceNumber);
	}
	
	public Optional<Practice> getPracticeById(Long practice_id) {
		return practiceRepository.findById(practice_id);
	}
	
	private PracticeDto convertToPracticeListDto(Practice practice) {
		PracticeDto dto = new PracticeDto();
		dto.setId(practice.getId());
        dto.setName(practice.getName());
        dto.setEmail(practice.getContactEmail());
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
