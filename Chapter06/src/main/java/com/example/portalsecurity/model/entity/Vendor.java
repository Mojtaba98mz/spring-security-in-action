package com.example.portalsecurity.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "nationality")
    private String nationality;

    @Email
    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Person")
    @NonNull
    private Person person;
}