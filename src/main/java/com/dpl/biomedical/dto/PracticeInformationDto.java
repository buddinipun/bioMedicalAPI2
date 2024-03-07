package com.dpl.biomedical.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracticeInformationDto implements Serializable {
	
	private static final long serialVersionUID = -6184336210915516023L;
	
	private String facilityName;
	private String facilityTaxId;
	private String facilityNPI;
	private String facilityPTAN;
	private String contactName;
	private String contactEmail;
	private String contactPhone;
	private String contactFax;
	private String noOfFacilities;
	private String noOfProviders;

}
