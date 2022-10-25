package com.example.KTS.Service;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Exception.MenuExpection;
import com.example.KTS.Model.DTO.DrinkMenuDTO;
import com.example.KTS.Model.DTO.DishMenuDTO;
import com.example.KTS.Model.Restaurant.*;
import com.example.KTS.Repo.DishMenuRepo;
import com.example.KTS.Repo.DrinkMenuRepo;
import com.example.KTS.Repo.MenuRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepo menuRepo;
    private final DishMenuRepo dishMenuRepo;
    private final DrinkMenuRepo drinkMenuRepo;
    private final ModelMapper mapper;
    private final DishService dishService;
    private final DrinkService drinkService;

    @Autowired
    public MenuService(MenuRepo menuRepo, DishMenuRepo dishMenuRepo, DrinkMenuRepo drinkMenuRepo, ModelMapper mapper, DishService dishService,DrinkService drinkService) {
        this.menuRepo = menuRepo;
        this.dishMenuRepo = dishMenuRepo;
        this.drinkMenuRepo = drinkMenuRepo;
        this.mapper = mapper;
        this.dishService = dishService;
        this.drinkService = drinkService;
    }

    public List<Menu> getAllMenus() {
        return menuRepo.findAll();
    }

//    public Response<List<DrinkMenu>> getAllDrinkMenus() {
//        return new Response<>(drinkMenuRepo.findAll());
//    }
//
//    public Response<List<DishMenu>> getAllDishMenus() {
//        return new Response<>(dishMenuRepo.findAll());
//    }
//
//    public DishMenu findByName(String name){
//        return dishMenuRepo.findByMenuName(name);
//    }
//    public List<DishMenuDTO> getAllMenusCurrentDate(LocalDateTime dateTime){
//        return menuRepo.getAllByDateTime(dateTime).stream().map(Menu -> mapper.map(Menu, DishMenuDTO.class)).collect(Collectors.toList());
//    }
//
//
//    public void addDishMenuDTO(DishMenuDTO dishMenuDTO) throws MenuExpection {
//
//        if(findByName(dishMenuDTO.getMenuName()) != null){
//            throw new MenuExpection(dishMenuDTO.getMenuName());
//        }
//        checkMenu(dishMenuDTO);
//        DishMenu dishMenu = mapper.map(dishMenuDTO,DishMenu.class);
//        Set<Dish> dishes = dishMenuDTO.getDishes().stream().map(dishNoIngredientsDTO -> dishService.findByName(dishNoIngredientsDTO.getName())).collect(Collectors.toSet());
//        dishMenu.setDishes(dishes);
//        menuRepo.save(dishMenu);
//    }
//    public void updateDishMenu(DishMenuDTO dishMenuDTO){
//
//        checkMenu(dishMenuDTO);
//        DishMenu dishMenu = findByName(dishMenuDTO.getMenuName());
//        if(dishMenu == null){
//            throw new MenuExpection(dishMenuDTO.getMenuName());
//        }
//        Long id = dishMenu.getId();
//        dishMenu = mapper.map(dishMenuDTO,DishMenu.class);
//        Set<Dish> dishes = dishMenuDTO.getDishes().stream().map(dishNoIngredientsDTO -> dishService.findByName(dishNoIngredientsDTO.getName())).collect(Collectors.toSet());
//        dishMenu.setDishes(dishes);
//        dishMenu.setId(id);
//        menuRepo.save(dishMenu);
//    }
//
//    public void deleteDishMenu(String name) throws MenuExpection{
//        DishMenu dishMenu = (DishMenu) findByName(name);
//        if(dishMenu == null){
//            throw new MenuExpection(name);
//        }
//        menuRepo.delete(dishMenu);
//    }
//
//    public void checkMenu(DishMenuDTO dishMenuDTO) throws MenuExpection{
//        if(dishMenuDTO.getStartDate().isAfter(dishMenuDTO.getEndDate())){
//            throw new MenuExpection(dishMenuDTO.getMenuName());
//        };
//        if(dishMenuDTO.getEndDate().isBefore(LocalDateTime.now())){
//            throw new MenuExpection(dishMenuDTO.getMenuName());
//        }
//    }
//
//    public void checkDrinkMenu(DrinkMenuDTO drinkMenuDTO) throws MenuExpection{
//        if(drinkMenuDTO.getStartDate().isAfter(drinkMenuDTO.getEndDate())){
//            throw new MenuExpection(drinkMenuDTO.getMenuName());
//        };
//        if(drinkMenuDTO.getEndDate().isBefore(LocalDateTime.now())){
//            throw new MenuExpection(drinkMenuDTO.getMenuName());
//        }
//    }
//
//    public void addDrinkMenuDto(DrinkMenuDTO drinkMenuDTO) throws MenuExpection {
//        if(findByName(drinkMenuDTO.getMenuName()) != null){
//            throw new MenuExpection(drinkMenuDTO.getMenuName());
//        }
//        checkDrinkMenu(drinkMenuDTO);
//        DrinkMenu drinkMenu = mapper.map(drinkMenuDTO,DrinkMenu.class);
//        Set<Drink> drink = drinkMenuDTO.getDrinks().stream().map(drinks -> drinkService.findByName(drinks.getName())).collect(Collectors.toSet());
//        drinkMenu.setDrinks(drink);
//        menuRepo.save(drinkMenu);
//    }
//
//    public void updateDrinkMenu(DrinkMenuDTO drinkMenuDTO) {
//        checkDrinkMenu(drinkMenuDTO);
//        //OVOOOOOOOOOOOO
//        DrinkMenu drinkMenu = findByNameDrink(drinkMenuDTO.getMenuName());
//        if(drinkMenu == null){
//            throw new MenuExpection(drinkMenuDTO.getMenuName());
//        }
//        Long id = drinkMenu.getId();
//        drinkMenu = mapper.map(drinkMenuDTO,DrinkMenu.class);
//        Set<Drink> drink = drinkMenuDTO.getDrinks().stream().map(drinks -> drinkService.findByName(drinks.getName())).collect(Collectors.toSet());
//        drinkMenu.setDrinks(drink);
//        drinkMenu.setId(id);
//        menuRepo.save(drinkMenu);
//    }
//
//    private DrinkMenu findByNameDrink(String menuName) {
//        return menuRepo.findDrinkMenuByMenuName(menuName);
//    }
//
//    public void deleteDrinkMenu(String menuName) throws MenuExpection{
//        DrinkMenu drinkMenu = findByNameDrink(menuName);
//        if(drinkMenu == null){
//            throw new MenuExpection(menuName);
//        }
//        menuRepo.delete(drinkMenu);
//    }
}
