package com.sivalabs.springbootjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class ContactService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> allContacts() {
        return contactRepository.findAll();
    }

    public Contact createContact(String name, String email) {
        Contact contact = new Contact();
        contact.setEmailId(email);
        contact.setName(name);
        em.persist(contact);
        return contact;
    }
}
