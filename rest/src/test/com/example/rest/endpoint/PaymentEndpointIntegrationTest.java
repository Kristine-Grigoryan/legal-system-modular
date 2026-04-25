package com.example.rest.endpoint;

import com.example.rest.dto.PaymentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentEndpointIntegrationTest extends BaseEndpointIntegrationTest  {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreatePayment() throws Exception {
        PaymentDto paymentDto = PaymentDto.builder()
                .paidDate(LocalDate.now())
                .method("BANK_TRANSFER")
                .caseId(1L)
                .amount(new BigDecimal("50000.00"))
                .amountUSD(new BigDecimal("125.00"))
                .amountEUR(new BigDecimal("115.00"))
                .amountRUB(new BigDecimal("12000.00"))
                .build();

        mockMvc.perform(post("/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.method").value("BANK_TRANSFER"))
                .andExpect(jsonPath("$.amountUSD").value(125.00));
    }

    @Test
    public void testGetAllPayments() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetPaymentById() throws Exception {
        mockMvc.perform(get("/payments/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testDeletePayment() throws Exception {
        mockMvc.perform(delete("/payment/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
