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

	String facilityName;
	String facilityTaxId;
	String facilityNPI;
	String facilityPTAN;
	String contactName;
	String contactPhone;
	String contactEmail;
	String contactFax;
	String noOfFacilities;
	String noOfProviders;
	String apContact;
	String apCotactPhone;
	String apCotactEmail;
	String billerContact;
	String billerContactPhone;
	String billerContactEmail;
	String billerAddress;
	String woundCareFocus;
	String AvgWoundsTreatedPerMO;
	List<String> placesOfServices;
	List<String> productOfInterest;
	String firstInsurancePayer;
	String secondInsurancePayer;
	String thirdInsurancePayer;

}
