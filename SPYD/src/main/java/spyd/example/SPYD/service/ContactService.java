package spyd.example.SPYD.service;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import spyd.example.SPYD.entity.Contact;
import spyd.example.SPYD.enums.ApplicationStatus;

import java.util.List;

public interface ContactService {
    ResponseEntity<Contact> saveContact(Contact contact);

    String updateContact(Long id, ApplicationStatus status) throws MessagingException;

    ResponseEntity<List<Contact>> getAll();

    String delete(Long id);
}
