package com.example.rest.mapper;

import com.example.model.User;
import com.example.rest.dto.SaveUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "token", source = "token")
    UserAuthResponse toDto(User user, String token);

    @Mapping(target = "role", source = "role")
    @Mapping(target = "cases", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "verificationCode", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "id", ignore = true)

    User toEntity(SaveUserRequest request);
}