package com.example.KTS.service.Waiter;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Model.Restaurant.RestaurantTable;
import com.example.KTS.Repo.OrderRepo;
import com.example.KTS.Service.WaiterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class WaiterServiceUnitTest {

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private OrderRepo orderRepo;

//    @Test
//    public void testFindAllTables(){
//        List<RestaurantTable> tablesList = waiterService.getAllTables().getData();
//        assertEquals(2, tablesList.size());
//    }


}
