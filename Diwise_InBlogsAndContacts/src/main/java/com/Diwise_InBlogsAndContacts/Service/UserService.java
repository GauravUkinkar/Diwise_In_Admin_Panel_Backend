package com.Diwise_InBlogsAndContacts.Service;

import com.Diwise_InBlogsAndContacts.Dto.LoginDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;
import com.Diwise_InBlogsAndContacts.Dto.RegistrationDto;
import com.Diwise_InBlogsAndContacts.Dto.UserDto;

public interface UserService {
	public Message<UserDto> login(LoginDto loginDto);
	public Message<UserDto> register(RegistrationDto userDto);

}
