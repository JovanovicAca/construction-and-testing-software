package com.example.KTS.Model.Staff;

import com.google.common.hash.Hashing;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Entity
public class Administration extends Client{
    public Administration(){

    }

    public Administration(String name, String surname, double paycheck, String username, String password) {
        super(name, surname, paycheck, username, password);
    }


}
