package rca.restapi.year2.hotelbookingms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rca.restapi.year2.hotelbookingms.models.Billings;
import rca.restapi.year2.hotelbookingms.services.BillingService;

@RestController
@RequestMapping("/api/billings")
@AllArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Billings> getBilling(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getBillingById(id));
    }
}