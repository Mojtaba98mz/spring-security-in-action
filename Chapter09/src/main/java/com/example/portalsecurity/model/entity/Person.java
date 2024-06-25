package com.example.portalsecurity.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @Column(name = "national_Id")
    private String nationalId;

    @Transient
    private Long age;
}