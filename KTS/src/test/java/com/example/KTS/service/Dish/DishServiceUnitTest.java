package com.example.KTS.service.Dish;

import com.example.KTS.Exception.DishNotFoundException;
import com.example.KTS.Exception.MenuItemExist;
import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DishDTO;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Repo.DishRepo;
import com.example.KTS.Service.DishService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.example.KTS.constants.DishConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DishServiceUnitTest {

    @Autowired
    private DishService dishService;

    @MockBean
    private DishRepo dishRepo;

    @BeforeAll
    public void setup() {
        List<Dish> d = new ArrayList<Dish>();
        Dish dish = new Dish();

        given(dishRepo.findByName(NAME))
                .willReturn(dish);

        given(dishRepo.findAll()).willReturn(d);
        //pageable

        Dish d1 = new Dish();
        Dish d2 = new Dish();

        d2.setId(ID);

        given(dishRepo.findById(ID)).willReturn(java.util.Optional.of(d2));


    }

    @Test
    public void findByNameShouldReturnTrue(){
        Dish dish = new Dish();
        dish.setName("dish-name");
        when(dishRepo.findByName("dish-name")).thenReturn(dish);
        Dish found = dishRepo.findByName("dish-name");
        assertEquals("dish-name", found.getName());
    }

    @Test
    public void findByNameShouldReturnNull(){
        when(dishRepo.findByName("non-exist-dish")).thenReturn(null);
        Dish found = dishRepo.findByName("non-exist-dish");
        assertEquals(null, found);
    }


    @Test(expected = MenuItemExist.class)
    public void addDishShouldReturnFalseWhenNameExist() throws Exception{
        DishDTO d = new DishDTO();
        d.setName(NAME);
        d.setDescription(DESCRIPTION);
        Dish newDish = new Dish();
        newDish.setName(NAME);
        when(dishRepo.findByName(NAME)).thenReturn(newDish);
        when(dishRepo.save(newDish)).thenReturn(newDish);
        dishService.saveNewFromDTO(d);
    }

    @Test(expected = MenuItemNotFound.class)
    public void updateDishShouldReturnFalseWhenNameExist() throws Exception{
        DishDTO d = new DishDTO();
        d.setName(NAME);
        d.setDescription(DESCRIPTION);
        Dish newDish = new Dish();
        newDish.setName(NAME);
        when(dishRepo.findByName(NAME)).thenReturn(newDish);
        when(dishRepo.save(newDish)).thenReturn(newDish);
        d.setName("new-name");
        dishService.updateFromDTO(d);
    }

    @Test
    public void saveDishShouldReturnTrue() throws Exception{ // Update
        Dish d = new Dish();
        when(dishRepo.save(d)).thenReturn(d);
        dishService.save(d);
    }

    @Test(expected = DishNotFoundException.class)
    public void deleteDishNameShouldThrowExceptionWhenNameDoesntExist() throws Exception{
        Dish d = new Dish();
        d.setName("ABCABC");
        dishService.deleteDishName(d.getName());
    }
}
