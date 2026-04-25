package com.example.rest.service.impl;
import com.example.model.LegalCase;
import com.example.model.Payment;
import com.example.repository.LegalCaseRepository;
import com.example.repository.PaymentRepository;
import com.example.rest.dto.PaymentDto;
import com.example.rest.mapper.PaymentMapper;
import com.example.rest.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestPaymentServiceImpl implements PaymentService {


    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private  final LegalCaseRepository legalCaseRepository;

    @Override
    public List<PaymentDto> findAll() {
        return paymentMapper.toDtoList(paymentRepository.findAll());

    }
    @Override
    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        return paymentMapper.toDto(payment);
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        LegalCase legalCase = legalCaseRepository.findById(paymentDto.getCaseId())
                .orElseThrow(() -> new EntityNotFoundException("Case not found"));
        Payment entity = paymentMapper.toEntity(paymentDto);
        entity.setLegalCase(legalCase);
        Payment savedEntity = paymentRepository.save(entity);
        return paymentMapper.toDto(savedEntity);
    }

    @Override
    public PaymentDto update(Long id, PaymentDto paymentDto) {
        paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        Payment payment = paymentMapper.toEntity(paymentDto);
        payment.setId(id);

        return paymentMapper.toDto(paymentRepository.save(payment));
    }


    @Override
    public void deleteById(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }
}
