package com.example.service;



import com.example.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    Payment save(Payment payment);


    Payment findById(Long id);

    void deleteById(Long id);

}


