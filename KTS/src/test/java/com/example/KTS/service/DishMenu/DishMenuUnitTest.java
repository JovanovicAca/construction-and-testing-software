package com.example.KTS.service.DrinkMenu;

import com.example.KTS.Model.DTO.DishMenuDTO;
import com.example.KTS.Model.Restaurant.DishMenu;
import com.example.KTS.Repo.DishMenuRepo;
import com.example.KTS.Service.DishMenuService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DishMenuUnitTest {

    @Autowired
    private DishMenuService dishMenuService;

    @MockBean
    private DishMenuRepo dishMenuRepo;

    @BeforeAll
    public void setup(){
        List<DishMenu> dishMenus = new ArrayList<>();
        DishMenu dishMenu = new DishMenu();

        given(dishMenuRepo.findByMenuName("ime1"))
                .willReturn(dishMenu);
    }

    @Test
    public void findByName() throws Exception{
        DishMenu found = dishMenuRepo.findByMenuName("ime1");
        verify(dishMenuRepo, times(1)).findByMenuName("");
        assertEquals(null, found.getMenuName());
    }

    @Test
    public void addDishMenuDto() throws Exception{
        DishMenuDTO dishMenuDTO = new DishMenuDTO();
        dishMenuDTO.setMenuName("menu1");
        DishMenu created = dishMenuService.addDishMenuDTO(dishMenuDTO);

        verify(dishMenuRepo,times(1)).findByMenuName("menu1");
        assertEquals("menu1",created.getMenuName());
    }

    @Test
    public void updateDishMenu() throws Exception{
        DishMenuDTO dishMenuDTO = new DishMenuDTO();
        dishMenuDTO.setMenuName("menu1");
        dishMenuService.addDishMenuDTO(dishMenuDTO);
        dishMenuDTO.setMenuName("menu2");
        DishMenu updated = dishMenuService.updateDishMenu(dishMenuDTO);

        verify(dishMenuRepo,times(1)).findByMenuName("menu2");
        assertEquals("menu2",updated.getMenuName());
    }

    @Test
    public void delete() throws Exception{
        DishMenu dm = new DishMenu();
        dm.setMenuName("rucak");
        dishMenuService.deleteDishMenu(dm.getMenuName());
        verify(dishMenuRepo, times(1)).findByMenuName("rucak");
    }

    @Test
    public void findById(){
        DishMenu dm = dishMenuRepo.getById(1L);
        verify(dishMenuRepo, times(1)).findById(1L);
        assertEquals(1L, java.util.Optional.ofNullable(dm.getId())); //
    }

    @Test
    public void findAll(){
        List<DishMenu> list = new ArrayList<DishMenu>();
        list = dishMenuRepo.findAll();

        verify(dishMenuRepo, times(1)).findAll();
        assertEquals(5, list.size()); //
    }
}
