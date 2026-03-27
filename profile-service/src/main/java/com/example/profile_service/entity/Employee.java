package com.example.profile_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String secondName;
    private String surName;
    private String phone;
    private String description;
    private String bank;
    private BigDecimal fixedRatePerHour;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pvz_id" )
    private Pvz pvz;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "employee" , orphanRemoval = true)
    List<Shift> shifts = new ArrayList<>();
}
