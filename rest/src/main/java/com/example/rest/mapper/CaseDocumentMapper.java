package com.example.rest.mapper;

import com.example.model.CaseDocument;
import com.example.model.CourtSession;
import com.example.model.LegalCase;
import com.example.model.Payment;
import com.example.rest.dto.CaseDocumentDto;
import com.example.rest.dto.CourtSessionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CaseDocumentMapper {



    @Mapping(source = "caseId", target = "legalCase.id")
    CaseDocument toEntity(CaseDocumentDto dto);


    @Mapping(source = "legalCase.id", target = "caseId")
    CaseDocumentDto toDto (CaseDocument entity);

    List<CaseDocumentDto> toDtoList(List<CaseDocument> all);



}
