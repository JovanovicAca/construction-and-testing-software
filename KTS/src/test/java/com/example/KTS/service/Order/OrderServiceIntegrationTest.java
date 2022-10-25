package com.example.KTS.service.Order;

import com.example.KTS.Model.DTO.OrderDTO;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void findByIdServiceShouldReturnTrue(){
        Order found = orderService.findById(1L);
        assertEquals(1L, java.util.Optional.ofNullable(found.getId()));
    }

    @Test
    public void findByIdServiceTestShouldReturnNull(){
        Order notFound = orderService.findById(150L);
        assertEquals(null,notFound);
    }

    @Test
    public void addNewOrderShouldReturnTrue() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(ORDER_STATUS.ACCEPTED);
        orderDTO.setOrder_price(25000);
        orderDTO.setTable("1");

        Long l = orderService.addNewOrder(orderDTO);
        Order created = orderService.findById(l);
        assertEquals(java.util.Optional.of(25000),created.getOrder_price());
    }

    @Test
    public void addNewOrderShouldReturnTrueByTable() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(ORDER_STATUS.ACCEPTED);
        orderDTO.setOrder_price(25000);
        orderDTO.setTable("1");

        Long l = orderService.addNewOrder(orderDTO);
        Order created = orderService.findById(l);
        assertEquals("1",created.getRestaurantTable().getId());
    }

    @Test
    public void addNewOrderShouldReturnTrueByOrderStatus() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(ORDER_STATUS.PENDING);
        orderDTO.setOrder_price(25000);
        orderDTO.setTable("1");

        Long l = orderService.addNewOrder(orderDTO);
        Order created = orderService.findById(l);
        assertEquals(ORDER_STATUS.PENDING,created.getOrderStatus());
    }

    @Test
    public void findAllShouldReturnTrue(){
        List<Order> orders = orderService.getAllOrders().getData();
        assertEquals(1,orders.size());
    }

    @Test
    public void findAllShouldReturnFalseWhenInvalidNumber(){
        List<Order> orders = orderService.getAllOrders().getData();
        assertEquals(5,orders.size());
    }
}
