package com.example.service;

import com.model.CaseDocument;

import java.util.List;

public interface CaseDocumentService {

    List<CaseDocument> findAll();

    CaseDocument save(CaseDocument caseDocument);


    CaseDocument findById(Long id);

    void deleteById(Long id);

}


