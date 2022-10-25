package com.example.KTS.service.DrinkMenu;

import com.example.KTS.Model.DTO.DrinkMenuDTO;
import com.example.KTS.Model.Restaurant.DrinkMenu;
import com.example.KTS.Repo.DrinkMenuRepo;
import com.example.KTS.Service.DrinkMenuService;
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
public class DrinkMenuUnitTest {

    @Autowired
    private DrinkMenuService drinkMenuService;

    @MockBean
    private DrinkMenuRepo drinkMenuRepo;

    @BeforeAll
    public void setup(){
        List<DrinkMenu> drinkMenus = new ArrayList<>();
        DrinkMenu drinkMenu = new DrinkMenu();

        given(drinkMenuRepo.findByMenuName("ime1"))
                .willReturn(drinkMenu);
    }

    @Test
    public void findByName() throws Exception{
        DrinkMenu found = drinkMenuRepo.findByMenuName("ime1");
        verify(drinkMenuRepo, times(1)).findByMenuName("ime1");
        assertEquals(null, found.getMenuName());
    }

    @Test
    public void addDrinkMenuDto() throws Exception{
        DrinkMenuDTO drinkMenuDTO = new DrinkMenuDTO();
        drinkMenuDTO.setMenuName("menu1");
        DrinkMenu created = drinkMenuService.addDrinkMenuDto(drinkMenuDTO);

        verify(drinkMenuRepo,times(1)).findByMenuName("menu1");
        assertEquals("menu1",created.getMenuName());
    }

    @Test
    public void updateDrinkMenu() throws Exception{
        DrinkMenuDTO drinkMenuDTO = new DrinkMenuDTO();
        drinkMenuDTO.setMenuName("rakija");
        drinkMenuService.addDrinkMenuDto(drinkMenuDTO);
        drinkMenuDTO.setMenuName("koktel");
        DrinkMenu updated = drinkMenuService.updateDrinkMenu(drinkMenuDTO);

        verify(drinkMenuRepo,times(1)).findByMenuName("koktel");
        assertEquals("menu2",updated.getMenuName());
    }

    @Test
    public void delete() throws Exception{
        DrinkMenu dm = new DrinkMenu();
        dm.setMenuName("vino");
        drinkMenuService.deleteDrinkMenu(dm.getMenuName());
        verify(drinkMenuRepo, times(1)).findByMenuName("vino");
    }

    @Test
    public void findById(){
        DrinkMenu dm = drinkMenuRepo.getById(1L);
        verify(drinkMenuRepo, times(1)).findById(1L);
        assertEquals(1L, java.util.Optional.ofNullable(dm.getId())); //
    }

    @Test
    public void findAll(){
        List<DrinkMenu> list = new ArrayList<DrinkMenu>();
        list = drinkMenuRepo.findAll();

        verify(drinkMenuRepo, times(1)).findAll();
        assertEquals(5, list.size()); //
    }
}
