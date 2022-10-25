package com.example.KTS.Model.Restaurant;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor

public class DishMenu extends Menu{

    @JsonIgnoreProperties(value = {"dishMenu","orderItem"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dish_menu_dish",
            joinColumns = @JoinColumn(name = "dish_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private Set<Dish> dishes = new HashSet<Dish>();

    public DishMenu() {
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "DishMenu{" + super.toString() +
                "dishes=" + dishes +
                '}';
    }
}
