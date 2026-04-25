package com.example.rest.service.impl;

import com.example.model.Payment;

import com.example.repository.PaymentRepository;
import com.example.rest.dto.PaymentDto;
import com.example.rest.mapper.PaymentMapper;
import com.example.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private RestPaymentServiceImpl paymentService;

    private PaymentDto paymentDto;
    private Payment payment;

    @BeforeEach
    void setUp() {
        paymentDto = PaymentDto.builder()
                .id(1L)
                .amountUSD(new BigDecimal("25"))
                .build();

        payment = new Payment();
        payment.setId(1L);
        payment.setAmount(new BigDecimal("1000"));
    }



    @Test
    void testFindById_Found() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentMapper.toDto(payment)).thenReturn(paymentDto);

        PaymentDto result = paymentService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(paymentRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> paymentService.findById(2L));
    }
}