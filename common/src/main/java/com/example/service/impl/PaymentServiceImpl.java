package com.example.service.impl;

import com.example.model.Payment;
import com.example.model.User;
import com.example.repository.PaymentRepository;
import com.example.repository.UserRepository;
import com.example.service.PaymentService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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

