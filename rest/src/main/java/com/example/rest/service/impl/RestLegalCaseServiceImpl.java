package com.example.rest.service.impl;

import com.example.model.LegalCase;
import com.example.repository.LegalCaseRepository;
import com.example.rest.dto.LegalCaseDto;
import com.example.rest.mapper.LegalCaseMapper;
import com.example.rest.service.LegalCaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class RestLegalCaseServiceImpl implements LegalCaseService {


    private final LegalCaseRepository legalCaseRepository;
    private final LegalCaseMapper legalCaseMapper;

    @Override
    public List<LegalCaseDto> findAll() {
        log.info("Fetching all legalCases");
        return legalCaseMapper.toDtoList(legalCaseRepository.findAll());
    }

    @Override
    public LegalCaseDto save(LegalCaseDto legalCaseDto) {
        LegalCase entity = legalCaseMapper.toEntity(legalCaseDto);
        LegalCase savedEntity = legalCaseRepository.save(entity);
        return legalCaseMapper.toDto(savedEntity);

    }


    @Override
    public LegalCaseDto findById(Long id) {
        log.info("Fetching legal case by id: {}", id);
        LegalCase legalCase = legalCaseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LegalCase with id " + id + " not found"));
        return legalCaseMapper.toDto(legalCase);
    }


    @Override
    public void deleteById(Long id) {
        log.info("Deleting legal case by id: {}", id);
        if (!legalCaseRepository.existsById(id)) {
            throw new EntityNotFoundException("LegalCase with id " + id + " not found");
        }
        legalCaseRepository.deleteById(id);
    }

}