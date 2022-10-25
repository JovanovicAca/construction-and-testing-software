package com.example.KTS.Exception;

public class ClientExists extends RuntimeException{

    public ClientExists(String username) {
        super("Client " + username + " already exists!");
    }
}
