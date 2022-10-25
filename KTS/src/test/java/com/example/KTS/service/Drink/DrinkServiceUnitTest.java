package com.example.KTS.service.Drink;

import com.example.KTS.Exception.DishNotFoundException;
import com.example.KTS.Exception.MenuItemExist;
import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DishDTO;
import com.example.KTS.Model.DTO.DrinkDTO;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Repo.DrinkRepo;
import com.example.KTS.Service.DrinkService;
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
import java.util.Optional;

import static com.example.KTS.constants.DishConstants.DESCRIPTION;
import static com.example.KTS.constants.DishConstants.NAME;
import static com.example.KTS.constants.DrinkConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class

DrinkServiceUnitTest {

    @Autowired
    private DrinkService drinkService;

    @MockBean
    private DrinkRepo drinkRepo;

    @BeforeAll
    public void setup() {
        List<Drink> d = new ArrayList<Drink>();
        Drink drink = new Drink();

        given(drinkRepo.findByName(NAME))
                .willReturn(drink);

        given(drinkRepo.findAll()).willReturn(d);
        //pageable

        Drink d1 = new Drink();
        Drink d2 = new Drink();

        d2.setId(ID);

        given(drinkRepo.findById(ID)).willReturn(java.util.Optional.of(d2));


    }

    @Test
    public void findByNameShouldReturnTrue(){
        Drink drink = new Drink();
        drink.setName(NAME);
        when(drinkRepo.findByName(NAME)).thenReturn(drink);
        Drink found = drinkRepo.findByName(NAME);
        assertEquals(NAME, found.getName());
    }

    @Test
    public void findByNameShouldReturnNull(){
        when(drinkRepo.findByName("non-exist-drink")).thenReturn(null);
        Drink found = drinkRepo.findByName("non-exist-drink");
        assertEquals(null, found);
    }

    @Test(expected = MenuItemExist.class)
    public void addDishShouldReturnFalseWhenNameExist() throws Exception{
        DrinkDTO d = new DrinkDTO();
        d.setName(NAME);
        d.setDescription(DESCRIPTION);
        Drink newDrink = new Drink();
        newDrink.setName(NAME);
        when(drinkRepo.findByName(NAME)).thenReturn(newDrink);
        when(drinkRepo.save(newDrink)).thenReturn(newDrink);
        drinkService.saveNewFromDTO(d);
    }

    @Test(expected = MenuItemNotFound.class)
    public void updateDishShouldReturnFalseWhenNameExist() throws Exception{
        DrinkDTO d = new DrinkDTO();
        d.setName(NAME);
        d.setDescription(DESCRIPTION);
        Drink newDrink = new Drink();
        newDrink.setName(NAME);
        when(drinkRepo.findByName(NAME)).thenReturn(newDrink);
        when(drinkRepo.save(newDrink)).thenReturn(newDrink);
        d.setName("new");
        drinkService.updateFromDTO(d);
    }

    @Test
    public void saveDrinkShouldReturnTrue() throws Exception{ // Update
        Drink d = new Drink();
        when(drinkRepo.save(d)).thenReturn(d);
        drinkService.save(d);
    }

    @Test(expected = MenuItemNotFound.class)
    public void deleteDrinkNameShouldThrowExceptionWhenNameDoesntExist() throws Exception{
        Drink d = new Drink();
        d.setName("ABCABC");
        drinkService.deleteDrinkName(d.getName());
    }

}