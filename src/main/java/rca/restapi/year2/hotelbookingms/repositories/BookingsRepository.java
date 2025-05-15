package rca.restapi.year2.hotelbookingms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.restapi.year2.hotelbookingms.models.Bookings;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUserId(Long userId);
}