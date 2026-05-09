package com.example.rest.service.impl;

import com.example.model.CourtSession;
import com.example.model.LegalCase;
import com.example.repository.CourtSessionRepository;
import com.example.repository.LegalCaseRepository;
import com.example.rest.dto.CourtSessionDto;
import com.example.rest.mapper.CourtSessionMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourtSessionServiceImplTest {

    @Mock
    private CourtSessionRepository courtSessionRepository;

    @Mock
    private CourtSessionMapper courtSessionMapper;

    @Mock
    private LegalCaseRepository legalCaseRepository;

    @InjectMocks
    private RestCourtSessionServiceImpl courtSessionService;

    private CourtSession courtSession;
    private CourtSessionDto courtSessionDto;
    private LegalCase legalCase;

    @BeforeEach
    void setUp() {
        legalCase = new LegalCase();
        legalCase.setId(10L);

        courtSession = new CourtSession();
        courtSession.setId(1L);
        courtSession.setCourtroom("Room 101");
        courtSession.setSessionDate(LocalDateTime.now().plusDays(1));
        courtSession.setLegalCase(legalCase);

        courtSessionDto = CourtSessionDto.builder()
                .id(1L)
                .courtroom("Room 101")
                .sessionDate(LocalDateTime.now().plusDays(1))
                .caseId(10L)
                .build();
    }


    @Test
    void findAll_ShouldReturnDtoList() {
        when(courtSessionRepository.findAll()).thenReturn(List.of(courtSession));
        when(courtSessionMapper.toDtoList(anyList())).thenReturn(List.of(courtSessionDto));

        List<CourtSessionDto> result = courtSessionService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Room 101", result.get(0).getCourtroom());

        verify(courtSessionRepository).findAll();
        verify(courtSessionMapper).toDtoList(anyList());
    }


    @Test
    void findById_ShouldReturnDto_WhenExists() {
        when(courtSessionRepository.findById(1L)).thenReturn(Optional.of(courtSession));
        when(courtSessionMapper.toDto(courtSession)).thenReturn(courtSessionDto);

        CourtSessionDto result = courtSessionService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(courtSessionRepository).findById(1L);
        verify(courtSessionMapper).toDto(courtSession);
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(courtSessionRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () ->
                courtSessionService.findById(1L)
        );

        assertTrue(ex.getMessage().contains("CourtSession"));

        verify(courtSessionRepository).findById(1L);
    }

    @Test
    void save_ShouldReturnSavedDto() {
        when(legalCaseRepository.findById(10L)).thenReturn(Optional.of(legalCase));
        when(courtSessionMapper.toEntity(courtSessionDto)).thenReturn(courtSession);
        when(courtSessionRepository.save(any(CourtSession.class))).thenReturn(courtSession);
        when(courtSessionMapper.toDto(courtSession)).thenReturn(courtSessionDto);

        CourtSessionDto result = courtSessionService.save(courtSessionDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(legalCaseRepository).findById(10L);
        verify(courtSessionRepository).save(any(CourtSession.class));
        verify(courtSessionMapper).toDto(courtSession);
    }

    @Test
    void save_ShouldThrowException_WhenCaseNotFound() {
        when(legalCaseRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                courtSessionService.save(courtSessionDto)
        );

        verify(legalCaseRepository).findById(10L);
        verify(courtSessionRepository, never()).save(any());
    }




    @Test
    void update_ShouldThrowException_WhenNotFound() {
        when(courtSessionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                courtSessionService.update(1L, courtSessionDto)
        );

        verify(courtSessionRepository).findById(1L);
        verify(courtSessionRepository, never()).save(any());
    }


    @Test
    void deleteById_ShouldDelete_WhenExists() {
        when(courtSessionRepository.existsById(1L)).thenReturn(true);

        courtSessionService.deleteById(1L);

        verify(courtSessionRepository).deleteById(1L);
    }

    @Test
    void deleteById_ShouldThrowException_WhenNotExists() {
        when(courtSessionRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () ->
                courtSessionService.deleteById(1L)
        );

        verify(courtSessionRepository, never()).deleteById(any());
    }
}