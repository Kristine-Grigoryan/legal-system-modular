package com.example.service.impl;

import com.example.model.CaseDocument;
import com.example.repository.CaseDocumentRepository;
import com.example.service.CaseDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class CaseDocumentServiceImpl implements CaseDocumentService {

    private final CaseDocumentRepository caseDocumentRepository;

    @Override
    public List<CaseDocument> findAll() {
        log.info("Fetching all caseDocuments");
        return caseDocumentRepository.findAll();
    }


    @Override
    public CaseDocument findById(Long id) {
        return caseDocumentRepository.findById(id).orElse(null);
    }

    @Override
    public CaseDocument save(CaseDocument caseDocument) {
        return caseDocumentRepository.save(caseDocument);
    }


    @Override
    public void deleteById(Long id) {
        caseDocumentRepository.deleteById(id);
    }

}