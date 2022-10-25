package com.example.KTS.Exception;

public class DrinkException extends RuntimeException{
    public DrinkException(){
        super("No drinks in the restaurant!");
    }
}
