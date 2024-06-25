package com.example.portalsecurity.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^(male|female)$", message = "{validation.gender}")
    private String gender;
    private LocalDate birthdayDate;
    @NotNull
    //@Digits(integer = 10, fraction = 0)
    @Pattern(regexp = "\\d{10}")
    private String nationalId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Long age;

    public Long getAge() {
        if (birthdayDate != null) {
            age = (long) Period.between(birthdayDate, LocalDate.now()).getYears();
        }
        return age;
    }
}