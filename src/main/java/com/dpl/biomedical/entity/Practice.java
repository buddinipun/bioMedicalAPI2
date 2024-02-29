package com.dpl.biomedical.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Practice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "practice_id")
	Long id;
	
	@Column(name = "`name`") 
	String name;
	
	@Column(name = "contact_email")
	String email;
	
	@Column(name = "sequence_number")
    private String sequence;
}
