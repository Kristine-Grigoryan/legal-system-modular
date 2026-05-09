package com.example.rest.service.impl;
import com.example.model.CourtSession;
import com.example.model.LegalCase;
import com.example.model.Payment;
import com.example.repository.CourtSessionRepository;
import com.example.repository.LegalCaseRepository;
import com.example.repository.PaymentRepository;
import com.example.rest.dto.CourtSessionDto;
import com.example.rest.dto.PaymentDto;
import com.example.rest.mapper.CourtSessionMapper;
import com.example.rest.mapper.PaymentMapper;
import com.example.rest.service.CourtSessionService;
import com.example.rest.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestCourtSessionServiceImpl implements CourtSessionService {


    private final CourtSessionMapper courtSessionMapper;
    private final CourtSessionRepository courtSessionRepository;
    private  final LegalCaseRepository legalCaseRepository;

    @Override
    public List<CourtSessionDto> findAll() {
        return courtSessionMapper.toDtoList(courtSessionRepository.findAll());

    }


    @Override
    public CourtSessionDto findById(Long id) {
        return courtSessionRepository.findById(id)
                .map(courtSessionMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CourtSession not found with id " + id));
    }



    @Override
    public CourtSessionDto save(CourtSessionDto courtSessionDto) {
        LegalCase legalCase = legalCaseRepository.findById(courtSessionDto.getCaseId())
                .orElseThrow(() -> new EntityNotFoundException("Case not found"));
        CourtSession entity = courtSessionMapper.toEntity(courtSessionDto);
        entity.setLegalCase(legalCase);
        CourtSession savedEntity = courtSessionRepository.save(entity);
        return courtSessionMapper.toDto(savedEntity);
    }



    @Override
    public CourtSessionDto update(Long id, CourtSessionDto dto) {
        CourtSession existing = courtSessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourtSession not found"));

        existing.setCourtroom(dto.getCourtroom());
        existing.setSessionDate(dto.getSessionDate());
        existing.setNotes(dto.getNotes());


        if (dto.getCaseId() != null) {
            LegalCase legalCase = legalCaseRepository.findById(dto.getCaseId())
                    .orElseThrow(() -> new EntityNotFoundException("Case not found"));
            existing.setLegalCase(legalCase);
        }

        return courtSessionMapper.toDto(courtSessionRepository.save(existing));
    }



    @Override
    public void deleteById(Long id) {
        if (!courtSessionRepository.existsById(id)) {
            throw new EntityNotFoundException("CourtSession not found");
        }
        courtSessionRepository.deleteById(id);
    }
}
