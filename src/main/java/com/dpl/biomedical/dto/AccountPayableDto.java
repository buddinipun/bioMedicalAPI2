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
public class AccountPayableDto implements Serializable {
	
	private static final long serialVersionUID = -6184336210915516023L;
	
	private String apContact;
	private String apContactPhone;
	private String apContactEmail;

}
