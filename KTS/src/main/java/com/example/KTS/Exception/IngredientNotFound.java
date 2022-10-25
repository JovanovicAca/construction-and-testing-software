package com.example.KTS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredientNotFound extends RuntimeException{
    public IngredientNotFound(String name){
        super("Ingredient " + name + " does not exist.");
    }
}
