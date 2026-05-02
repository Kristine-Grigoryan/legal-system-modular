package com.example.rest.service.impl;

import com.example.model.CaseDocument;
import com.example.model.LegalCase;
import com.example.repository.CaseDocumentRepository;
import com.example.repository.LegalCaseRepository;
import com.example.rest.dto.CaseDocumentDto;
import com.example.rest.mapper.CaseDocumentMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CaseDocumentServiceImplTest {


        @Mock
        private CaseDocumentRepository caseDocumentRepository;

        @Mock
        private CaseDocumentMapper caseDocumentMapper;

        @Mock
        private LegalCaseRepository legalCaseRepository;

        @InjectMocks
        private RestCaseDocumentServiceImpl caseDocumentService;

        private CaseDocument caseDocument;
        private CaseDocumentDto caseDocumentDto;
        private LegalCase legalCase;

        @BeforeEach
        void setUp() {
            caseDocumentDto = CaseDocumentDto.builder()
                    .id(1L)
                    .caseId(10L)
                    .fileName("test_doc.pdf")
                    .filePath("/docs/test_doc.pdf")
                    .fileType("application/pdf")
                     .build();


            caseDocument = new CaseDocument();
            caseDocument.setId(1L);
            caseDocument.setFileName("test_doc.pdf");


            legalCase = new LegalCase();
            legalCase.setId(10L);
        }

        @Test
        void testSave_Success() {
            when(legalCaseRepository.findById(10L)).thenReturn(Optional.of(legalCase));
            when(caseDocumentMapper.toEntity(any(CaseDocumentDto.class))).thenReturn(caseDocument);
            when(caseDocumentRepository.save(any(CaseDocument.class))).thenReturn(caseDocument);
            when(caseDocumentMapper.toDto(caseDocument)).thenReturn(caseDocumentDto);


            CaseDocumentDto result = caseDocumentService.save(caseDocumentDto);


            assertNotNull(result);
            assertEquals(1L, result.getId());
            verify(caseDocumentRepository, times(1)).save(any());
        }
    @Test
    void testFindById_Success() {
        when(caseDocumentRepository.findById(1L)).thenReturn(Optional.of(caseDocument));
        when(caseDocumentMapper.toDto(caseDocument)).thenReturn(caseDocumentDto);

        CaseDocumentDto result = caseDocumentService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_ThrowsException_WhenNotFound() {

        when(caseDocumentRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> {
            caseDocumentService.findById(1L);
        });
    }



        @Test
        void testDeleteById_Success() {
            caseDocumentService.deleteById(1L);


            verify(caseDocumentRepository, times(1)).deleteById(1L);
        }
    }
