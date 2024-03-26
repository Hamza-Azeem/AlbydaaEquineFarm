package com.albydaa.equinefarm.model;

import com.albydaa.equinefarm.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Double salary;
    private Specialization specialization;
    @OneToMany(mappedBy = "doctorInCharge",cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @Column(name = "managed_horses")
    @JsonIgnore
    private Set<Horse> managedHorses;

    public void addHorse(Horse horse){
        if(managedHorses == null)
            managedHorses = new HashSet<>();
        managedHorses.add(horse);
    }

    public enum Specialization{
        SURGEON,
        ANESTHESIOLOGIST,
        TECHNICIAN,
        GENERAL
    }

}
