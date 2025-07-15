package com.Diwise_InBlogsAndContacts.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Diwise_InBlogsAndContacts.Dto.LoginDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;
import com.Diwise_InBlogsAndContacts.Dto.RegistrationDto;
import com.Diwise_InBlogsAndContacts.Dto.UserDto;
import com.Diwise_InBlogsAndContacts.Repository.UserRepository;
import com.Diwise_InBlogsAndContacts.Util.Constants;
import com.Diwise_InBlogsAndContacts.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public Message<UserDto> register(RegistrationDto userDto) {
		Message<UserDto> message = new Message<>();
		User user = new User();
		UserDto userDto1 = new UserDto();
		try {
			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());
			user = userRepository.save(user);
			userDto1.setId(user.getId());
			userDto1.setUsername(user.getUsername());
			message.setStatus(HttpStatus.CREATED);
			message.setResponseMessage(Constants.SUCCESS);
			message.setData(userDto1);
			return message;

		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			return message;
		}
	}

	@Override
	public Message<UserDto> login(LoginDto loginDto) {
		Message<UserDto> message = new Message<>();
		User user = new User();
		user = userRepository.findByUsername(loginDto.getUsername());
		try {

			if (user.getPassword().equals(loginDto.getPassword())) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUsername(user.getUsername());
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(Constants.SUCCESS);
				message.setData(userDto);
				return message;
			} else
				message.setStatus(HttpStatus.BAD_REQUEST);
			message.setResponseMessage(Constants.USER_NOT_FOUND);
			return message;
		} catch (Exception ec) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(ec.getMessage());
			return message;

		}
	}

}
