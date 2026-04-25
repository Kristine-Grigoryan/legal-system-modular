package com.example.rest.service.impl;
import com.example.model.CaseDocument;
import com.example.model.CourtSession;
import com.example.model.LegalCase;
import com.example.repository.CaseDocumentRepository;
import com.example.repository.CourtSessionRepository;
import com.example.repository.LegalCaseRepository;
import com.example.rest.dto.CaseDocumentDto;
import com.example.rest.dto.CourtSessionDto;
import com.example.rest.mapper.CaseDocumentMapper;
import com.example.rest.mapper.CourtSessionMapper;
import com.example.rest.service.CaseDocumentService;
import com.example.rest.service.CourtSessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestCaseDocumentServiceImpl implements CaseDocumentService {


    private final CaseDocumentMapper caseDocumentMapper;
    private final CaseDocumentRepository caseDocumentRepository;
    private  final LegalCaseRepository legalCaseRepository;

    @Override
    public List<CaseDocumentDto> findAll() {
        return caseDocumentMapper.toDtoList(caseDocumentRepository.findAll());

    }

    @Override
    public CaseDocumentDto findById(Long id) {
        return caseDocumentRepository.findById(id)
                .map(caseDocumentMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CaseDocument not found with id " + id));
    }


    @Override
    public CaseDocumentDto save(CaseDocumentDto caseDocumentDto) {
        LegalCase legalCase = legalCaseRepository.findById(caseDocumentDto.getCaseId())
                .orElseThrow(() -> new EntityNotFoundException("Case not found"));
        CaseDocument entity = caseDocumentMapper.toEntity(caseDocumentDto);
        entity.setLegalCase(legalCase);
        CaseDocument savedEntity = caseDocumentRepository.save(entity);
        return caseDocumentMapper.toDto(savedEntity);
    }




    @Override
    public void deleteById(Long id) {
        if (!caseDocumentRepository.existsById(id)) {
            throw new EntityNotFoundException("CaseDocument not found");
        }
        caseDocumentRepository.deleteById(id);
    }
}
