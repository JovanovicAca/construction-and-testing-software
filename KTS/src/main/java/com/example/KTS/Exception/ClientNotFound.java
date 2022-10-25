package com.example.KTS.Exception;

public class ClientNotFound extends RuntimeException{

    public ClientNotFound(String username) {
        super("Client " + username + " does not exist.");
    }
}
