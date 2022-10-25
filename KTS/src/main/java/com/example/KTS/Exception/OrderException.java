package com.example.KTS.Exception;

public class OrderException extends RuntimeException{
    public OrderException(String name){
        super("Order " + name + " does not exist.");
    }
}
