package com.example.portalsecurity.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
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