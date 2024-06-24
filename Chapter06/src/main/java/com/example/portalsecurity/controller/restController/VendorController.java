package com.example.portalsecurity.controller.restController;

import com.example.portalsecurity.controller.dto.VendorDto;
import com.example.portalsecurity.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/portal/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<VendorDto> loadVendorList() {
        return vendorService.findAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<VendorDto> loadVendorById(@PathVariable("id") Long id) {
        VendorDto vendor = vendorService.findById(id);
        return ResponseEntity.ok(vendor);
    }

    @PostMapping
    public VendorDto insertVendor(@Valid @RequestBody VendorDto vendorDto) {
        return vendorService.insert(vendorDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<VendorDto> updateVendor(@PathVariable Long id, @Valid @NonNull @RequestBody VendorDto vendorDto) {
        VendorDto vendor = vendorService.update(id, vendorDto);
        return ResponseEntity.ok(vendor);
    }

    @DeleteMapping("{id}")
    public void deleteVendorById(@PathVariable Long id) {
        vendorService.delete(id);
    }
}