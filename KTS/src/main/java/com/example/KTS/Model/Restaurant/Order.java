package com.example.KTS.Model.Restaurant;

import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private ORDER_STATUS orderStatus;
    private double order_price;
    //private LocalDateTime dateFinished;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_table_id", referencedColumnName = "id")
    private RestaurantTable restaurantTable;

    @JsonIgnoreProperties(value = {"orders"})
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders_order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "order_item_id"))
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(){

    };

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ORDER_STATUS getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(ORDER_STATUS orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        this.order_price += orderItem.getQuantity() * orderItem.getMenuItem().getPrice();
    }
}
