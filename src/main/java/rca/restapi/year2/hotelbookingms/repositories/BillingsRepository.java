package rca.restapi.year2.hotelbookingms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.restapi.year2.hotelbookingms.models.Billings;

public interface BillingsRepository extends JpaRepository<Billings, Long> {
}
