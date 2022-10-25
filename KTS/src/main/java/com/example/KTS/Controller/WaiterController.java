package com.example.KTS.Controller;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Restaurant.*;
import com.example.KTS.Service.WaiterService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

    private final WaiterService waiterService;

    @Autowired
    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @GetMapping(path = "/tables")
    public Response<List<RestaurantTable>> getAllTables(){
        return waiterService.getAllTables();
    }

    @GetMapping(path = "/table")
    public Response<RestaurantTable> getTableById (@RequestParam("id") Long id){
        return waiterService.getTableById(id);
    }

    @GetMapping(path = "/tables/{status}")
    public List<RestaurantTable> findByTableStatus(@PathVariable("status") TABLE_STATUS table_status){
        return waiterService.findByTableStatus(table_status);
    }

    @PutMapping(path = "/tables")
    public Response<RestaurantTable> changeTableStatus(@RequestParam("id") Long id,
                                                       @RequestBody TABLE_STATUS table_status){

        return waiterService.changeTableStatus(table_status,id);
    }

    @GetMapping(path = "/order")
    public Response<Order> getOrderById(@RequestParam("id") Long id){
        return waiterService.getOrderById(id);
    }

    @PostMapping(path = "/order")
    public Order saveOrder(@RequestBody Order order){
        return waiterService.saveOrder(order);
    }

    @DeleteMapping(path = "/order")
    public void deleteOrder(@RequestParam("id") Long id){
        waiterService.deleteOrder(id);
    }

    @PutMapping(path = "/orderItem")
    public Response<OrderItem> changeOrderItemStatus(@RequestParam("orderItemId") Long id,
                                                     @RequestBody DISH_STATUS dish_status){

        return waiterService.changeOrderItemStatus(id,dish_status);
    }

    @PutMapping(path = "/orderItem/add")
    public Response<Order> addOrderItem(@RequestParam("orderId") Long id,
                                        @RequestBody OrderItem orderItem){

        return waiterService.addOrderItem(id,orderItem);
    }

    @PutMapping(path = "/orderItem/remove")
    public Response<Order> removeOrderItem(@RequestParam("orderItemId") Long orderItemId,
                                           @RequestParam("orderId") Long orderId){

        return waiterService.removeOrderItem(orderId,orderItemId);
    }

    @PutMapping(path = "/orderItem/allergy")
    public Response<OrderItem> addAllergyToOrderItem(@RequestParam("orderItemId") Long orderItemId,
                                                     @RequestBody String allergyDescription){

        return waiterService.addAllergyToOrderItem(orderItemId,allergyDescription);
    }

}
