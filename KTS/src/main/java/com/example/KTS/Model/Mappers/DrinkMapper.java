package com.example.KTS.Model.Mappers;

import com.example.KTS.Model.DTO.DrinkDTO;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.Ingredient;
import org.modelmapper.ModelMapper;

public class DrinkMapper {

    public static Drink convertToDrink(DrinkDTO drink) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(drink, Drink.class);
    }

}
