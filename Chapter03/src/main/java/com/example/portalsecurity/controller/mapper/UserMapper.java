package com.example.portalsecurity.controller.mapper;

import com.example.portalsecurity.controller.dto.UserDto;
import com.example.portalsecurity.model.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(Users entity);

    Users toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)//Ignore mapping the ID from the DTO
    void toEntity(UserDto dto, @MappingTarget Users entity);

    List<UserDto> toDtoList(List<Users> entityList);

    List<Users> toEntityList(List<UserDto> dtoList);
}