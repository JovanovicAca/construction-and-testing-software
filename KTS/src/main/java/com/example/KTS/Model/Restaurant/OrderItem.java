package com.example.KTS.Model.Restaurant;

import com.example.KTS.Model.Enums.DISH_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private DISH_STATUS dish_status;
    private Double quantity;
    private String allergyDescription;
    @JsonIgnoreProperties(value = {"orderItem"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
    private MenuItem menuItem;
    @ManyToMany(mappedBy = "orderItems",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();


    public OrderItem(){}


    public OrderItem(MenuItem menuItem, double quantity, DISH_STATUS dish_status,String allergyDescription) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.dish_status = dish_status;
        this.allergyDescription = allergyDescription;

    }
}
