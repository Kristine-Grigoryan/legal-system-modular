package com.example.service.impl;
import com.example.model.Payment;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;


    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }


    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
}
