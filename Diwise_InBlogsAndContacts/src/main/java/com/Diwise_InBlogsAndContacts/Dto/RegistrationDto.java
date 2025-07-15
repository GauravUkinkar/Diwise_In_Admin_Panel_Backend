package com.Diwise_InBlogsAndContacts.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
@ToString
@Data
@Accessors(chain = true)
public class RegistrationDto {

		private int id;
		private String username;
		private String password;


}
