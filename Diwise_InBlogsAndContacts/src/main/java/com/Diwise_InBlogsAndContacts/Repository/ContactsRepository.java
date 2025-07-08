package com.Diwise_InBlogsAndContacts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Diwise_InBlogsAndContacts.model.Contacts;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Integer> {

}
