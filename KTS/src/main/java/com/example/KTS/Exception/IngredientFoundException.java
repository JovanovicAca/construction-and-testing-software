package com.example.KTS.Exception;

public class IngredientFoundException extends RuntimeException{
    public IngredientFoundException(){
        super("Ingredient with that name already exists!");
    }
}
