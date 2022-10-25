package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {

    @Query(value = "SELECT order_item from OrderItem order_item join Dish dish on dish.id = order_item.menuItem.id where order_item.dish_status = 3")
    List<OrderItem> getAllAvailableDishes();

    @Query(value = "SELECT order_item from OrderItem order_item join Drink drink on drink.id = order_item.menuItem.id where order_item.dish_status = 3")
    List<OrderItem> getAllAvailableDrinks();

}
