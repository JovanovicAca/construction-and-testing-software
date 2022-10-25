package com.example.KTS.Service;

import com.example.KTS.Exception.OrderItemExpection;
import com.example.KTS.Model.DTO.OrderItemDTO;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.MenuItem;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Repo.OrderItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepo orderItemRepo;

    private final ModelMapper mapper;
    private final DrinkService drinkService;
    private final DishService dishService;
    @Autowired
    public OrderItemService(OrderItemRepo orderItemRepo, ModelMapper mapper, DrinkService drinkService, DishService dishService) {
        this.orderItemRepo = orderItemRepo;
        this.mapper = mapper;
        this.drinkService = drinkService;
        this.dishService = dishService;
    }

    public List<OrderItem> getPendingDishes(){
        return orderItemRepo.getAllAvailableDishes();
    }

    public List<OrderItem> getPendingDrinks(){
        return orderItemRepo.getAllAvailableDrinks();
    }

    //ovo za bartendera da samo pravi pica
    public OrderItem createDrinkOrderItem(OrderItemDTO orderItemDTO,String allergy){
        MenuItem menuItem = drinkService.findByName(orderItemDTO.getName());
        if(menuItem == null){
            throw new OrderItemExpection(orderItemDTO.getName());
        }
        OrderItem orderItem = new OrderItem(menuItem,orderItemDTO.getQuantity(),orderItemDTO.getDish_status(),allergy);
        return orderItem;
    }
    public List<OrderItem> createOrderItem(OrderItemDTO orderItemDTO,String allergy){
        MenuItem menuItem = drinkService.findByName(orderItemDTO.getName());
        if(menuItem == null){
            menuItem = dishService.findByName(orderItemDTO.getName());
        }
        if(menuItem == null){
            throw new OrderItemExpection(orderItemDTO.getName());
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for(int i =0; i< orderItemDTO.getQuantity();i++){
            orderItems.add(new OrderItem(menuItem,orderItemDTO.getQuantity(),orderItemDTO.getDish_status(),allergy));
        }
        return orderItems;
    }

    public List<OrderItem> prepareOrder(List<OrderItem> orderItems) {
        for(OrderItem orderItem:orderItems){
            if(orderItem.getMenuItem() instanceof Dish){
                orderItem.setDish_status(DISH_STATUS.PENDING);
            }
            else if(orderItem.getMenuItem() instanceof Drink){
                orderItem.setDish_status(DISH_STATUS.ORDER_NOT_TAKEN);
            }
            else{
                throw new OrderItemExpection("Order item class type error!");
            }
        }
        return orderItems;
    }
}
