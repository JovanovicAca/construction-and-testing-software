package com.example.KTS.service.Drink;

import com.example.KTS.Exception.MenuItemExist;
import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DishDTO;
import com.example.KTS.Model.DTO.DrinkDTO;
import com.example.KTS.Model.Enums.DISH_TYPE;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Service.DrinkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.example.KTS.constants.DishConstants.DESCRIPTION_2;
import static com.example.KTS.constants.DishConstants.NAME_3;
import static com.example.KTS.constants.DrinkConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkServiceIntegrationTest {

    @Autowired
    private DrinkService drinkService;

    @Test
    public void findByNameServiceTestShouldReturnTrue(){
        Drink drink = new Drink();
        drink.setName("drink-found");
        Drink found = drinkService.save(drink);
        assertEquals("drink-found", found.getName());
    }

    @Test
    public void findByNameServiceTestShouldNull(){
        Drink notFound = drinkService.findByName("Nonexistent drink");
        assertEquals(null, notFound);
    }

    @Test
    public void addServiceTestShouldReturnTrue() throws MenuItemExist { // Add
        DrinkDTO d = new DrinkDTO();
        d.setName("sokic");
        d.setIngredients(new ArrayList<String>());
        Drink created = drinkService.saveNewFromDTO(d);
        assertEquals("sokic", created.getName());
    }

    @Test(expected = MenuItemExist.class)
    public void addServiceTestShouldThrowExceptionWhenNameExist() throws MenuItemExist {
        Drink drink = new Drink();
        drink.setName("new-drink");
        drinkService.save(drink);
        DrinkDTO d = new DrinkDTO();
        d.setName("new-drink");
        drinkService.saveNewFromDTO(d);
    }

    @Test(expected = MenuItemNotFound.class)
    public void updateServiceTestShouldThrowExceptionWhenNameExist() throws MenuItemExist {
        Drink drink = new Drink();
        drink.setName("new-drink");
        drinkService.save(drink);
        DrinkDTO d = new DrinkDTO();
        d.setName("drink");
        d.setDescription("description");
        drinkService.updateFromDTO(d);
    }

    @Test(expected = MenuItemNotFound.class)
    public void deleteShouldThrowDishNotFoundException() throws Exception{
        drinkService.deleteDrinkName("Nonexistent drink");
    }

    @Test
    public void saveDishShouldReturnTrue(){
        Drink d = new Drink();
        d.setName(NAME_3);
        Drink created = drinkService.save(d);
        assertEquals(NAME_3,created.getName());
    }
}
