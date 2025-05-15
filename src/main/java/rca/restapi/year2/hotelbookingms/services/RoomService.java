package rca.restapi.year2.hotelbookingms.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rca.restapi.year2.hotelbookingms.models.Rooms;
import rca.restapi.year2.hotelbookingms.repositories.RoomRepository;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Rooms createRoom(Rooms room) {
        return roomRepository.save(room);
    }

    public Rooms getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }
}