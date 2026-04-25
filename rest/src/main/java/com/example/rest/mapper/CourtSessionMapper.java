package com.example.rest.mapper;
import com.example.model.CourtSession;
import com.example.rest.dto.CourtSessionDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourtSessionMapper {

    @Mapping(source = "legalCase.id", target = "caseId")
    @Mapping(source = "id", target = "id")
    CourtSessionDto toDto(CourtSession entity);


    @Mapping(source = "caseId", target = "legalCase.id")
    CourtSession toEntity(CourtSessionDto dto);

    List<CourtSessionDto> toDtoList(List<CourtSession> all);
}
