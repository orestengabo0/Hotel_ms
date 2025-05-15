package rca.restapi.year2.hotelbookingms.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(nullable = false)
    private double price;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;
}