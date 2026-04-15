package com.example.service.impl;
import com.example.model.*;
import com.example.repository.LegalCaseRepository;
import com.example.service.LegalCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class LegalCaseServiceImpl implements LegalCaseService {



    private final LegalCaseRepository legalCaseRepository;

    @Value("${legal.system.upload.image.directory.path}")
    private String imageDirectoryPath;


    @Override
    public Page<LegalCase> findAll(Pageable pageable) {
        return legalCaseRepository.findAll(pageable);
    }





    @Override
    public List<LegalCase> findByUser(User user) {

        return legalCaseRepository.findByUser(user);
    }

    @Override
    public Page<LegalCase> findAllWithSpecification(Specification<LegalCase> spec) {
        return legalCaseRepository.findAll(spec, Pageable.unpaged());
    }
        @Override
        public LegalCase save(LegalCase legalCase, MultipartFile multipartFile) {
            // 1. Եթե կա նկար՝ պահում ենք
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                File file = new File(imageDirectoryPath + File.separator + fileName);
                try {
                    multipartFile.transferTo(file);
                    legalCase.setPictureName(fileName);
                } catch (IOException e) {
                    log.error("Error while saving image for legalCase {}: {}, {}", legalCase.getTitle(), e.getMessage(), e.getStackTrace());
                }
            }
                    return legalCaseRepository.save(legalCase);
            }



    @Override
    public List<LegalCase> findAll() {
        return legalCaseRepository.findAll();

    }

        @Override
        public void deleteById(Long id) {
            legalCaseRepository.deleteById(id);
        }
    @Override
    public LegalCase findById(Long id) {
        return legalCaseRepository.findById(id).orElse(null);
    }

}
















