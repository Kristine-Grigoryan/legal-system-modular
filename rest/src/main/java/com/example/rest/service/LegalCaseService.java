package com.example.rest.service;

import com.example.rest.dto.LegalCaseDto;

import java.util.List;

public interface LegalCaseService {


    List<LegalCaseDto> findAll();

    LegalCaseDto save(LegalCaseDto legalCaseDto);

    LegalCaseDto findById(Long id);

    void deleteById(Long id);

}









