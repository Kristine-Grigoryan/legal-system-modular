package com.example.rest.mapper;

import com.example.model.User;
import com.example.rest.dto.SaveUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "role")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cases", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "verificationCode", ignore = true)
    User toEntity(SaveUserRequest request);
    @Mapping(source = "id", target = "userId")
    UserAuthResponse toDto(User user);
}