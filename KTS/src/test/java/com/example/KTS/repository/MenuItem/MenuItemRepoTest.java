package com.example.KTS.repository.MenuItem;

import com.example.KTS.Repo.MenuItemRepo;
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
public class MenuItemRepoTest {

    @Autowired
    private MenuItemRepo menuItemRepo;

    @Test
    public void testMenuItemWithGivenIngredientWithMenuItem(){
        assertEquals("peceno meso",menuItemRepo.findAllByIngredient("kikiriki").get(0).getName());
    }

    @Test
    public void testMenuItemWithGivenIngredientWithoutMenuItem(){
        assertEquals(0,menuItemRepo.findAllByIngredient("badem").size());
    }

}
