package com.example.KTS.Service;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Model.Restaurant.RestaurantTable;
import com.example.KTS.Repo.OrderItemRepo;
import com.example.KTS.Repo.OrderRepo;
import com.example.KTS.Repo.TableRepo;
import com.example.KTS.Repo.WaiterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WaiterService {

    private final WaiterRepo waiterRepo;

    private final TableRepo tableRepo;

    private final OrderRepo orderRepo;

    private final OrderItemRepo orderItemRepo;

    private final OrderItemService orderItemService;

    private final OrderService orderService;

    private final RestaurantTableService tableService;
    @Autowired
    public WaiterService(WaiterRepo waiterRepo, TableRepo tableRepo, OrderRepo orderRepo, OrderItemRepo orderItemRepo, OrderItemService orderItemService, OrderService orderService, RestaurantTableService tableService) {
        this.waiterRepo = waiterRepo;
        this.tableRepo = tableRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.tableService = tableService;
    }

    public Response<List<RestaurantTable>> getAllTables() {
        return new Response<>(tableRepo.findAll());
    }

    public Response<Order> getOrderById(Long orderId) {
        Optional<Order> optional = orderRepo.findById(orderId);
        return optional.map(Response::new).orElse(null);
    }

    public Order saveOrder(Order order) {
        order.setOrderStatus(ORDER_STATUS.PENDING);
        for(OrderItem orderItem : order.getOrderItems()){
            orderItem.setDish_status(DISH_STATUS.ORDER_NOT_TAKEN);
        }
        return orderRepo.save(order);
    }

    public Response<Order> addOrderItem(Long orderId, OrderItem orderItem) {
        Optional<Order> optional = orderRepo.findById(orderId);
        if(optional.isPresent()){
            Order order = optional.get();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.add(orderItem);
            order.setOrderItems(orderItems);
            orderItemRepo.save(orderItem);
            orderRepo.save(order);
            return new Response<>(order);
        }
        return null;
    }

    public Response<OrderItem> addAllergyToOrderItem(Long orderItemId, String allergyDescription){
        Optional<OrderItem> optionalOrderItem = orderItemRepo.findById(orderItemId);
        if(optionalOrderItem.isPresent()){
            OrderItem orderItem = optionalOrderItem.get();
            orderItem.setAllergyDescription(allergyDescription);
            orderItemRepo.save(orderItem);
            return new Response<>(orderItem);
        }
        return null;
    }

    public List<RestaurantTable> findByTableStatus(TABLE_STATUS table_status) {
        return tableRepo.findByTableStatus(table_status);
    }

    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }

    public Response<RestaurantTable> changeTableStatus(TABLE_STATUS table_status, Long id) {
        Optional<RestaurantTable> optional = tableRepo.findById(id);
        if(optional.isPresent()){
            RestaurantTable table = optional.get();
            table.setTableStatus(table_status);
            tableRepo.save(table);
            return new Response<>(table);
        }
        return null;
    }

    public Response<Order> removeOrderItem(Long orderId, Long orderItemId) {
        Optional<Order> optional = orderRepo.findById(orderId);
        if(optional.isPresent()){
            Order order = optional.get();
            for(OrderItem orderItem : order.getOrderItems()){
                if(Objects.equals(orderItem.getId(), orderItemId)){
                    order.getOrderItems().remove(orderItem);
                    orderItemRepo.deleteById(orderItemId);
                    orderRepo.save(order);
                    return new Response<>(order);
                }
            }
        }
        return null;
    }

    public Response<RestaurantTable> getTableById(Long id) {
        Optional<RestaurantTable> optional = tableRepo.findById(id);
        return new Response<>(optional.get());
    }

    public Response<OrderItem> changeOrderItemStatus(Long id, DISH_STATUS dish_status) {
        Optional<OrderItem> optional = orderItemRepo.findById(id);
        if(optional.isPresent()){
            OrderItem orderItem = optional.get();
            orderItem.setDish_status(dish_status);
            orderItemRepo.save(orderItem);
            return new Response<>(orderItem);
        }
        return null;
    }

}
