package com.example.rest.mapper;

import com.example.model.LegalCase;
import com.example.rest.dto.LegalCaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class LegalCaseMapper {

    @Mapping(target = "courseName", source = "student.course.name")
    public abstract LegalCaseDto toDto(LegalCase legalCase);

    public abstract List<LegalCaseDto> toDtoList(List<LegalCaseDto> legalCases);

}
