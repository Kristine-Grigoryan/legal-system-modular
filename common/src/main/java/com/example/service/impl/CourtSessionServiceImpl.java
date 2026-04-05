package com.example.service.impl;
import com.example.model.CourtSession;
import com.example.repository.CourtSessionRepository;
import com.example.service.CourtSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class CourtSessionServiceImpl implements CourtSessionService {

    private final CourtSessionRepository courtSessionRepository;


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
