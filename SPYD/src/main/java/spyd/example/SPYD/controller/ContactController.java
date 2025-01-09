package spyd.example.SPYD.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spyd.example.SPYD.entity.Application;
import spyd.example.SPYD.entity.Contact;
import spyd.example.SPYD.enums.ApplicationStatus;
import spyd.example.SPYD.enums.Applied_Position;
import spyd.example.SPYD.enums.Source;
import spyd.example.SPYD.service.ContactService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/save")
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

    @PutMapping("/status/change")
    public String updateContact(@RequestParam Long id, @RequestParam ApplicationStatus status) throws MessagingException {
        return contactService.updateContact(id,status);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Contact>> getAll(){
        return contactService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(contactService.delete(id), HttpStatus.OK);
    }
    }
