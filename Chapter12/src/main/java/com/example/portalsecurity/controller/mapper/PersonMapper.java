package com.example.portalsecurity.controller.mapper;

import com.example.portalsecurity.controller.dto.PersonDto;
import com.example.portalsecurity.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDto toDto(Person entity);

    Person toEntity(PersonDto dto);

    @Mapping(target = "id", ignore = true)
        //Ignore mapping the ID from the DTO
    void toEntity(PersonDto dto, @MappingTarget Person entity);

    List<PersonDto> toDtoList(List<Person> entityList);

    List<Person> toEntityList(List<PersonDto> dtoList);
}