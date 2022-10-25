package com.example.KTS.service.Ingredient;

import com.example.KTS.Exception.DishNotFoundException;
import com.example.KTS.Exception.IngredientFoundException;
import com.example.KTS.Exception.IngredientNotFoundException;
import com.example.KTS.Model.DTO.IngredientDTO;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Repo.IngredientRepo;
import com.example.KTS.Service.IngredientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngredientServiceUnitTest {
    @Autowired
    private IngredientService ingredientService;

    @MockBean
    private IngredientRepo ingredientRepo;

    @Test
    public void findByName(){
        Ingredient i = new Ingredient();
        i.setName("kikiriki");
        when(ingredientRepo.findByName("kikiriki")).thenReturn(i);
        Ingredient found = ingredientRepo.findByName("kikiriki");
        assertEquals("kikiriki",found.getName());
    }

    @Test
    public void findByNameReturnNull(){
        when(ingredientRepo.findByName("name-non-exist")).thenReturn(null);
        Ingredient found = ingredientRepo.findByName("name-non-exist");
        assertEquals(null,found);
    }

    @Test(expected = IngredientFoundException.class)
    public void addIngredientShouldReturnFalseWhenNameExist() throws Exception {
        IngredientDTO dto = new IngredientDTO();
        dto.setName("abb");
        dto.setPrice(100.0);
        dto.setIsAllergic(false);
        Ingredient newIng = new Ingredient();
        newIng.setName("abb");
        newIng.setPrice(100.0);
        newIng.setIsAllergic(false);
        when(ingredientRepo.findByName("abb")).thenReturn(newIng);
        when(ingredientRepo.save(newIng)).thenReturn(newIng);
        ingredientService.saveFromDTO(dto);
    }

    @Test(expected = IngredientNotFoundException.class)
    public void updateFromDtoIngredientShouldReturnFalseWhenNameExist() throws Exception {
        IngredientDTO dto = new IngredientDTO();
        dto.setName("abb");
        dto.setPrice(100.0);
        dto.setIsAllergic(false);
        Ingredient newIng = new Ingredient();
        newIng.setName("abb");
        newIng.setPrice(100.0);
        newIng.setIsAllergic(false);
        when(ingredientRepo.findByName("abb")).thenReturn(newIng);
        when(ingredientRepo.save(newIng)).thenReturn(newIng);
        dto.setName("a");
        ingredientService.updateIngredient(dto);
    }

    @Test
    public void saveIngredientShouldReturnTrue(){
        Ingredient i = new Ingredient();
        when(ingredientRepo.save(i)).thenReturn(i);
        ingredientService.save(i);
    }
}
