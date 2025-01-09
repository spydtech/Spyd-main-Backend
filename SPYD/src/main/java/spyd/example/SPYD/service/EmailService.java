package spyd.example.SPYD.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import spyd.example.SPYD.entity.Application;
import spyd.example.SPYD.entity.Contact;
import spyd.example.SPYD.enums.ApplicationStatus;
import spyd.example.SPYD.repository.ApplicationRepository;
import spyd.example.SPYD.repository.ContactRepository;

import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ContactRepository contactRepository;
    public void sendApplicationStatus(Long id , ApplicationStatus status) throws MessagingException {
        Optional<Application> optional= applicationRepository.findById(id);
        Application  application=optional.get();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(application.getEmailAddress());
        helper.setSubject("Please Check Your Application Status");
        String emailContent = "<p>Dear " + application.getFirstName() + " " + application.getLastName() + ",</p>" +
                "<p>We are writing to inform you that your application has been: <strong>" + status + "</strong>.</p>" +
                "<p>Thank you for your interest.</p>";
        helper.setText(emailContent, true);
        mailSender.send(message);

    }

    public void sendContactStatus(Long id , ApplicationStatus status) throws MessagingException {
        Optional<Contact> optional= contactRepository.findById(id);
        Contact  contact=optional.get();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(contact.getEmailId());
        helper.setSubject("Please Check Your Contact Requested Status");
        String emailContent = "<p>Dear " + contact.getRequestedName() + ",</p>" +
                "<p>We are writing to inform you that your Contact Request has been: <strong>" + status + "</strong>.</p>" +
                "<p>Thank you for your interest.</p>";
        helper.setText(emailContent, true);
        mailSender.send(message);

    }

}
