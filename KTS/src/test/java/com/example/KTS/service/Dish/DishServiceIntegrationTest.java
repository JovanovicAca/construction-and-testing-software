package com.example.KTS.service.Dish;

import com.example.KTS.Exception.DishNotFoundException;
import com.example.KTS.Exception.MenuItemExist;
import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DishDTO;
import com.example.KTS.Model.Enums.DISH_TYPE;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Service.DishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.KTS.constants.DishConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DishServiceIntegrationTest {

    @Autowired
    private DishService dishService;

    @Test
    public void findByNameServiceTestShouldReturnTrue(){
        Dish dish = new Dish();
        dish.setName("found-dish");
        Dish added = new Dish();
        added = dishService.save(dish);
        assertEquals("found-dish", added.getName());
    }

    @Test
    public void findByNameServiceTestShouldNull(){
        Dish notFound = dishService.findByName("Nonexistent dish");
        assertEquals(null, notFound);
    }

    @Test
    public void addServiceTestShouldReturnTrue() throws MenuItemExist { // Save mew dto
        DishDTO d = new DishDTO();
        d.setName(NAME_3);
        d.setDescription(DESCRIPTION_2);
        d.setDishType(DISH_TYPE.SOUP);
        Dish created = dishService.saveNewFromDTO(d);
        assertEquals(NAME_3, created.getName());
    }
    @Test
    public void addServiceTestShouldReturnTrue1() throws MenuItemExist {
        DishDTO d = new DishDTO();
        d.setName(NAME_2);
        d.setDescription(DESCRIPTION_2);
        d.setDishType(DISH_TYPE.SOUP);
        Dish created = dishService.saveNewFromDTO(d);
        assertEquals(NAME_2, created.getName());
    }

    @Test(expected = MenuItemExist.class)
    public void addServiceTestShouldThrowExceptionWhenNameExist() throws MenuItemExist {
        Dish dish = new Dish();
        dish.setName("Burger");
        dishService.save(dish);
        DishDTO d = new DishDTO();
        d.setName("Burger");
        d.setDescription(DESCRIPTION_2);
        d.setDishType(DISH_TYPE.SOUP);
        dishService.saveNewFromDTO(d);
    }

    @Test(expected = MenuItemNotFound.class)
    public void saveServiceTestShouldThrowExceptionWhenDishNotFound() throws Exception{ // Update
        Dish dish = new Dish();
        dish.setName("found-dish");
        dishService.save(dish);
        DishDTO d = new DishDTO();
        d.setName("new-dish");
        d.setDescription("New better descritpion");
        Dish out = dishService.updateFromDTO(d);
        assertEquals("New better descritpion", out.getDescription());
    }

    @Test
    public void findAllShouldReturnTrue(){
        Dish dish = new Dish();
        dish.setName("found-dish");
        dishService.save(dish);
        Dish dish1 = new Dish();
        dish1.setName("found-dish-more");
        dishService.save(dish1);
        List<Dish> list = dishService.getAllDishes();
        assertEquals(3, list.size());
    }

    @Test(expected = DishNotFoundException.class)
    public void deleteShouldThrowDishNotFoundException() throws Exception{
        dishService.deleteDishName("Nonexistent dish");
    }

    @Test
    public void saveDishShouldReturnTrue(){
        Dish dish = new Dish();
        dish.setName(NAME_3);
        Dish created = dishService.save(dish);
        assertEquals(NAME_3,created.getName());
    }
}
