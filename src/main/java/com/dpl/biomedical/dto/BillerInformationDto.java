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
public class BillerInformationDto implements Serializable {
	
	private static final long serialVersionUID = -6184336210915516023L;
	
	private String billerContact;
	private String billerContactPhone;
	private String billerContactEmail;
	private String billerAddress;

}
