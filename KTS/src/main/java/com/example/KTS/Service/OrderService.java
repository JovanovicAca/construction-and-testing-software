package com.example.KTS.Service;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Exception.OrderException;
import com.example.KTS.Model.DTO.OrderDTO;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Repo.OrderItemRepo;
import com.example.KTS.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final OrderItemService orderItemService;
    private final RestaurantTableService tableService;
    @Autowired
    public OrderService(OrderRepo orderRepo, OrderItemRepo orderItemRepo, OrderItemService orderItemService, RestaurantTableService tableService) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.tableService = tableService;
        this.orderItemService = orderItemService;
    }

    public Response<List<Order>> getAllOrders() {
        return new Response<>(orderRepo.findAll());
    }

    public List<Order> getOrders(){
        return orderRepo.findAll();
    }

    public List<Order> getAllNotFinishedOrders() {
        return orderRepo.findAllNotFinished();
    }

    public Order sortOrderByPriority(Order order){
        Collections.sort(order.getOrderItems(),new Comparator<OrderItem>(){
            @Override
            public int compare(OrderItem o1, OrderItem o2) {
                if(o1.getDish_status() == o2.getDish_status()){
                    return o1.getDish_status().compareTo(o2.getDish_status());
                }
                else{
                    return o2.getDish_status().compareTo(o1.getDish_status());
                }
            }
        });
        return order;
    }
    public Order findById(Long id){
        return orderRepo.findById(id).get();
    }
    public Long addNewOrder(OrderDTO orderDTO) throws Exception {
        if(orderDTO.getOrderStatus() != ORDER_STATUS.PENDING){
            throw new OrderException("order");
        }

        System.out.println(orderDTO.getTable());
        Order o = new Order();
        //pronadjemo sto
        try{
            o.setRestaurantTable(tableService.findById(orderDTO.getTable()));
        }
        catch (Exception e){
            throw new Exception();
        }

        o.setOrderStatus(orderDTO.getOrderStatus());

        // za svaki orderItem napravimo novi objekat
        List<List<OrderItem>> orderItems = orderDTO.getOrderItems().stream().map(orderItemDTO -> orderItemService.createOrderItem(orderItemDTO,orderDTO.getAllergyDescription())).collect(Collectors.toList());
        List<OrderItem> orderItems1 =
                orderItems.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
        o.setOrderItems(orderItems1);
        o.setOrder_price(orderDTO.getOrder_price());

        return save(o).getId();
    }
    public Order save(Order o) {
        return orderRepo.save(o);
    }

    public Order findByTable(long id) throws Exception{
        try{
            return orderRepo.findByRestaurantTable(id);
        }
        catch (Exception e){
            throw new Exception();
        }

    }

    public OrderItem updateOrderItemStatus(long id, DISH_STATUS dish_status) throws Exception {
        Optional<OrderItem> optionalOrderItem = orderItemRepo.findById(id);
        if(optionalOrderItem.isPresent()){
            OrderItem orderItem = optionalOrderItem.get();
            orderItem.setDish_status(dish_status);
            orderItemRepo.save(orderItem);
            return optionalOrderItem.get();
        }
        throw new Exception();

    }

    public Order updateOrderStatus(long id, ORDER_STATUS order_status) throws Exception {
        Order order = orderRepo.findById(id).get();
        if(order == null){
            throw new Exception();
        }
        if(order_status == ORDER_STATUS.FINISHED){
            order.setRestaurantTable(null);
        }
        order.setOrderStatus(order_status);
        return orderRepo.save(order);
    }

    public List<OrderItem> getPendingOrders() {
        return this.orderItemService.getPendingDishes();
    }
    public List<Order> allFinishedOrders(){
        return orderRepo.findAllFinished();
    }
    public List<OrderItem> getPendingDrinks() {
        return this.orderItemService.getPendingDrinks();
    }
}
