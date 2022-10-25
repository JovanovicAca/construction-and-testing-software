package com.example.KTS.Controller;


import com.example.KTS.Model.DTO.DishMenuDTO;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.DishMenu;
import com.example.KTS.Service.DishMenuService;
import com.example.KTS.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/dish/menu")
public class DishMenuController {

    private final DishMenuService dishMenuService;

    @Autowired
    public DishMenuController(DishMenuService dishMenuService) {
        this.dishMenuService = dishMenuService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<DishMenu>> getAllDishes(){
        return new ResponseEntity<>(this.dishMenuService.getAll(),HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addDishMenu(@RequestBody DishMenuDTO dishMenuDTO){
        try{
            dishMenuService.addDishMenuDTO(dishMenuDTO);

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>("Dish menu added!", HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateDishMenu(@RequestBody DishMenuDTO dishMenuDTO){
        try{
            dishMenuService.updateDishMenu(dishMenuDTO);
            return new ResponseEntity<>("Dish menu updated!", HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @DeleteMapping(value = "delete")
    public ResponseEntity<String> deleteDishMenu(@RequestBody DishMenuDTO dishMenuDTO){
        try{
            dishMenuService.deleteDishMenu(dishMenuDTO.getMenuName());
            return new ResponseEntity<>("Dish menu deleted!", HttpStatus.OK);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
