package spyd.example.SPYD.service;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spyd.example.SPYD.entity.Application;
import spyd.example.SPYD.enums.ApplicationStatus;
import spyd.example.SPYD.enums.Applied_Position;
import spyd.example.SPYD.enums.Source;

import java.io.IOException;
import java.util.List;

public interface ApplicationService
{
    public ResponseEntity<Application> saveUserData1(String firstName, String lastName, String emailAddress, String phoneNumber, String linkedIn, String coverLetter , Applied_Position positionApplied, Source source, MultipartFile resume) throws IOException;

    String updateStatus(Long id, ApplicationStatus status) throws MessagingException;

    ResponseEntity<List<Application>> getAll();

    String delete(Long id);
}
