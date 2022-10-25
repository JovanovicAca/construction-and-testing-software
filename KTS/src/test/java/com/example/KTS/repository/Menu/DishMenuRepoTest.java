package com.example.KTS.repository.Menu;

import com.example.KTS.Repo.DishMenuRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class DishMenuRepoTest {

    @Autowired
    private DishMenuRepo dishMenuRepo;

    @Test
    public void test(){
        System.out.println(dishMenuRepo.getAllByDateTime(LocalDateTime.now()));
    }
}
