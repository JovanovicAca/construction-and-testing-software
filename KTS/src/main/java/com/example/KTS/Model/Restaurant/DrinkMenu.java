package com.example.KTS.Model.Restaurant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DrinkMenu extends Menu{

    @JsonIgnoreProperties(value = {"drinkMenu","orderItem"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "drink_menu_drink",
            joinColumns = @JoinColumn(name = "drink_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private Set<Drink> drinks = new HashSet<Drink>();

}
