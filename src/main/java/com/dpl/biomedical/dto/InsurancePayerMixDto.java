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
public class InsurancePayerMixDto implements Serializable {
	
	private static final long serialVersionUID = -6184336210915516023L;
	
	private String firstInsurancePayer;
	private String secondInsurancePayer;
	private String thirdInsurancePayer;

}
