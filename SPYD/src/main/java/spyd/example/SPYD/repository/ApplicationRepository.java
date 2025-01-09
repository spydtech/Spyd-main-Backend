package spyd.example.SPYD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spyd.example.SPYD.entity.Application;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>
{

    Optional<Application> findByEmailAddress(String email);
}
