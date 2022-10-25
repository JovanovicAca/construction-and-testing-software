package com.example.KTS.service.DrinkMenu;

import com.example.KTS.Model.DTO.DrinkMenuDTO;
import com.example.KTS.Model.Restaurant.DrinkMenu;
import com.example.KTS.Service.DrinkMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkMenuIntegrationTest {

    @Autowired
    private DrinkMenuService drinkMenuService;

    @Test
    public void findByName(){
        DrinkMenu found = drinkMenuService.findByName("dorucak");
        assertEquals("dorucak", found.getMenuName());
    }

    @Test
    public void addDrinkMenuDto() throws Exception{
        DrinkMenuDTO drinkMenuDTO = new DrinkMenuDTO();
        drinkMenuDTO.setMenuName("dorucak");
        DrinkMenu created = drinkMenuService.addDrinkMenuDto(drinkMenuDTO);
        assertEquals("dorucak",created.getMenuName());
    }

    @Test
    public void updateDrinkMenu() throws Exception{
        DrinkMenuDTO drinkMenuDTO = new DrinkMenuDTO();
        drinkMenuDTO.setMenuName("menu1");
        drinkMenuService.addDrinkMenuDto(drinkMenuDTO);
        drinkMenuDTO.setMenuName("menu2");
        DrinkMenu updated = drinkMenuService.updateDrinkMenu(drinkMenuDTO);
        assertEquals("menu2",updated.getMenuName());
    }

    @Test
    public void delete() throws Exception{
        DrinkMenu dm = new DrinkMenu();
        dm.setMenuName("rucak");
        drinkMenuService.deleteDrinkMenu(dm.getMenuName());
    }
}
