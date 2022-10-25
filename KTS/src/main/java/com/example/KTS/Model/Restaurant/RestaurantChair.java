package com.example.KTS.Model.Restaurant;

import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Staff.Bartender;
import com.example.KTS.Model.Staff.Waiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant_chair")
public class RestaurantChair{
    @Id
    @GeneratedValue
    private Long id;

    private TABLE_STATUS tableStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private Bartender bartender;

//    @OneToMany(mappedBy = "bartender", fetch = FetchType.EAGER)
//    private Set<Drink> drinkSet = new HashSet<>();

    private float x;
    private float y;
    private String tableNumber;
}