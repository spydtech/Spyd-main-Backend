package spyd.example.SPYD.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spyd.example.SPYD.entity.Application;
import spyd.example.SPYD.entity.Contact;
import spyd.example.SPYD.enums.ApplicationStatus;
import spyd.example.SPYD.enums.Applied_Position;
import spyd.example.SPYD.enums.Source;
import spyd.example.SPYD.repository.ApplicationRepository;
import spyd.example.SPYD.service.ApplicationService;
import spyd.example.SPYD.service.EmailService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImplementation implements ApplicationService
{
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    EmailService emailService;


    @Override
    public ResponseEntity<Application> saveUserData1(String firstName, String lastName, String emailAddress, String phoneNumber, String linkedIn, String coverLetter, Applied_Position positionApplied, Source source, MultipartFile resume) throws IOException {
        Application application1=new Application();
        application1.setFirstName(firstName);
        application1.setLastName(lastName);
        application1.setEmailAddress(emailAddress);
        application1.setPhoneNumber(phoneNumber);
        application1.setLinkedIn(linkedIn);
        application1.setCoverLetter(coverLetter);
        application1.setSource(source);
        application1.setPosition_applied(positionApplied);
        application1.setResume(resume.getBytes());
        application1.setStatus(ApplicationStatus.PENDING);
        Application applicationSave=applicationRepository.save(application1);
        return ResponseEntity.ok(applicationSave);
    }

    @Override
    public String updateStatus(Long id, ApplicationStatus status) throws MessagingException {
        Optional<Application> optional=applicationRepository.findById(id);
        if(optional.isPresent()){
          Application application=optional.get();
          application.setStatus(status);
          applicationRepository.save(application);
            emailService.sendApplicationStatus(id,status);

        }
        return "Application status sent successfully.";
    }

    @Override
    public String delete(Long id) {
        Optional<Application> optional=applicationRepository.findById(id);
        optional.ifPresent(application -> applicationRepository.delete(application));
        return "Application Deleted  successfully.";
    }

    @Override
    public ResponseEntity<List<Application>> getAll() {
        List<Application> applications=applicationRepository.findAll();
        return ResponseEntity.ok(applications);
    }
}
