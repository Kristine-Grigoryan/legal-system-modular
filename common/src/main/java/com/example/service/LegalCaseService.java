package com.example.service;


import com.example.model.LegalCase;
import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface LegalCaseService {




        Page<LegalCase> findAll(Pageable pageable);

        LegalCase save(LegalCase legalCase, MultipartFile multipartFile);

        List<LegalCase> findByUser(User user);

        Page<LegalCase> findAllWithSpecification(Specification<LegalCase> spec);

    }


