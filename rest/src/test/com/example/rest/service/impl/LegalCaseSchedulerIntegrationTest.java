package com.example.rest.service.impl;

import com.example.model.LegalCase;
import com.example.model.Status;
import com.example.repository.LegalCaseRepository;
import com.example.rest.endpoint.BaseEndpointIntegrationTest;
import com.example.rest.service.LegalCaseScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class LegalCaseSchedulerIntegrationTest extends BaseEndpointIntegrationTest {

    @Autowired
    private LegalCaseRepository legalCaseRepository;

    @SpyBean
    private LegalCaseScheduler legalCaseScheduler;

    @Test
    void shouldUpdateCaseStatusToClosedByScheduler() {
        LegalCase legalCase = new LegalCase();
        legalCase.setTitle("Test Scheduler Case");
        legalCase.setStatus(Status.OPEN);
        legalCaseRepository.save(legalCase);


        legalCaseScheduler.autoUpdateCaseStatus();


        await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
                LegalCase updatedCase = legalCaseRepository.findById(legalCase.getId()).orElseThrow();
                assertEquals(Status.CLOSED, updatedCase.getStatus());
            });


        verify(legalCaseScheduler, atLeastOnce()).autoUpdateCaseStatus();
    }
}
