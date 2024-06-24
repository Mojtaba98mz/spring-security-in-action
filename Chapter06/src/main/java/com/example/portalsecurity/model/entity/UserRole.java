package com.example.portalsecurity.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;
}