package com.example.rest.service;
import com.example.model.LegalCase;
import com.example.model.Status;
import com.example.repository.LegalCaseRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LegalCaseScheduler {

    private final LegalCaseRepository legalCaseRepository;

    public LegalCaseScheduler(LegalCaseRepository legalCaseRepository) {
        this.legalCaseRepository = legalCaseRepository;
    }

    @Scheduled(cron ="0 0 9 * * Mon")
    public void autoUpdateCaseStatus() {
        List<LegalCase> activeCases = legalCaseRepository.findAllByStatus(Status.valueOf("OPEN"));

        if (!activeCases.isEmpty()) {
            for (LegalCase lc : activeCases) {

                lc.setStatus(Status.valueOf("CLOSED"));
            }

            legalCaseRepository.saveAll(activeCases);
            System.out.println("Legal cases updated: " + activeCases.size());
        }
    }
}
