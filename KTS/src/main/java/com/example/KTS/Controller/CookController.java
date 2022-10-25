package com.example.KTS.Controller;

import com.example.KTS.Model.DTO.OrderItemDTO;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Service.CookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/cook")
public class CookController {
    private final CookService cookService;

    @Autowired
    public CookController(CookService cookService){
        this.cookService = cookService;
    }

    @GetMapping(value = "order/new")
    public ResponseEntity<List<OrderItemDTO>> getAllNewOrders(){
        return new ResponseEntity<>(cookService.getNotTakenDishes(), HttpStatus.OK);
    }

    @PostMapping(value="/order/prepare")
    public ResponseEntity<String> prepareOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        try{
            cookService.prepareOrderItem(orderItemDTO, DISH_STATUS.ORDER_NOT_TAKEN,DISH_STATUS.IN_PREPARATION);
            return new ResponseEntity<>("Dish changed to pending!",HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
    @PostMapping(value="/order/finish")
    public ResponseEntity<String> finishOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        try{
            cookService.prepareOrderItem(orderItemDTO, DISH_STATUS.IN_PREPARATION,DISH_STATUS.READY_TO_SERVE);
            return new ResponseEntity<>("Dish is prepared!",HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
