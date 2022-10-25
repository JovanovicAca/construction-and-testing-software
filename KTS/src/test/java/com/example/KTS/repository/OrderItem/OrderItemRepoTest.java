package com.example.KTS.repository.OrderItem;

import com.example.KTS.Repo.OrderItemRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class OrderItemRepoTest {



    @Autowired
    private OrderItemRepo orderItemRepo;

    @Test
    public void shouldReturnOneOrderItem(){
        assertEquals(1, orderItemRepo.getAllAvailableDishes().size());
        assertEquals("peceno meso", orderItemRepo.getAllAvailableDishes().get(0).getMenuItem().getName());
    }
}
