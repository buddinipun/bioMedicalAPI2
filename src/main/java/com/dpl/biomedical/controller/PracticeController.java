package com.dpl.biomedical.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dpl.biomedical.dto.PracticeDto;
import com.dpl.biomedical.entity.Practice;
import com.dpl.biomedical.service.PracticeService;
import com.dpl.biomedical.util.ApplicationConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class PracticeController {
	
	@Autowired
	PracticeService practiceService;
	
	@PostMapping("/practices")
	public ResponseEntity<String> createPracticeRequest(@RequestBody PracticeDto practiceDto) {

		boolean practiceStatus = practiceService.createPracticeRequest(practiceDto.getName(), practiceDto.getEmail(),
				practiceDto.getContactName(), practiceDto.getContactPhone());

		if (practiceStatus) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(500).body("Failure");
		}
	}

	@PutMapping("/practices/{sequence_number}")
	public ResponseEntity<String> submitPracticeRequest(@PathVariable String sequence_number,
			@RequestBody PracticeDto practiceDto) {

		Practice practice = null;
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		ObjectMapper objectMapper = new ObjectMapper();

		List<Practice> Practices = practiceService.getPracticeBySequenceNumber(sequence_number);

		if (Practices != null && Practices.size() > 0) {

			practice = Practices.get(0);

			practice.setContactName(practiceDto.getContactName());
			practice.setContactPhone(practiceDto.getContactPhone());
			practice.setTax_id(practiceDto.getFacilityTaxId());
			practice.setContactEmail(practiceDto.getContactEmail());
			practice.setContactFax(practiceDto.getContactFax());
			practice.setApprovalStatus(ApplicationConstants.STATUS_PENDING);
			practice.setAuditTimestamp(currentTimestamp);
			try {
				String practiceDtoJson = objectMapper.writeValueAsString(practiceDto);

				practice.setPracticeRequestInfo(practiceDtoJson);
			} catch (Exception e) {
				e.printStackTrace();
			}

			boolean practiceStatus = practiceService.savePracticeRequest(practice);

			if (practiceStatus) {
				return ResponseEntity.ok("Success");
			} else {
				return ResponseEntity.status(500).body("Failure");
			}

		} else {
			System.out.print("Practices not found");
			return ResponseEntity.status(500).body("Failure");
		}
	}

	@GetMapping("/practice_list")
	public List<PracticeDto> getAllPractices() {
		return practiceService.getAllPractices();
	}

}
