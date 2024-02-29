package com.dpl.biomedical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dpl.biomedical.dto.PracticeDto;
import com.dpl.biomedical.service.PracticeService;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class PracticeController {
	
	@Autowired
	PracticeService practiceService;
	
	@PostMapping("/createPracticeRequest")
	public ResponseEntity<String> createPractice(@RequestBody PracticeDto practiceDto) {
		
		boolean practiceStatus = practiceService.createPracticeRequest(practiceDto.getName(), practiceDto.getEmail(), practiceDto.getContactName(), practiceDto.getContactPhone());
		
		if(practiceStatus) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(500).body("Failure");	
		}
	}
	
	@GetMapping("/practice_list")
    public List<PracticeDto> getAllPractices() {
        return practiceService.getAllPractices();
    }
	

}
