package rca.restapi.year2.hotelbookingms.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rca.restapi.year2.hotelbookingms.models.Bookings;
import rca.restapi.year2.hotelbookingms.models.User;
import rca.restapi.year2.hotelbookingms.repositories.BookingsRepository;
import rca.restapi.year2.hotelbookingms.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingsRepository bookingRepository;
    private final UserRepository userRepository;

    public Bookings createBooking(Bookings booking, String username) {
        User user = userRepository.findByName(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        booking.setUser(user);
        booking.setStatus("ACTIVE");
        return bookingRepository.save(booking);
    }

    public List<Bookings> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public void cancelBooking(Long id, String username) {
        Bookings booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (!booking.getUser().getName().equals(username)) {
            throw new RuntimeException("Unauthorized to cancel this booking");
        }
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }
}