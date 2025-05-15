package rca.restapi.year2.hotelbookingms.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rca.restapi.year2.hotelbookingms.models.Hotel;
import rca.restapi.year2.hotelbookingms.repositories.HotelRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}