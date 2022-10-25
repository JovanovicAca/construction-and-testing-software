package com.example.KTS.Exception;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(){
        super("No dishes in the restaurant!");
    }
}
