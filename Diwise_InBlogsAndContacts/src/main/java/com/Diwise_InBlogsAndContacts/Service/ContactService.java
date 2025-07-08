package com.Diwise_InBlogsAndContacts.Service;

import java.util.List;

import com.Diwise_InBlogsAndContacts.Dto.ContactDto;
import com.Diwise_InBlogsAndContacts.Dto.Message;

public interface ContactService {
	
	public Message<ContactDto> addContact(ContactDto contactDto);
	public List<Message<ContactDto>> getAllContacts();

}
