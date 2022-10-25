package com.example.KTS.Exception;

public class MenuExpection extends RuntimeException{
    public MenuExpection(String name){
        super("Menu item " + name + " error.");
    }
}
