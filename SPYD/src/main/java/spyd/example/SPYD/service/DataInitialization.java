package spyd.example.SPYD.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import spyd.example.SPYD.entity.User;
import spyd.example.SPYD.repository.UserRepository;
@Component
public class DataInitialization implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataInitialization.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void run(String... args) throws Exception {
        initializeAdminUser();

    }

    private void initializeAdminUser() {
        String adminUsername = "hr@spydtech.com";

        try {
            if (userRepository.findByEmail(adminUsername) == null) {
                User adminUser = new User();
                adminUser.setFirstName("Spyd");
                adminUser.setLastName("tech");
                adminUser.setEmail(adminUsername);
                adminUser.setPassword(bCryptPasswordEncoder.encode("Spyd@1234"));
                adminUser.setRole("ADMIN");
                userRepository.save(adminUser);
                logger.info("Admin user initialized successfully.");
            } else {
                logger.warn("Admin user already exists. Skipping initialization.");
            }
        } catch (DataAccessException e) {
            logger.error("error while initializing admin user: " + e.getMessage(), e);
        }
    }


}
