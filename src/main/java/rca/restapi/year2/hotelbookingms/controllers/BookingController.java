package rca.restapi.year2.hotelbookingms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rca.restapi.year2.hotelbookingms.models.Bookings;
import rca.restapi.year2.hotelbookingms.repositories.UserRepository;
import rca.restapi.year2.hotelbookingms.services.BookingService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final UserRepository userRepository;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Bookings> createBooking(@RequestBody Bookings booking, Principal principal) {
        return ResponseEntity.ok(bookingService.createBooking(booking, principal.getName()));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Bookings>> getMyBookings(Principal principal) {
        // Example: fetch user by username and get ID
        Long userId = userRepository.findByName(principal.getName()).getId();
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id, Principal principal) {
        bookingService.cancelBooking(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}