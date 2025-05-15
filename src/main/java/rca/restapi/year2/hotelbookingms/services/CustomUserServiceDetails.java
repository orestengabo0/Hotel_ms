package rca.restapi.year2.hotelbookingms.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import rca.restapi.year2.hotelbookingms.models.User;
import rca.restapi.year2.hotelbookingms.repositories.UserRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserServiceDetails implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) {
        User user = userRepository.findByNameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}

