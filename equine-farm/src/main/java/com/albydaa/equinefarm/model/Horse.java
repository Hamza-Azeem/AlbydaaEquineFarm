package com.albydaa.equinefarm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "horses")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "horse_name")
    private String name;
    private String breed;
    private Double price;
    @ManyToOne
    private Horse parent;
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Horse> children = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "doctor_in_charge")
    private Doctor doctorInCharge;

    public void addChild(Horse horse){
        children.add(horse);
    }
}
