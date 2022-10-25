package com.example.KTS.repository;


import com.example.KTS.Repo.IngredientRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepo ingredientRepo;
    @Test
    public void testFindByName() {
        System.out.println(ingredientRepo.findAll());
    }

}
