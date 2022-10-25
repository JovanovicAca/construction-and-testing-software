package com.example.KTS.Controller;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.DTO.DishMenuDTO;
import com.example.KTS.Model.Restaurant.DishMenu;
import com.example.KTS.Model.Restaurant.DrinkMenu;
import com.example.KTS.Model.Restaurant.Menu;
import com.example.KTS.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(path = "/getAll")
    public List<Menu> getAll(){
        return menuService.getAllMenus();
    }

//    @GetMapping(path = "/drinkMenu/getAll")
//    public Response<List<DrinkMenu>> getAllDrinkMenus(){
//        return menuService.getAllDrinkMenus();
//    }
//
//    @GetMapping(path = "/dishMenu/getAll")
//    public Response<List<DishMenu>> getAllDishMenus(){ return menuService.getAllDishMenus();}

//    @GetMapping(value = "/all/date")
//    public ResponseEntity<List<DishMenuDTO>> allMenusDate(){
//        return new ResponseEntity<>(menuService.getAllMenusCurrentDate(LocalDateTime.now()), HttpStatus.OK);
//    }
}
