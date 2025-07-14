package com.Diwise_InBlogsAndContacts.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Entity	
public class Contacts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cId;
	private String fname;
	private String lname;
	private String email;
	private String phoneNumber;
	private String message;
	private Date dateAndTime;
	private String subject;
}
