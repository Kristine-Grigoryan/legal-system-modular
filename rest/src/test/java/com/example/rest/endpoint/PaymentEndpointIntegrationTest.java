package com.example.rest.endpoint;

import com.example.model.LegalCase;
import com.example.model.Payment;
import com.example.model.PaymentMethod;
import com.example.repository.LegalCaseRepository;
import com.example.repository.PaymentRepository;
import com.example.rest.dto.PaymentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class PaymentEndpointIntegrationTest  extends BaseEndpointIntegrationTest   {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LegalCaseRepository legalCaseRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private LegalCase savedCase;

    @BeforeEach
    void setUp() {
        paymentRepository.deleteAll();
        legalCaseRepository.deleteAll();

        LegalCase legalCase = new LegalCase();
        savedCase = legalCaseRepository.save(legalCase);
    }

    @Test
    @Transactional
    @WithMockUser
    void shouldCreatePaymentSuccessfully() throws Exception {

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setCaseId(savedCase.getId());
        paymentDto.setAmount(new BigDecimal("5000.00"));
        paymentDto.setMethod("CASH");
        paymentDto.setPaidDate(LocalDate.now());

        mockMvc.perform(post("/payment")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(5000.0))
                .andExpect(jsonPath("$.caseId").value(savedCase.getId()));
    }






}