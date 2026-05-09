package com.example.rest.endpoint;

import com.example.model.LegalCase;
import com.example.model.Payment;
import com.example.repository.PaymentRepository;
import com.example.rest.dto.LegalCaseDto;
import com.example.rest.dto.PaymentDto;
import com.example.rest.mapper.PaymentMapper;
import com.example.rest.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentEndpoint {


    private final PaymentService paymentService;


    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.save(paymentDto));
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id, @RequestBody PaymentDto paymentDto) {
        PaymentDto updatedPayment = paymentService.update(id, paymentDto);
        return ResponseEntity.ok(updatedPayment);
    }


    @DeleteMapping("/payment/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}









