package rca.restapi.year2.hotelbookingms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.restapi.year2.hotelbookingms.models.Rooms;

public interface RoomRepository extends JpaRepository<Rooms, Long> {
}
