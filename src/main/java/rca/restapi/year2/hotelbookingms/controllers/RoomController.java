package rca.restapi.year2.hotelbookingms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rca.restapi.year2.hotelbookingms.models.Rooms;
import rca.restapi.year2.hotelbookingms.services.RoomService;

@RestController
@RequestMapping("/api/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Rooms> createRoom(@RequestBody Rooms room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rooms> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
}