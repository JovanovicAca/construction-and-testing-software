package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Enums.DISH_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishNoIngredientsDTO {
    private String name;
    private String description;
    private Double price;
    private Double prepareTime;
    private DISH_TYPE dishType;
}
