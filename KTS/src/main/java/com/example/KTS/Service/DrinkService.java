package com.example.KTS.Service;

import com.example.KTS.Exception.DrinkException;
import com.example.KTS.Exception.MenuItemExist;
import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DrinkDTO;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Repo.DrinkRepo;
import com.example.KTS.Repo.IngredientRepo;
import com.example.KTS.Repo.MenuItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepo drinkRepo;

    @Autowired
    private MenuItemRepo menuItemRepo;

    @Autowired
    private IngredientRepo ingredientRepo;

    private ModelMapper mapper;

    public List<Drink> getDrinks(Pageable pageable, HttpHeaders hh) throws DrinkException {
        Page<Drink> pagedResult = this.drinkRepo.findAll(pageable);
        hh.add("Total-items", Long.toString(pagedResult.getTotalElements()));
        return pagedResult.getContent();
    }

    public Drink findByName(String name){
        return drinkRepo.findByName(name);
    }

    public Drink add(Drink i) {
        return drinkRepo.save(i);
    }

    public void deleteDrinkName(String name) throws MenuItemNotFound {
        Drink drink = findByName(name);
        if(drink == null){
            throw new MenuItemNotFound(name);
        }
        drinkRepo.delete(drink);

    }

    public Drink save(Drink drink) {
        return drinkRepo.save(drink);
    }

    public Drink updateFromDTO(DrinkDTO drinkDTO) throws MenuItemNotFound {
        Drink drink = findByName(drinkDTO.getName());
        if(drink == null){
            throw new MenuItemNotFound(drinkDTO.getName());
        }
        Long id = drink.getId();
        drink = mapper.map(drinkDTO, Drink.class);
        drink.setId(id);
        return save(drink);
    }

    public Drink saveNewFromDTO(DrinkDTO drinkDTO) {
        if(drinkRepo.findByName(drinkDTO.getName()) != null){
            throw new MenuItemExist(drinkDTO.getName());
        }
        Set<Ingredient> ings = new HashSet<Ingredient>();
        for (String s : drinkDTO.getIngredients()) {
            Ingredient i = ingredientRepo.findByName(s);
            ings.add(i);
        }
        Drink drink = new Drink(drinkDTO);
        drink.setIngredients(ings);
        return drinkRepo.save(drink);
    }

    public List<Drink> getAllDrinks(){
        return drinkRepo.findAll();
    }

    public List<Drink> getAll(Pageable page, HttpHeaders header, String searchInput) {
        Page<Drink> drinks = this.drinkRepo.findByNameContainingIgnoreCase(page,searchInput);

        header.add("Total-items", Long.toString(drinks.getTotalElements()));

        return drinks.getContent();
    }

    public List<DrinkDTO> getSearchedDrinks(Pageable page, HttpHeaders header, String searchInput) {
        Page<Drink> drinks = drinkRepo.findByNameContainingIgnoreCase(page,searchInput);
        List<Drink> drinkList = drinks.getContent();
        List<DrinkDTO> dtos = new ArrayList<>();
        for (Drink d :drinkList) {
            DrinkDTO drinkDTO = new DrinkDTO(d);
            dtos.add(drinkDTO);
        }
        header.add("Total-items", Long.toString(drinks.getTotalElements()));
        return dtos;
    }

    public Drink saveNewFromDTOS(DrinkDTO drinkDTO) {
        if(drinkDTO.getName() == null){
            return null;
        }
        if(drinkRepo.findByName(drinkDTO.getName()) != null){
            throw new MenuItemExist(drinkDTO.getName());
        }
        Drink drink = new Drink(drinkDTO);
        return drinkRepo.save(drink);
    }
}
