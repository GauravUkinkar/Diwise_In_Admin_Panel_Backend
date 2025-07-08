package com.Diwise_InBlogsAndContacts.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Diwise_InBlogsAndContacts.Dto.ContactDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;
import com.Diwise_InBlogsAndContacts.Repository.ContactsRepository;
import com.Diwise_InBlogsAndContacts.Util.Constants;
import com.Diwise_InBlogsAndContacts.model.Contacts;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
private final ContactsRepository contactRepository;
	@Override
	public Message<ContactDto> addContact(ContactDto contactDto) {
		Message<ContactDto> message = new Message<>();
		Contacts contact = new Contacts();
		try {
			contact.setFname(contactDto.getFname());
			contact.setLname(contactDto.getLname());
			contact.setEmail(contactDto.getEmail());
			contact.setPhoneNumber(contactDto.getPhoneNumber());
			contact.setMessage(contactDto.getMessage());
			
			 Date now = new Date();
			    contact.setDateAndTime(now);
			contact.setSubject(contactDto.getSubject());
			 contactRepository.save(contact);
			ContactDto contactDto1 = new ContactDto();
			contactDto1.setCId(contact.getCId());
			contactDto1.setFname(contact.getFname());
			contactDto1.setLname(contact.getLname());
			contactDto1.setEmail(contact.getEmail());
			contactDto1.setPhoneNumber(contact.getPhoneNumber());
			contactDto1.setMessage(contact.getMessage());
			contactDto1.setDateAndTime(contact.getDateAndTime());
			contactDto1.setSubject(contact.getSubject());
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(Constants.SUCCESS);
			message.setData(contactDto1);
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			return message;
		}
	}

	@Override
	public List<Message<ContactDto>> getAllContacts() {
		List<Message<ContactDto>> message = new ArrayList<>();
		try {
			List<Contacts> contacts = contactRepository.findAll();
			for(Contacts contact : contacts) {
				ContactDto dto = new ContactDto();
				dto.setCId(contact.getCId());
				dto.setFname(contact.getFname());
				dto.setLname(contact.getLname());
				dto.setEmail(contact.getEmail());
				dto.setPhoneNumber(contact.getPhoneNumber());
				dto.setMessage(contact.getMessage());
				dto.setDateAndTime(contact.getDateAndTime());
				dto.setSubject(contact.getSubject());
				
				message.add(new Message<>(HttpStatus.OK,Constants.CONTACT_FETCH_SUCCESSFUL,dto));
			}
			return message;
		} catch (Exception e) {
			message.add(new Message<ContactDto>(HttpStatus.INTERNAL_SERVER_ERROR,Constants.SOMETHING_WENT_WRONG,null));
			return message;
		}
	}

}
