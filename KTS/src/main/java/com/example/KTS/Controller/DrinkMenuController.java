package com.example.KTS.Controller;

import com.example.KTS.Model.DTO.DrinkMenuDTO;
import com.example.KTS.Model.Restaurant.DishMenu;
import com.example.KTS.Model.Restaurant.DrinkMenu;
import com.example.KTS.Service.DrinkMenuService;
import com.example.KTS.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/drink/menu")
public class DrinkMenuController {

    private final DrinkMenuService drinkMenuService;

    @Autowired
    public DrinkMenuController(DrinkMenuService drinkMenuService) {
        this.drinkMenuService = drinkMenuService;
    }
    @GetMapping(path = "/all")
    public ResponseEntity<List<DrinkMenu>> getAllDishes(){
        return new ResponseEntity<>(this.drinkMenuService.getAll(),HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addDrinkMenu(@RequestBody DrinkMenuDTO drinkMenuDTO) {
        try {
            drinkMenuService.addDrinkMenuDto(drinkMenuDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>("Drink menu added!", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateDrinkMenu(@RequestBody DrinkMenuDTO drinkMenuDTO){
        try{
            drinkMenuService.updateDrinkMenu(drinkMenuDTO);
            return new ResponseEntity<>("Drink menu updated!", HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<String> deleteDrinkMenu(@RequestBody DrinkMenuDTO drinkMenuDTO){
        try{
            drinkMenuService.deleteDrinkMenu(drinkMenuDTO.getMenuName());
            return new ResponseEntity<>("Drink menu deleted!", HttpStatus.OK);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
