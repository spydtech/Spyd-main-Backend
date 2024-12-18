package spyd.example.SPYD.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spyd.example.SPYD.entity.User;
import spyd.example.SPYD.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetails implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository=userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String firstname) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(firstname);

        if(user == null) {
            throw new UsernameNotFoundException("user not found with email "+firstname);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

}
