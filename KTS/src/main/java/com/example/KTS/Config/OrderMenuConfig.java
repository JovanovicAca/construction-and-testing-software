package com.example.KTS.Config;

import com.example.KTS.Model.Enums.DISH_TYPE;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.*;
import com.example.KTS.Repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Configuration
public class OrderMenuConfig {
    @Bean
    CommandLineRunner orderMenuCommandLineRunner(OrderRepo orderRepo, OrderItemRepo orderItemRepo, DishRepo dishRepo, DrinkRepo drinkRepo,
                                                 DishMenuRepo dishMenuRepo,DrinkMenuRepo drinkMenuRepo, MenuItemRepo menuItemRepo){
        return args -> {

            List<Dish> dishes = dishRepo.findAll();
            List<Drink> drinks = drinkRepo.findAll();

            DishMenu dishMenuDorucak = new DishMenu();
            dishMenuDorucak.setMenuName("Dorucak");
            dishMenuDorucak.setDishes(new HashSet<>());
            dishMenuDorucak.setStartDate(LocalDateTime.now());
            dishMenuDorucak.setEndDate(LocalDateTime.now());

            DrinkMenu drinkMenuCedjeni = new DrinkMenu();
            drinkMenuCedjeni.setMenuName("Cedjeni Sokovi");
            drinkMenuCedjeni.setDrinks(new HashSet<>());
            drinkMenuCedjeni.setStartDate(LocalDateTime.now());
            drinkMenuCedjeni.setEndDate(LocalDateTime.now());

            DrinkMenu drinkMenuGazirani = new DrinkMenu();
            drinkMenuGazirani.setMenuName("Gazirani Sokovi");
            drinkMenuGazirani.setDrinks(new HashSet<>());
            drinkMenuGazirani.setStartDate(LocalDateTime.now());
            drinkMenuGazirani.setEndDate(LocalDateTime.now());

            dishMenuRepo.save(dishMenuDorucak);
            drinkMenuRepo.saveAll(List.of(drinkMenuCedjeni,drinkMenuGazirani));

//            OrderItem orderItem01 = new OrderItem();
//            orderItem01.setMenuItem(sok01);
//            orderItem01.setDish_type(DISH_TYPE.BREAKFAST);
//
//            OrderItem orderItem02 = new OrderItem();
//            orderItem02.setMenuItem(sok01);
//            orderItem02.setDish_type(DISH_TYPE.BREAKFAST);
//
//            OrderItem orderItem03 = new OrderItem();
//            orderItem03.setMenuItem(sok02);
//            orderItem03.setDish_type(DISH_TYPE.BREAKFAST);
//
//            OrderItem orderItem04 = new OrderItem();
//            orderItem04.setMenuItem(dorucak01);
//            orderItem04.setDish_type(DISH_TYPE.SOUP);
//
//            OrderItem orderItem05 = new OrderItem();
//            orderItem05.setMenuItem(dorucak02);
//            orderItem05.setDish_type(DISH_TYPE.MAIN_COURSE);
//
//            orderItemRepo.saveAll(List.of(orderItem01,orderItem02,orderItem03,orderItem04,orderItem05));
//
//            dorucak01.setDishMenu(dishMenuDorucak);
////            dorucak01.setOrderItem(orderItem04);
//            dorucak02.setDishMenu(dishMenuDorucak);
////            dorucak01.setOrderItem(orderItem05);
//            dorucak03.setDishMenu(dishMenuDorucak);
//
//            sok01.setDrinkMenu(drinkMenuGazirani);
//            sok02.setDrinkMenu(drinkMenuGazirani);
//            sok03.setDrinkMenu(drinkMenuGazirani);
//            sok04.setDrinkMenu(drinkMenuCedjeni);
//            sok05.setDrinkMenu(drinkMenuCedjeni);
//
//            dishRepo.saveAll(List.of(dorucak01,dorucak02,dorucak03));
//            drinkRepo.saveAll(List.of(sok01,sok02,sok03,sok04,sok05));
//
//            Order order = new Order();
//            order.setOrder_status(ORDER_STATUS.PENDING);
//            order.setOrderItems(List.of(orderItem01,orderItem02,orderItem03));
//
//            Order order1 = new Order();
//            order1.setOrder_status(ORDER_STATUS.PENDING);
//            order1.setOrderItems(List.of(orderItem01,orderItem03));
//
//            Order order2 = new Order();
//            order2.setOrder_status(ORDER_STATUS.PENDING);
//            order2.setOrderItems(List.of(orderItem01,orderItem02,orderItem03,orderItem01,orderItem02,orderItem05));
//            orderRepo.saveAll(List.of(order,order1,order2));
        };
    }
}
