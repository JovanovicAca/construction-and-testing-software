package com.example.KTS.Config;

import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Restaurant.RestaurantTable;
import com.example.KTS.Repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RestaurantTableConfig {
    @Bean
    CommandLineRunner restaurantTableCMDRunner(TableRepo tableRepo, ClientRepo clientRepo, MenuItemRepo menuItemRepo,
                                               OrderRepo orderRepo, OrderItemRepo orderItemRepo){
        return args -> {
            RestaurantTable restaurantTable01 = new RestaurantTable();
            restaurantTable01.setTableStatus(TABLE_STATUS.EMPTY);
            restaurantTable01.setTableNumber("01");
            restaurantTable01.setRows(1);
            restaurantTable01.setCols(1);
           // CommandLineRunner restaurantTableCMDRunner(TableRepo tableRepo){
       /* return args -> {
            RestaurantTable restaurantTable01 = new RestaurantTable();
            restaurantTable01.setTableStatus(TABLE_STATUS.EMPTY);
            restaurantTable01.setTableNumber("01");
            restaurantTable01.setRows(1);
            restaurantTable01.setCols(1);*/

//            Waiter waiter = (Waiter)clientRepo.findById(10L).get();
//
//            RestaurantTable restaurantTable01 = new RestaurantTable();
//            restaurantTable01.setTableStatus(TABLE_STATUS.EMPTY);
//            restaurantTable01.setNumberOfSeats(5);
//            restaurantTable01.setTableNumber("01");
//
//            RestaurantTable restaurantTable02 = new RestaurantTable();
//            restaurantTable02.setTableStatus(TABLE_STATUS.RESERVED);
//            restaurantTable02.setNumberOfSeats(2);
//            restaurantTable02.setTableNumber("02");
//
//            RestaurantTable restaurantTable03 = new RestaurantTable();
//            restaurantTable03.setTableStatus(TABLE_STATUS.RESERVED);
//            restaurantTable03.setNumberOfSeats(2);
//            restaurantTable03.setTableNumber("03");
//
//            RestaurantTable restaurantTable04 = new RestaurantTable();
//            restaurantTable04.setTableStatus(TABLE_STATUS.EMPTY);
//            restaurantTable04.setNumberOfSeats(5);
//            restaurantTable04.setTableNumber("04");
//
//            RestaurantTable restaurantTable05 = new RestaurantTable();
//            restaurantTable05.setTableStatus(TABLE_STATUS.OCCUPIED);
//            restaurantTable05.setNumberOfSeats(5);
//            restaurantTable05.setTableNumber("05");
//            restaurantTable05.setWaiter(waiter);
//
//            Order order = new Order();
//            order.setOrderStatus(ORDER_STATUS.PENDING);
//            order.setRestaurantTable(restaurantTable05);
//
//            OrderItem orderItem01 = new OrderItem();
//            orderItem01.setDish_status(DISH_STATUS.ORDER_NOT_TAKEN);
//            orderItem01.setQuantity(2.0);
//            orderItem01.setAllergyDescription("Alergican na Tomatino sos");
//            Dish dish01 = (Dish) menuItemRepo.findById(31L).get();
//            orderItem01.setMenuItem(dish01);
//
//            OrderItem orderItem02 = new OrderItem();
//            orderItem02.setDish_status(DISH_STATUS.SERVED);
//            orderItem02.setQuantity(3.0);
//            orderItem02.setAllergyDescription("Nema Napomene");
//            Dish dish02 = (Dish) menuItemRepo.findById(32L).get();
//            orderItem02.setMenuItem(dish02);
//
//            OrderItem orderItem03 = new OrderItem();
//            orderItem03.setDish_status(DISH_STATUS.SERVED);
//            orderItem03.setQuantity(5.0);
//            orderItem03.setAllergyDescription("Nema Napomene");
//            Drink drink = (Drink) menuItemRepo.findById(27L).get();
//            orderItem03.setMenuItem(drink);
//
//            OrderItem orderItem04 = new OrderItem();
//            orderItem04.setDish_status(DISH_STATUS.SERVED);
//            orderItem04.setQuantity(1.0);
//            orderItem04.setAllergyDescription("Nema Napomene");
//            Dish dish03 = (Dish) menuItemRepo.findById(35L).get();
//            orderItem04.setMenuItem(dish03);
//
//            orderItemRepo.saveAll(List.of(orderItem01,orderItem02,orderItem03,orderItem04));
//
//            order.setOrderItems(List.of(orderItem01,orderItem02,orderItem03,orderItem04));
//            restaurantTable05.setOrder(order);
//
//            orderRepo.save(order);
//
//            RestaurantTable restaurantTable06 = new RestaurantTable();
//            restaurantTable06.setTableStatus(TABLE_STATUS.EMPTY);
//            restaurantTable06.setNumberOfSeats(8);
//            restaurantTable06.setTableNumber("06");
              tableRepo.saveAll(List.of(restaurantTable01));
//            tableRepo.saveAll(List.of(restaurantTable01,restaurantTable02,restaurantTable03,
//                                    restaurantTable04,restaurantTable05,restaurantTable06));


        };
    }
}
