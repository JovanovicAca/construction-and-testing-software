package com.example.KTS.service.DrinkMenu;

import com.example.KTS.Model.DTO.DishMenuDTO;
import com.example.KTS.Model.Restaurant.DishMenu;
import com.example.KTS.Service.DishMenuService;
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
public class DishMenuIntegrationTest {

    @Autowired
    private DishMenuService dishMenuService;

    @Test
    public void findByName() throws Exception{
        DishMenu found = dishMenuService.findByName("ime1");
        assertEquals(null, found.getMenuName());
    }

    @Test
    public void addDishMenuDto() throws Exception{
        DishMenuDTO dishMenuDTO = new DishMenuDTO();
        dishMenuDTO.setMenuName("menu1");
        DishMenu created = dishMenuService.addDishMenuDTO(dishMenuDTO);
        assertEquals("menu1",created.getMenuName());
    }

    @Test
    public void updateDishMenu() throws Exception{
        DishMenuDTO dishMenuDTO = new DishMenuDTO();
        dishMenuDTO.setMenuName("menu1");
        dishMenuService.addDishMenuDTO(dishMenuDTO);
        dishMenuDTO.setMenuName("menu2");
        DishMenu updated = dishMenuService.updateDishMenu(dishMenuDTO);
        assertEquals("menu2",updated.getMenuName());
    }

    @Test
    public void delete() throws Exception{
        DishMenu dm = new DishMenu();
        dm.setMenuName("rucak");
        dishMenuService.deleteDishMenu(dm.getMenuName());
    }
}
