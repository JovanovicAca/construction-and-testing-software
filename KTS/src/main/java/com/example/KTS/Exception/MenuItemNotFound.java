package com.example.KTS.Exception;

public class MenuItemNotFound extends RuntimeException{

    public MenuItemNotFound(String name){
        super("Menu item " + name + " does not exist.");
    }
}
