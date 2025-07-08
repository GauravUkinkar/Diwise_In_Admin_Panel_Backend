package com.Diwise_InBlogsAndContacts.Dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
public class ContactDto {
	private int cId;
	private String fname;
	private String lname;
	private String email;
	private String phoneNumber;
	private String message;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	    private Date dateAndTime;
	private String subject;
}
