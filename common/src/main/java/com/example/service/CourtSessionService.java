package com.example.service;


import com.example.model.CourtSession;

import java.util.List;

public interface CourtSessionService {

    List<CourtSession> findAll();

    CourtSession save(CourtSession courtSession);

    CourtSession findById(Long id);

    void deleteById(Long id);


}


