package com.vrealcompany.demo.controller;

import com.vrealcompany.demo.model.Contact;
import com.vrealcompany.demo.service.ContactService;
import com.vrealcompany.demo.service.client.ContactClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactClient client;

    /**
     * TODO:: Pagination
     */
    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {

        List<Contact> contactList = client.getAllContacts();
        List<Contact> contacts = contactService.getContacts();

        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact savedContact = contactService.createContact(contact);
        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Contact> createContact(@PathVariable Long id, @RequestBody Contact contact) {
        contact.setId(id);
        Contact updatedContact = contactService.updateContact(contact);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    /**
     * TODO:: Do a controller advice
     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
