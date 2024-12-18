package spyd.example.SPYD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spyd.example.SPYD.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String adminUsername);
}
