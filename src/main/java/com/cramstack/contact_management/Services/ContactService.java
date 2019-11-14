package com.cramstack.contact_management.Services;

import com.cramstack.contact_management.Models.Contact;
import com.cramstack.contact_management.Payloads.ContactPayload;
import com.cramstack.contact_management.Repositorys.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public void saveContact(ContactPayload contactPayload) {

        Contact contact = new Contact(contactPayload.getFirstName(),
                                      contactPayload.getLastName(),
                                      contactPayload.getEmail(),
                                      contactPayload.getPhoneNumber(),
                                      contactPayload.getAddress());

        contactRepository.save(contact);
    }

    public void saveContact(Integer id , ContactPayload contactPayload) {

        Contact contact = new Contact(contactPayload.getFirstName(),
                                      contactPayload.getLastName(),
                                      contactPayload.getEmail(),
                                      contactPayload.getPhoneNumber(),
                                      contactPayload.getAddress());
        contact.setId(id);
        contactRepository.save(contact);
    }

    public List<Contact> getAllContact() {

        return contactRepository.findAll();
    }

    public Optional<Contact> findContact(Integer id) {

        return contactRepository.findById(id);
    }

    public void deleteContact(Contact contact) {

        contactRepository.delete(contact);
    }
}
