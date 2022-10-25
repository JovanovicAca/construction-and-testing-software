package com.example.KTS.Model.Restaurant;

import com.example.KTS.Model.DTO.DrinkDTO;
import com.example.KTS.Model.Enums.DRINK_TYPE;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@JsonDeserialize(as = Drink.class)
public class Drink extends MenuItem {

    private DRINK_TYPE drinkType;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "drinks")
    private Set<DrinkMenu> drinkMenu = new HashSet<DrinkMenu>();

    public Drink() {
        super();
    }
    public Drink(DrinkDTO dto){
        setDrinkType(dto.getDrinkType());
        setPrice(dto.getPrice());
        setName(dto.getName());
        setDescription(dto.getDescription());
        setDiscount(dto.getDiscount());
        setPurchasePrice(dto.getPurchasePrice());
        setRecipe(dto.getRecipe());
    }

    public DRINK_TYPE getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DRINK_TYPE drinkType) {
        this.drinkType = drinkType;
    }
}
