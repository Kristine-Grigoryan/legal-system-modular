package com.example.rest.mapper;
import com.example.model.CourtSession;
import com.example.model.LegalCase;
import com.example.rest.dto.CourtSessionDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourtSessionMapper {



    @Mapping(source = "caseId", target = "legalCase")
    CourtSession toEntity(CourtSessionDto dto);

    @Mapping(source = "legalCase.id", target = "caseId")
    CourtSessionDto toDTO(CourtSession entity);

    default LegalCase map(Long caseId) {
        if (caseId == null) return null;

        LegalCase lc = new LegalCase();
        lc.setId(caseId);
        return lc;
    }

    default Long map(LegalCase legalCase) {
        return legalCase != null ? legalCase.getId() : null;
    }
}