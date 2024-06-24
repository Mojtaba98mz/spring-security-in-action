package com.example.portalsecurity.service;

import com.example.portalsecurity.common.exception.ApiRequestException;
import com.example.portalsecurity.controller.dto.VendorDto;
import com.example.portalsecurity.controller.mapper.VendorMapper;
import com.example.portalsecurity.model.entity.Vendor;
import com.example.portalsecurity.model.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService implements BusinessService<VendorDto, Long> {

    private final VendorRepository VendorRepository;

    public VendorService(VendorRepository VendorRepository) {
        this.VendorRepository = VendorRepository;
    }

    @Override
    public List<VendorDto> findAll() {
        List<Vendor> VendorList = VendorRepository.findAll();
        return VendorMapper.INSTANCE.toDtoList(VendorList);
    }

    @Override
    public VendorDto findById(Long id) {
        Vendor Vendor = VendorRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Vendor is not exist with id :" + id));
        return VendorMapper.INSTANCE.toDto(Vendor);
    }

    @Override
    public VendorDto insert(VendorDto VendorDto) {
        Vendor Vendor = VendorMapper.INSTANCE.toEntity(VendorDto);
        Vendor newVendor = VendorRepository.save(Vendor);
        //Vendor = VendorRepository.save(Vendor);
        return VendorMapper.INSTANCE.toDto(newVendor);
    }

    @Override
    public VendorDto update(Long id, VendorDto VendorDto) {
        Vendor loadedVendor = VendorRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Vendor is not exist with id :" + id));
        VendorMapper.INSTANCE.toEntity(VendorDto, loadedVendor);
        Vendor newVendor = VendorRepository.save(loadedVendor);
        return VendorMapper.INSTANCE.toDto(newVendor);
    }

    @Override
    public void delete(Long id) {
        Vendor loadedVendor = VendorRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Vendor is not exist with id :" + id));
        VendorRepository.delete(loadedVendor);
    }
}