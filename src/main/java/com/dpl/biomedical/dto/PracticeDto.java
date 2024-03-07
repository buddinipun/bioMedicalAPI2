package com.dpl.biomedical.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PracticeDto {

	Long id;
	String name;
	String email;
	String sequence;
	PracticeInformationDto practiceInformationData;
	AccountPayableDto accountPayableData;
	BillerInformationDto billerInformationData;
	WoundCareInformationDto woundCareInformationData;
	InsurancePayerMixDto insurancePayerMixData;
}
