package com.example.rest.service;

import com.example.rest.dto.CourtSessionDto;
import com.example.rest.dto.PaymentDto;

import java.util.List;

public interface CourtSessionService {

    List<CourtSessionDto> findAll();

    CourtSessionDto findById(Long id);

     CourtSessionDto save(CourtSessionDto courtSessionDto);

    CourtSessionDto update(Long id, CourtSessionDto courtSessionDto);

    void deleteById(Long id);

}











