package rca.restapi.year2.hotelbookingms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.restapi.year2.hotelbookingms.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameOrEmail(String username, String email);
    User findByName(String name);
    boolean existsByEmail(String email);
}
