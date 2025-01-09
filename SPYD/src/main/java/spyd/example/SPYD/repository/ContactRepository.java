package spyd.example.SPYD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spyd.example.SPYD.entity.Contact;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    Optional<Contact> findByEmailId(String emailId);
}
