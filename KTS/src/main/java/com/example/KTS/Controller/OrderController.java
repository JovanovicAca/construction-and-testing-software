package com.example.KTS.Controller;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.DTO.OrderDTO;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/getAll")
    public Response<List<Order>> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Order>> getAllDishes(){
        return new ResponseEntity<>(orderService.getOrders(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CHEF')")
    @PostMapping(value = "/add")
    public ResponseEntity<String> addNewOrder(@RequestBody OrderDTO orderDTO){
        try{
            return new ResponseEntity<>(orderService.addNewOrder(orderDTO).toString(), HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PreAuthorize("hasRole('CHEF')")
    @GetMapping(value = "/findByTable")
    public ResponseEntity<Order> findByTable(@RequestParam String id){
        try{
            return new ResponseEntity<>(orderService.findByTable(Long.parseLong(id)), HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('CHEF','WAITER')")
    @GetMapping(value = "/update/order/status")
    public ResponseEntity<Order> updateOrderStatus(@RequestParam String id, @RequestParam ORDER_STATUS order_status){
        try{
            return new ResponseEntity<>(orderService.updateOrderStatus(Long.parseLong(id),order_status), HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PreAuthorize("hasAnyRole('CHEF','WAITER')")
    @GetMapping(value = "/update/orderItem")
    public ResponseEntity<OrderItem> updateOrderItemStatus(@RequestParam String id, @RequestParam DISH_STATUS dish_status){
        try{
            return new ResponseEntity<>(orderService.updateOrderItemStatus(Long.parseLong(id),dish_status), HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('CHEF','WAITER')")
    @GetMapping(value = "/dish/pending")
    public ResponseEntity<List<OrderItem>> getPendingDishes(){
        try{
            return new ResponseEntity<>(orderService.getPendingOrders(),HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('CHEF','WAITER')")
    @GetMapping(value = "/drink/pending")
    public ResponseEntity<List<OrderItem>> getPendingDrinks(){
        try{
            return new ResponseEntity<>(orderService.getPendingDrinks(),HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping(value = "/finished")
    public ResponseEntity<List<Order>> getAllFinishedOrders() {
        return new ResponseEntity<>(this.orderService.allFinishedOrders(),HttpStatus.OK);
    }
}
