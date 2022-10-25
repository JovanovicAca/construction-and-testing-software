package com.example.KTS.Model.Staff;

import com.example.KTS.Model.Restaurant.RestaurantChair;
import com.example.KTS.Model.Restaurant.RestaurantTable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bartender extends Employee {

    public Bartender() {
        super();
    }

//    @OneToMany(mappedBy = "bartender", fetch = FetchType.EAGER)
//    private Set<RestaurantChair> restaurantChairs = new HashSet<>();
//
//    public Set<RestaurantChair> getRestaurantChairs() {
//        return restaurantChairs;
//    }
//
//    public void setRestaurantChairs(Set<RestaurantChair> restaurantChairs) {
//        this.restaurantChairs = restaurantChairs;
//    }
}
