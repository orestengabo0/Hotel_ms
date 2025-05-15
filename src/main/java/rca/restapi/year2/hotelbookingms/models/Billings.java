package rca.restapi.year2.hotelbookingms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "billings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Bookings booking;

    @Column(nullable = false)
    private double amount;

    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;
}