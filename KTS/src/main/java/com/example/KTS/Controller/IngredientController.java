package com.example.KTS.Controller;

import com.example.KTS.Model.DTO.IngredientDTO;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Model.Restaurant.MenuItem;
import com.example.KTS.Service.IngredientService;
import com.example.KTS.Service.MenuItemService;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private MenuItemService menuItemService;

    private ModelMapper mapper;

    @Autowired
    public IngredientController(IngredientService ingredientService, MenuItemService menuItemService, ModelMapper mapper) {
        this.ingredientService = ingredientService;
        this.menuItemService = menuItemService;
        this.mapper = mapper;
    }


    @PostMapping(value="add")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<String> addIngredient(@RequestBody IngredientDTO ingredientDTO){

        try{
            ingredientService.saveFromDTO(ingredientDTO);
            return new ResponseEntity("Ingredient added!" ,HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value="all")
    @PreAuthorize(("hasRole('CHEF')"))
    public ResponseEntity<List<IngredientDTO>> getAllIngredients(Pageable page){
        HttpHeaders header = new HttpHeaders();
        header.setAccessControlExposeHeaders(Collections.singletonList("Total-items"));
        List<IngredientDTO> holder = ingredientService.getAll(page, header).stream().map(ingredient -> mapper.map(ingredient,IngredientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(holder, header, HttpStatus.OK);
//        return new ResponseEntity<List<IngredientDTO>>(ingredientDTOS,HttpStatus.OK);
    }
    @GetMapping(value="all/search")
    @PreAuthorize(("hasRole('CHEF')"))
    public ResponseEntity<List<IngredientDTO>> getAllIngredients(Pageable page,String searchInput){
        HttpHeaders header = new HttpHeaders();
        header.setAccessControlExposeHeaders(Collections.singletonList("Total-items"));
        List<IngredientDTO> holder = ingredientService.getAll(page, header, searchInput).stream().map(ingredient -> mapper.map(ingredient,IngredientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(holder, header, HttpStatus.OK);
//        return new ResponseEntity<List<IngredientDTO>>(ingredientDTOS,HttpStatus.OK);
    }
    @GetMapping(value="getAllNames")
    //@PreAuthorize(("hasRole('CHEF')"))
    public List<String> getAllIngredients(){
        HttpHeaders header = new HttpHeaders();
        List<String> names = ingredientService.getAllNames();
        return names;
        //eturn new ResponseEntity<>(names, header, HttpStatus.OK);
//        return new ResponseEntity<List<IngredientDTO>>(ingredientDTOS,HttpStatus.OK);
    }

    @PostMapping(value="update")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<String> updateIngredient(@RequestBody IngredientDTO ingredientDTO) throws Exception{
        try{
            if(ingredientDTO.getPrice() < 0){
                return new ResponseEntity("Price can't be less than 0",HttpStatus.BAD_REQUEST);
            }
            ingredientService.updateIngredient(ingredientDTO);
            return new ResponseEntity<String>("Ingredient updated!",HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
    @DeleteMapping(value="deleteIngredient")
    @PreAuthorize("hasRole('CHEF')")
    public ResponseEntity<String> deleteIngredient(@RequestBody IngredientDTO ingredientDTO){
        Ingredient ingredient = ingredientService.findByName(ingredientDTO.getName());
        if(ingredient == null){
            return new ResponseEntity<String>("Ingredient with that does not exists!",HttpStatus.BAD_REQUEST);
        }

        List<MenuItem> items = menuItemService.findAllByIngredient(ingredient.getName());
        if(!items.isEmpty()){
            return new ResponseEntity<String>("Ingredient is used in :" +
                    items.stream().map(MenuItem::getName).collect(Collectors.joining(",")) +
                    "\nYou have to remove it from menu items before deletion.",HttpStatus.BAD_REQUEST);
        }

       ingredientService.delete(ingredient);

        return new ResponseEntity<>("Ingredient deleted!",HttpStatus.OK);
    }

}
