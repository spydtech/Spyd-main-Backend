package spyd.example.SPYD.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spyd.example.SPYD.entity.Application;
import spyd.example.SPYD.enums.ApplicationStatus;
import spyd.example.SPYD.enums.Applied_Position;
import spyd.example.SPYD.enums.Source;
import spyd.example.SPYD.service.ApplicationService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/save1")
    public ResponseEntity<Application> saveUserData1(@RequestParam  String firstName, @RequestParam  String lastName, @RequestParam  String emailAddress, @RequestParam  String phoneNumber, @RequestParam String linkedIn, @RequestParam String coverLetter , @RequestParam Applied_Position positionApplied, @RequestParam Source source, @RequestParam MultipartFile resume) throws IOException {
        return applicationService.saveUserData1(firstName,lastName,emailAddress,phoneNumber,linkedIn,coverLetter,positionApplied,source,resume);
    }

    @PutMapping("/change/status")
    public String updateStatus(@RequestParam Long id, @RequestParam ApplicationStatus status ) throws MessagingException {
        return applicationService.updateStatus(id,status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(applicationService.delete(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Application>> getAll(){
        return applicationService.getAll();
    }
}
