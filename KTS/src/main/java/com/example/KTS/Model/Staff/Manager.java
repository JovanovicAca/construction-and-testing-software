package com.example.KTS.Model.Staff;

import javax.persistence.Entity;

@Entity
public class Manager extends Administration {

    public Manager() {
        super();
    }

    public Manager(String name, String surname, double paycheck, String username, String password) {
        super(name, surname, paycheck, username, password);
    }
}
