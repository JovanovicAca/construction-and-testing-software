package com.example.KTS.Exception;

public class IngredientNotFoundException extends RuntimeException{
    public IngredientNotFoundException(){
        super("Ingredient with that name does not exists!");
    }
}

