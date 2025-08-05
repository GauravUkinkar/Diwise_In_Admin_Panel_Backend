package com.Diwise_InBlogsAndContacts.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Diwise_InBlogsAndContacts.Dto.ContactDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;
import com.Diwise_InBlogsAndContacts.Service.ContactServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
@Log4j2
public class ContactController {
	private final ContactServiceImpl service;
	
	@PostMapping("/addContacts")
	public ResponseEntity<Message<ContactDto>>  addContact(@RequestBody ContactDto contactDto){
		Message<ContactDto> message = service.addContact(contactDto);
        HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
        return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getAllContacts")
	public ResponseEntity<List<Message<ContactDto>>> getAllContacts(){
        List<Message<ContactDto>> message = service.getAllContacts();
        return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
