package com.example.rest.mapper;

import com.example.model.CaseDocument;
import com.example.model.LegalCase;
import com.example.rest.dto.CaseDocumentDto;
import com.example.rest.dto.LegalCaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CaseDocumentMapper {

    @Mapping(target = "courseName", source = "student.course.name")
    public abstract CaseDocumentDto toDto(CaseDocument CaseDocument);

    public abstract List<CaseDocument> toDtoList(List<CaseDocumentDto> CaseDocuments);

}
