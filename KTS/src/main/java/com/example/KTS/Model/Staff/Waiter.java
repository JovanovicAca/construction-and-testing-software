package com.example.KTS.Model.Staff;

import com.example.KTS.Model.Restaurant.RestaurantTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Waiter extends Employee {

    @JsonIgnore
    @OneToMany(mappedBy = "waiter", fetch = FetchType.EAGER)
    private Set<RestaurantTable> restaurantTableSet = new HashSet<>();

    public Waiter(){

    }

    public Waiter(String name, String surname, double paycheck, String password) {
        super(name, surname, paycheck, password);
    }

    public Set<RestaurantTable> getRestaurantTableSet() {
        return restaurantTableSet;
    }

    public void setRestaurantTableSet(Set<RestaurantTable> restaurantTableSet) {
        this.restaurantTableSet = restaurantTableSet;
    }
}
