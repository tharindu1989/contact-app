package com.vrealcompany.demo.service;

import com.vrealcompany.demo.model.Contact;
import com.vrealcompany.demo.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.get();
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Optional<Contact> existingContact = contactRepository.findById(contact.getId());
        if(existingContact.isEmpty())
            throw new EntityNotFoundException();
        /**
         * set properties here
         */
        return contactRepository.save(contact);
    }
}
