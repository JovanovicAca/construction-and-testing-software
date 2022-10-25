package com.example.KTS.Service;

import com.example.KTS.Exception.DishNotFoundException;
import com.example.KTS.Exception.MenuItemExist;
import com.example.KTS.Exception.MenuItemNotFound;
import com.example.KTS.Model.DTO.DishDTO;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Repo.DishRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DishService {

    private final DishRepo dishRepo;
    private final IngredientService ingredientService;
    private ModelMapper mapper;
    @Autowired
    public DishService(DishRepo dishRepo, IngredientService ingredientService, ModelMapper mapper) {
        this.dishRepo = dishRepo;
        this.ingredientService = ingredientService;
        this.mapper = mapper;
    }

    public List<Dish> getAllDishes() {
        return this.dishRepo.findAll();
    }
    public List<Dish> getAllDishes(Pageable page, HttpHeaders header, String query) {
        Page<Dish> items = this.dishRepo.findByNameContainingIgnoreCase(page,query);
        header.add("Total-items", Long.toString(items.getTotalElements()));

        return items.getContent();
    }
    public Dish findByName(String name){
        return dishRepo.findByName(name);
    }

    public Dish save(Dish dish) {
        return dishRepo.save(dish);
    }

    public Dish saveNewFromDTO(DishDTO dishDTO) throws MenuItemExist{
        if(dishRepo.findByName(dishDTO.getName()) != null){
            throw new MenuItemExist(dishDTO.getName());
        }
        Set<Ingredient> ingredients = dishDTO.getIngredients().stream().map(ingredientDTO -> ingredientService.findByName(ingredientDTO.getName())).collect(Collectors.toSet());
        Dish dish = mapper.map(dishDTO, Dish.class);
//        Dish dish = new Dish();
//        dish.DTOConvert(dishDTO);
        dish.setIngredients(ingredients);
        dish.setOrderItem(null);
        return dishRepo.save(dish);
    }
    public Dish updateFromDTO(DishDTO dishDTO) throws MenuItemNotFound {
        Dish dish = findByName(dishDTO.getName());
        if(dish == null){
            throw new MenuItemNotFound(dishDTO.getName());
        }
        Long id = dish.getId();
        Set<Ingredient> ingredients = dishDTO.getIngredients().stream().map(ingredientDTO -> ingredientService.findByName(ingredientDTO.getName())).collect(Collectors.toSet());
        dish = mapper.map(dishDTO, Dish.class);
        dish.setIngredients(ingredients);
        dish.setId(id);
        return save(dish);
    }

    public void deleteDishName(String name) throws DishNotFoundException {
        Dish dish = findByName(name);
        if(dish == null){
            throw new DishNotFoundException();
        }
        dishRepo.delete(dish);


    }
}
