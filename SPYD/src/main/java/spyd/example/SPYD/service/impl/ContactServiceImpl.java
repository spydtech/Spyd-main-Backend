package spyd.example.SPYD.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spyd.example.SPYD.entity.Application;
import spyd.example.SPYD.entity.Contact;
import spyd.example.SPYD.enums.ApplicationStatus;
import spyd.example.SPYD.repository.ContactRepository;
import spyd.example.SPYD.service.ContactService;
import spyd.example.SPYD.service.EmailService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    EmailService emailService;
    @Override
    public ResponseEntity<Contact> saveContact(Contact contactRequest) {
        Contact contact=new Contact();
        contact.setDate(LocalDate.now());
        contact.setRequestedName(contactRequest.getRequestedName());
        contact.setEmailId(contactRequest.getEmailId());
        contact.setMobileNo(contactRequest.getMobileNo());
        contact.setComments(contactRequest.getComments());
        contact.setStatus(ApplicationStatus.PENDING);
        contactRepository.save(contact);
        return ResponseEntity.ok(contact);
    }

    @Override
    public String updateContact(Long id, ApplicationStatus status) throws MessagingException {
        Optional<Contact> optional=contactRepository.findById(id);
        if(optional.isPresent()){
            Contact contact=optional.get();
            contact.setStatus(status);
            contactRepository.save(contact);
            emailService.sendContactStatus(id,status);

        }
        return "Application status sent successfully.";
    }

    @Override
    public ResponseEntity<List<Contact>> getAll() {
        List<Contact> contacts=contactRepository.findAll();
        return ResponseEntity.ok(contacts);
    }

    @Override
    public String delete(Long id) {
        Optional<Contact> optional=contactRepository.findById(id);
        optional.ifPresent(contact -> contactRepository.delete(contact));
        return "Contact Deleted  successfully.";
    }
}
