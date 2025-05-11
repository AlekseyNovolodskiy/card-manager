package com.managingCardsOfBank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @SequenceGenerator(name = "roleSequence", sequenceName = "role_sequence", allocationSize = 1, initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

}
