package com.example.rest.service;

import com.example.rest.dto.LegalCaseDto;
import com.example.rest.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findAll();

    PaymentDto findById(Long id);

    PaymentDto save(PaymentDto paymentDto);

    PaymentDto update(Long id, PaymentDto paymentDto);

    void deleteById(Long id);

}











