package com.example.KTS.Model.Restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    private String name;
    private Boolean isAllergic;
    private Double price;
    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients",cascade = CascadeType.ALL)
    private Set<MenuItem> menuItem = new HashSet<>();

    public Ingredient(){

    }

    public Ingredient(Long id, String name, Boolean isAllergic, Double price) {
        Id = id;
        this.name = name;
        this.isAllergic = isAllergic;
        this.price = price;
    }


}
