package com.example.rest.service;

import com.example.rest.dto.CaseDocumentDto;
import com.example.rest.dto.CourtSessionDto;

import java.util.List;

public interface CaseDocumentService {

    List<CaseDocumentDto> findAll();

    CaseDocumentDto findById(Long id);

    CaseDocumentDto save(CaseDocumentDto caseDocumentDto);

    void deleteById(Long id);

}











