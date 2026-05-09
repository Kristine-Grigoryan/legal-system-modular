package com.example.rest.service.impl;

import com.example.model.CaseDocument;
import com.example.model.LegalCase;
import com.example.model.Status;
import com.example.repository.CaseDocumentRepository;
import com.example.repository.LegalCaseRepository;
import com.example.rest.dto.CaseDocumentDto;
import com.example.rest.dto.LegalCaseDto;
import com.example.rest.mapper.CaseDocumentMapper;
import com.example.rest.mapper.LegalCaseMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class LegalCaseServiceImplTest {


    @Mock
    private LegalCaseRepository legalCaseRepository;

    @Mock
    private LegalCaseMapper legalCaseMapper;

    @InjectMocks
    private RestLegalCaseServiceImpl legalCaseService;

    @Test
    void testSave_Success() {
        LegalCaseDto dto = new LegalCaseDto();
        dto.setTitle("Test Case");

        LegalCase entity = new LegalCase();
        entity.setTitle("Test Case");

        LegalCase savedEntity = new LegalCase();
        savedEntity.setId(1L);
        savedEntity.setTitle("Test Case");

        when(legalCaseMapper.toEntity(dto)).thenReturn(entity);
        when(legalCaseRepository.save(entity)).thenReturn(savedEntity);
        when(legalCaseMapper.toDto(savedEntity)).thenReturn(dto);


        LegalCaseDto result = legalCaseService.save(dto);


        assertNotNull(result);
        verify(legalCaseRepository, times(1)).save(entity);
    }
}
