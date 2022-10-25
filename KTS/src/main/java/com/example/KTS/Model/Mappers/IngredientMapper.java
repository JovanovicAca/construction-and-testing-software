package com.example.KTS.Model.Mappers;

import com.example.KTS.Model.DTO.IngredientDTO;
import com.example.KTS.Model.Restaurant.Ingredient;
import org.modelmapper.ModelMapper;

public class IngredientMapper {

    public IngredientDTO convertToIngredientDto(Ingredient ingredient) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(ingredient, IngredientDTO.class);
    }
    public static Ingredient convertToIngredient(IngredientDTO ingredient) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(ingredient, Ingredient.class);
    }
}
