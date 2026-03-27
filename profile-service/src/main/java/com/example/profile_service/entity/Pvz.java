package com.example.profile_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pvz")
public class Pvz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id" , nullable = false)
    private Profile owner;


    @OneToMany(mappedBy = "pvz" , cascade = CascadeType.ALL  , fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

}
