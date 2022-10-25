package com.example.KTS.Service;

import com.example.KTS.Exception.OrderItemExpection;
import com.example.KTS.Model.DTO.OrderItemDTO;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Repo.OrderItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CookService {

    private final ModelMapper mapper;
    private final OrderItemRepo orderItemRepo;

    @Autowired
    public CookService(ModelMapper mapper, OrderItemRepo orderItemRepo) {
        this.mapper = mapper;
        this.orderItemRepo = orderItemRepo;
    }


    public List<OrderItemDTO> getNotTakenDishes() {
        List<OrderItem> orderItems = orderItemRepo.getAllAvailableDishes();
        List<OrderItemDTO> dtos = orderItems.stream().map(orderItem -> mapper.map(orderItem,OrderItemDTO.class)).collect(Collectors.toList());
        return dtos;
    }
    public String prepareOrderItem(OrderItemDTO orderItemDTO, DISH_STATUS starting, DISH_STATUS ending) throws OrderItemExpection {
        Optional<OrderItem> orderItem = orderItemRepo.findById(orderItemDTO.getId());
        if(orderItem.get().getDish_status() != starting){
            throw new OrderItemExpection(orderItemDTO.getName());
        }
        orderItem.get().setDish_status(ending);
        orderItemRepo.save(orderItem.get());
        return "Order item status changed";
    }
}
