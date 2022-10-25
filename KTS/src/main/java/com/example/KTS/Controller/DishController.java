package com.example.KTS.Controller;

import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DishDTO;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Service.DishService;
import com.example.KTS.Service.IngredientService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/dish")
public class DishController {

    private final DishService dishService;
    private final IngredientService ingredientService;
    private ModelMapper mapper;
    PropertyMap<DishDTO, Dish> skipIngredients = new PropertyMap<DishDTO, Dish>() {
        protected void configure() {
            skip().setIngredients(null);
        }
    };
    @Autowired
    public DishController(DishService dishService, IngredientService ingredientService,ModelMapper mapper) {
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.mapper = mapper;
        this.mapper.addMappings(skipIngredients);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Dish>> getAllDishes(){
        return new ResponseEntity<>(this.dishService.getAllDishes(),HttpStatus.OK);
    }
    @GetMapping(path = "/all/search")
    public ResponseEntity<List<Dish>> getAllDishes(Pageable page,@RequestParam String searchInput){
        HttpHeaders header = new HttpHeaders();
        header.setAccessControlExposeHeaders(Collections.singletonList("Total-items"));
        return new ResponseEntity<>(this.dishService.getAllDishes(page,header,searchInput),header,HttpStatus.OK);
    }

    @PostMapping(value = "addDish")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<String> addDish(@RequestBody DishDTO dishDTO){
        try{
            dishService.saveNewFromDTO(dishDTO);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<String>("Dish added!", HttpStatus.OK);
    }
    @PostMapping(value="updateDish")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<String> updateDish(@RequestBody DishDTO dishDTO){
        try{
            dishService.updateFromDTO(dishDTO);
        }
        catch (MenuItemNotFound e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<String>("Dish updated!", HttpStatus.OK);
    }
    @DeleteMapping(value = "deleteDish")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<String> deleteDish(@RequestParam String name){

        try{
            dishService.deleteDishName(name);
        }
        catch (MenuItemNotFound e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


        return new ResponseEntity<String>("Dish deleted!",HttpStatus.OK);
    }
}
