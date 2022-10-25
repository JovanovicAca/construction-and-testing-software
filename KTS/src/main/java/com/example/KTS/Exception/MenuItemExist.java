package com.example.KTS.Exception;

public class MenuItemExist extends RuntimeException{

    public MenuItemExist(String name){
        super("Menu item " + name + " already exists.");
    }
}
