package com.example.KTS.Controller;

import com.example.KTS.Exception.MenuItemExist;
import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DrinkDTO;

import com.example.KTS.Model.DTO.IngredientDTO;
import com.example.KTS.Model.Restaurant.Dish;

import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Service.DrinkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/drink")
public class DrinkController {

    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    private ModelMapper mapper;

//    @GetMapping(path = "/getDrinks/{pageNo}/{pageSize}/{sortBy}")
//    @PreAuthorize("hasRole('MANAGER')")
//    public ResponseEntity<List<Drink>> getDrinks(
//            @PathVariable int pageNo,
//            @PathVariable int pageSize,
//            @PathVariable String sortBy)
//    {
//        try {
//            List<Drink> drinks = new ArrayList<Drink>();
//            drinks = drinkService.getDrinks(pageNo, pageSize, sortBy);
//            return new ResponseEntity<List<Drink>>(drinks,new HttpHeaders(), HttpStatus.OK);
//        }catch (DrinkException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//
//    }
    @GetMapping(value="all")
    @PreAuthorize(("hasRole('CHEF')"))
    public ResponseEntity<List<DrinkDTO>> getAllDrinks(Pageable page){
        HttpHeaders header = new HttpHeaders();
       //List<DrinkDTO> holder = drinkService.getDrinks(page, header).stream().map(drink -> mapper.map(drink,DrinkDTO.class)).collect(Collectors.toList());
        List<DrinkDTO> holder = new ArrayList<>();
        List<Drink> drinks = drinkService.getDrinks(page,header);
        header.setAccessControlExposeHeaders(Collections.singletonList("Total-items"));
        for (Drink drink: drinks) {
            DrinkDTO drinkDTO = new DrinkDTO(drink);
            holder.add(drinkDTO);
        }
        return new ResponseEntity<>(holder, header, HttpStatus.OK);
        //        return new ResponseEntity<List<IngredientDTO>>(ingredientDTOS,HttpStatus.OK);
    }

    @GetMapping(path="/all/search")
    @PreAuthorize(("hasRole('CHEF')"))
    public ResponseEntity<List<DrinkDTO>> getAllDrinks(Pageable page,String searchInput){
        HttpHeaders header = new HttpHeaders();
        header.setAccessControlExposeHeaders(Collections.singletonList("Total-items"));
        List<DrinkDTO> drinks = drinkService.getSearchedDrinks(page,header,searchInput);
        return new ResponseEntity<>(drinks, header, HttpStatus.OK);
    }

    @PostMapping(value="addDrink")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> addDrink(@RequestBody DrinkDTO drinkDTO){
        try{
            drinkService.saveNewFromDTO(drinkDTO);
        }catch (MenuItemExist e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity(drinkDTO, HttpStatus.CREATED);
    }

    @PostMapping(value="deleteDrink")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> deleteDrink(@RequestBody String name){

        try{
            drinkService.deleteDrinkName(name);
        }
        catch (MenuItemNotFound e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


        return new ResponseEntity<String>("Dish deleted!",HttpStatus.OK);
    }

    @PostMapping(value="updateDrink")
    public void updateDrink(@RequestBody DrinkDTO drinkDTO) throws MenuItemNotFound {
        try {
            Drink drink = drinkService.updateFromDTO(drinkDTO);
        }catch (MenuItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value="addDrinks")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> addDrinks(@RequestBody DrinkDTO drinkDTO){
        try{
            Drink drink = drinkService.saveNewFromDTOS(drinkDTO);
            if(drink == null){
                return new ResponseEntity(drinkDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (MenuItemExist e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity(drinkDTO, HttpStatus.CREATED);
    }

}
