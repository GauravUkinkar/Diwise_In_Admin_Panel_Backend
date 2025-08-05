package com.Diwise_InBlogsAndContacts.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Diwise_InBlogsAndContacts.Dto.LoginDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;
import com.Diwise_InBlogsAndContacts.Dto.RegistrationDto;
import com.Diwise_InBlogsAndContacts.Dto.UserDto;
import com.Diwise_InBlogsAndContacts.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
@Log4j2
public class UserController {
	private final UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<Message<UserDto>> registerUser(@RequestBody RegistrationDto dto) {
        Message<UserDto> message = service.register(dto);
        HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
        return ResponseEntity.status(httpStatus).body(message);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Message<UserDto>> login(@RequestBody LoginDto dto) {
        Message<UserDto> message = service.login(dto);
        HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
        return ResponseEntity.status(httpStatus).body(message);
	}

}
