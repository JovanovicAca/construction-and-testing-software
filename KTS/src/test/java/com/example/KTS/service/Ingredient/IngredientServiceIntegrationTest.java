package com.example.KTS.service.Ingredient;

import com.example.KTS.Exception.DishNotFoundException;
import com.example.KTS.Exception.IngredientFoundException;
import com.example.KTS.Exception.IngredientNotFoundException;
import com.example.KTS.Model.DTO.IngredientDTO;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Service.IngredientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class IngredientServiceIntegrationTest {

    @Autowired
    private IngredientService ingredientService;


    @Test
    public void testFindAll(){
        List<Ingredient> found = ingredientService.all();
        assertEquals(2,found.size());
    }

    @Test
    public void findByNameServiceShouldReturnTrue(){
        Ingredient found = ingredientService.findByName("kikiriki");
        assertEquals("kikiriki",found.getName());
    }

    @Test
    public void findByNameServiceShouldReturnEmpty(){
        Optional<Ingredient> notFound = Optional.ofNullable(ingredientService.findByName("error-name"));
        assertEquals(Optional.empty(),notFound);
    }


    @Test
    public void addIngredientShouldReturnTrue() throws Exception {
        IngredientDTO dto = new IngredientDTO();
        dto.setName("kikiriki1");
        dto.setPrice(100.0);
        dto.setIsAllergic(false);
        Ingredient i = ingredientService.saveFromDTO(dto);
        assertEquals("kikiriki1",i.getName());
    }

    @Test
    public void addIngredientShouldReturnTrueAgain() throws Exception{
        IngredientDTO dto = new IngredientDTO();
        dto.setName("new-name-ing");
        Ingredient i = ingredientService.saveFromDTO(dto);
        assertEquals("new-name-ing",i.getName());
    }

    @Test(expected = IngredientFoundException.class)
    public void addIngredientShouldThrowExceptionWhenNameExist() throws Exception{
        Ingredient i = new Ingredient();
        i.setName("new-name");
        ingredientService.save(i);
        IngredientDTO dto = new IngredientDTO();
        dto.setName("new-name");
        ingredientService.saveFromDTO(dto);
    }

    @Test
    public void updateIngredientShouldReturnTrue() throws Exception {
        Ingredient i = new Ingredient();
        i.setName("new-ingredient");
        ingredientService.save(i);
        IngredientDTO dto = new IngredientDTO();
        dto.setName("new-ingredient");
        Ingredient updated = ingredientService.updateIngredient(dto);
        assertEquals("new-ingredient",updated.getName());
    }

    @Test(expected = IngredientNotFoundException.class)
    public void updateIngredientShouldThrowExceptionWhenWrongNameIsGiven() throws Exception {
        IngredientDTO dto = new IngredientDTO();
        dto.setName("wrong-name");
        ingredientService.updateIngredient(dto);
    }

    @Test
    public void testGetAllNames(){
        List<String> list = ingredientService.getAllNames();
        assertEquals(2, list.size());
    }

    @Test
    public void testGetAllShouldReturnFalse(){
        List<String> list = ingredientService.getAllNames();
        assertNotEquals(5, list.size());
    }


    @Test
    public void deleteTestByIdShouldReturnTrue(){
        Ingredient i = ingredientService.findByName("kikiriki");
        ingredientService.delete(i);

    }
}