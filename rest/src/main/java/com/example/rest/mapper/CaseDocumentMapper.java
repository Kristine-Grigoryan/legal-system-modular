package com.example.rest.mapper;

import com.example.model.CaseDocument;
import com.example.model.LegalCase;
import com.example.model.Payment;
import com.example.rest.dto.CaseDocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CaseDocumentMapper {



    @Mapping(source = "caseId", target = "legalCase")
    CaseDocument toEntity(CaseDocumentDto dto);

    @Mapping(source = "legalCase.id", target = "caseId")
    CaseDocumentDto toDTO(CaseDocument entity);

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
