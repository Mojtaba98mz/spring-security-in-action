package com.example.portalsecurity.controller.dto;

import com.example.portalsecurity.model.entity.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VendorDto {

    private Long id;
    @NotBlank
    private String code;
    @NotBlank (message = "Nationality Can not be null")
    private String nationality;
    @Email
    private String email;
    @NotBlank
    private Person person;
}