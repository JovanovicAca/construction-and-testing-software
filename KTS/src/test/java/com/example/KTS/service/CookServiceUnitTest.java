package com.example.KTS.service;


import com.example.KTS.Model.DTO.OrderItemDTO;
import com.example.KTS.Model.Enums.DISH_STATUS;
import com.example.KTS.Repo.OrderItemRepo;
import com.example.KTS.Service.CookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.KTS.Exception.OrderItemExpection;
import static com.example.KTS.constants.CookConstants.orderItemMessage;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class CookServiceUnitTest {
    // Instanciramo pravi objekat klase koju testiramo
    @Autowired
    private CookService cookService;

    // Mokujemo sve reference, kako bismo imali unit test
    @MockBean
    private OrderItemRepo orderItemRepo;

    @Test
    public void testPrepareOrderItemValid(){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setDish_status(DISH_STATUS.IN_PREPARATION);
        assertEquals(orderItemMessage,cookService.prepareOrderItem(orderItemDTO, DISH_STATUS.IN_PREPARATION,DISH_STATUS.PENDING));
    }
    @Test(expected = OrderItemExpection.class)
    public void testPrepareOrderItemNotSameStartingDishStatus(){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setDish_status(DISH_STATUS.PENDING);
        cookService.prepareOrderItem(orderItemDTO, DISH_STATUS.IN_PREPARATION,DISH_STATUS.PENDING);
    }

}
