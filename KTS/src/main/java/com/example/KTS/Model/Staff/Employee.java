package com.example.KTS.Model.Staff;

import javax.persistence.*;

@Entity
public class Employee extends Client{


    public Employee() {
    }

    public Employee(String name, String surname, double paycheck, String password) {
        super(name, surname, paycheck, password);
    }

    public Employee(String name, String surname, double paycheck, String username, String password) {
        super(name, surname, paycheck, username, password);
    }

    @Override
    public String toString() {
        return "Employee{}";
    }
}
