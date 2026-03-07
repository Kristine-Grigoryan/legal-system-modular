package com.example.service.impl;
import com.example.service.CourtSessionService;
import com.model.CourtSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import repository.CourtSessionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class CourtSessionServiceImpl implements CourtSessionService {

    private final CourtSessionRepository courtSessionRepository;
    private final CourtSessionService courtSessionService;

    @Override
    public List<CourtSession> findAll() {
        return courtSessionRepository.findAll();
    }

    @Override
    public CourtSession save(CourtSession courtSession) {
        return courtSessionRepository.save(courtSession);
    }

    @Override
    public CourtSession findById(Long id) {
        return courtSessionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        courtSessionRepository.deleteById(id);
    }

}
