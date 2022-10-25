package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Restaurant.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

    private String name;
    private Boolean isAllergic;
    private Double price;

    public IngredientDTO(Ingredient ingredient){
        this.name = ingredient.getName();
        this.isAllergic = ingredient.getIsAllergic();
        this.price = ingredient.getPrice();
    }
}
