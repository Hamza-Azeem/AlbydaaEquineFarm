package com.albydaa.equinefarm.model;

import com.albydaa.equinefarm.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Horse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "horse_name")
    private String name;
    private String breed;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Horse parent;
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Horse> children = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_in_charge")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Doctor doctorInCharge;

    public void addChild(Horse horse){
        children.add(horse);
    }
}
