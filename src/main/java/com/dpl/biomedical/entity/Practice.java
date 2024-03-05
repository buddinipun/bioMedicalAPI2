package com.dpl.biomedical.entity;

import java.sql.Timestamp;

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

	@Column(name = "sequence_number")
	private String sequence;

	@Column(name = "practice_payable_contact")
	String paybleContact;

	@Column(name = "practice_payable_phone")
	String payblePhone;

	@Column(name = "practice_payable_email")
	String paybleEmail;

	@Column(name = "contact_name")
	String contactName;

	@Column(name = "contact_email")
	String contactEmail;

	@Column(name = "contact_phone")
	String contactPhone;

	@Column(name = "contact_fax")
	String contactFax;

	@Column(name = "`ptan`")
	String ptan;

	@Column(name = "`npi`")
	String npi;

	@Column(name = "tax_id")
	String tax_id;

	@Column(name = "bill_group_individual_npi")
	String billGroupNPI;

	@Column(name = "billing_street_address")
	String billingStreetAddress;

	@Column(name = "billing_unit_number")
	String billingUnitNumber;

	@Column(name = "billing_city")
	String billingCity;

	@Column(name = "billing_state")
	String billingState;

	@Column(name = "billing_zipcode")
	String billingZipcode;

	@Column(name = "practice_request_info")
	String practiceRequestInfo;

	@Column(name = "notification_emails")
	String notificationeEmails;

	@Column(name = "approval_status")
	String approvalStatus;

	@Column(name = "request_timestamp")
	private Timestamp requestTimestamp;

	@Column(name = "approval_timestamp")
	private Timestamp approvalTimestamp;

	@Column(name = "audit_user_id")
	String auditUserId;

	@Column(name = "audit_timestamp")
	private Timestamp auditTimestamp;
}
