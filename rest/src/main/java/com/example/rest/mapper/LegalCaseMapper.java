package com.example.rest.mapper;

import com.example.model.LegalCase;
import com.example.rest.dto.LegalCaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface LegalCaseMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "status", source = "status")
    LegalCase toEntity(LegalCaseDto dto);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "status", source = "status")
    LegalCaseDto toDto(LegalCase entity);
}
