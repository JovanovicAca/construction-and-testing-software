package com.example.KTS.service.Order;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.DTO.OrderDTO;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Repo.OrderRepo;
import com.example.KTS.Service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class OrderServiceUnitTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepo orderRepo;

    @Test
    public void findAll(){
        List<Order> orders = orderRepo.findAll();
        verify(orderRepo,times(1)).findAll();
        assertEquals(1,orders.size());
    }

    @Test
    public void findByValidId(){
        Optional<Order> o = orderRepo.findById(1L);
        verify(orderRepo,times(1)).findById(1L);
        assertEquals(Optional.of(1L),o.get().getId());
    }

    @Test
    public void addNewOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder_price(2000);
        orderDTO.setOrderStatus(ORDER_STATUS.ACCEPTED);
        orderDTO.setTable("1");

        Long l = orderService.addNewOrder(orderDTO);
        Order o = orderRepo.findById(l).get();
        o.setOrder_price(55555);
        Order updated = orderService.save(o);
        assertEquals(Optional.of(55555),updated.getOrder_price());
    }

    @Test
    public void findById(){
        Order o = orderRepo.getById(1L);
        verify(orderRepo,times(1)).findById(1L);
        assertEquals(1L,java.util.Optional.ofNullable(o.getId()));
    }

}
