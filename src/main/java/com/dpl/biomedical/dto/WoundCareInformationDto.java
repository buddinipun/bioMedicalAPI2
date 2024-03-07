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
public class WoundCareInformationDto implements Serializable {
	
	private static final long serialVersionUID = -6184336210915516023L;
	private String woundCareFocus;
	private String avgWoundsTreatedPerMonth;
	private String[] placesOfServices;
    private String[] productsOfInterest;

}
