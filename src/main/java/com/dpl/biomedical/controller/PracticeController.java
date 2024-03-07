package com.dpl.biomedical.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class PracticeController {
	
	@Autowired
	PracticeService practiceService;
	
	@PostMapping("/practices")
	public ResponseEntity<String> createPracticeRequest(@RequestBody PracticeDto practiceDto) {

		boolean practiceStatus = practiceService.createPracticeRequest(practiceDto.getName(), practiceDto.getEmail());

		if (practiceStatus) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(500).body("Failure");
		}
	}

	@PutMapping("/practices/register/{sequence_number}")
	public ResponseEntity<String> submitPracticeRequest(@PathVariable String sequence_number,
			@RequestBody PracticeDto practiceDto) {

		Practice practice = null;
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		ObjectMapper objectMapper = new ObjectMapper();

		List<Practice> Practices = practiceService.getPracticeBySequenceNumber(sequence_number);

		if (Practices != null && Practices.size() > 0) {

			practice = Practices.get(0);

			practice.setPaybleContact(practiceDto.getAccountPayableData().getApContact());
			practice.setPayblePhone(practiceDto.getAccountPayableData().getApContactPhone());
			practice.setPaybleEmail(practiceDto.getAccountPayableData().getApContactEmail());
			practice.setContactName(practiceDto.getPracticeInformationData().getContactName());
			practice.setContactPhone(practiceDto.getPracticeInformationData().getContactPhone());
			practice.setContactEmail(practiceDto.getPracticeInformationData().getContactEmail());
			practice.setContactFax(practiceDto.getPracticeInformationData().getContactFax());
			practice.setPtan(practiceDto.getPracticeInformationData().getFacilityPTAN());
			practice.setNpi(practiceDto.getPracticeInformationData().getFacilityNPI());
			practice.setTax_id(practiceDto.getPracticeInformationData().getFacilityTaxId());
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
	
	@GetMapping("/getPracticeById/{practice_id}")
	public ResponseEntity<PracticeDto> getPracticeById(@PathVariable Long practice_id) {

		ObjectMapper objectMapper = new ObjectMapper();
		Practice Practice = practiceService.getPracticeById(practice_id)
				.orElseThrow(() -> new ObjectNotFoundException("No UserProfile with ID : " + "", null));
		PracticeDto practiceDto = new PracticeDto();

		try {
			practiceDto = objectMapper.readValue(Practice.getPracticeRequestInfo(), PracticeDto.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(practiceDto);
	}

	@GetMapping("/practice_list")
	public List<PracticeDto> getAllPractices() {
		return practiceService.getAllPractices();
	}

	@GetMapping("/woundCareFocus")
	public List<String> getWoundCare() {
		return Arrays.asList(
				"Acute wound care", 
				"Chronic wound care", 
				"Both acute & chronic wound care"
				);
	}

	@GetMapping("/placeOfService")
	public List<String> getPlaceOfService() {
		return Arrays.asList(
				"In Office (11)", 
				"In Home(12)", 
				"Assisted Living Facility (13)",
				"Skilled Nursing Facility (31)", 
				"Nursing Facility (32)", 
				"ASC 24",
				"HOPD 19/22"
				);
	}
	
	@GetMapping("/productOfInterest")
	public List<String> getProductOfInterest() {
		return Arrays.asList(
				"Barerra", 
				"CarePatch", 
				"Dermacyte",
				"Impax", 
				"Restorigin", 
				"Other"
				);
	}

}
