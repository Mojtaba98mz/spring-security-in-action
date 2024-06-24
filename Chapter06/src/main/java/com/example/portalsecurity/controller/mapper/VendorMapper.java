package com.example.portalsecurity.controller.mapper;

import com.example.portalsecurity.controller.dto.VendorDto;
import com.example.portalsecurity.model.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDto toDto(Vendor entity);

    Vendor toEntity(VendorDto dto);

    @Mapping(target = "id", ignore = true)
    void toEntity(VendorDto dto, @MappingTarget Vendor entity);

    List<VendorDto> toDtoList(List<Vendor> entityList);

    List<Vendor> toEntityList(List<VendorDto> dtoList);
}
