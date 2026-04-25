package com.example.rest.service.impl;

import com.example.model.LegalCase;
import com.example.repository.LegalCaseRepository;
import com.example.rest.dto.LegalCaseDto;
import com.example.rest.mapper.LegalCaseMapper;
import com.example.rest.service.LegalCaseService;
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
        return legalCaseMapper.toDto(legalCaseRepository.findById(id).orElse(null));
    }


    @Override
    public void deleteById(Long id) {
        legalCaseRepository.deleteById(id);
    }

}




