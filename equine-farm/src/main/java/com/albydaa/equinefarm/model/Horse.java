package com.albydaa.equinefarm.model;

import com.albydaa.equinefarm.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;

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
    private Integer age;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "child_parent",
    joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private Set<Horse> children;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "child_parent",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private Set<Horse> parents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_in_charge")
    private Doctor doctorInCharge;

    public void addChild(Horse horse){
        if(children == null)
            children = new HashSet<>();
        children.add(horse);
    }
    public void addParent(Horse horse){
        if(parents == null)
            parents = new HashSet<>();
        if(parents.size() == 2)
            return; // handle
        parents.add(horse);
    }
}
