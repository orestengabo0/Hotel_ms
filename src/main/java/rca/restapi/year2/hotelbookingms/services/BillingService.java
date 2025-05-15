package rca.restapi.year2.hotelbookingms.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rca.restapi.year2.hotelbookingms.models.Billings;
import rca.restapi.year2.hotelbookingms.repositories.BillingsRepository;

@Service
@AllArgsConstructor
public class BillingService {
    private final BillingsRepository billingRepository;

    public Billings getBillingById(Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));
    }
}