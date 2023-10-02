package com.vrealcompany.demo.service;

import com.vrealcompany.demo.model.Contact;

import java.util.List;

public interface ContactService {
    public List<Contact> getContacts();

    public Contact getContactById(Long id);

    public Contact createContact(Contact contact);

    public Contact updateContact(Contact contact);
}
