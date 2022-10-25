package com.example.KTS.Exception;


public class OrderItemExpection extends RuntimeException{
    public OrderItemExpection(String name){
        super("Order item" + name + " does not exist.");
    }
}
