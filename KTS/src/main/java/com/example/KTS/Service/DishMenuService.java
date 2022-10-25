package com.example.KTS.Service;

import com.example.KTS.Exception.MenuExpection;
import com.example.KTS.Model.DTO.DishMenuDTO;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.DishMenu;
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
public class DishMenuService {

    private final MenuRepo menuRepo;
    private final DishMenuRepo dishMenuRepo;
    private final DrinkMenuRepo drinkMenuRepo;
    private final ModelMapper mapper;
    private final DishService dishService;
    private final DrinkService drinkService;

    @Autowired
    public DishMenuService(MenuRepo menuRepo, DishMenuRepo dishMenuRepo, DrinkMenuRepo drinkMenuRepo, ModelMapper mapper, DishService dishService,DrinkService drinkService) {
        this.menuRepo = menuRepo;
        this.dishMenuRepo = dishMenuRepo;
        this.drinkMenuRepo = drinkMenuRepo;
        this.mapper = mapper;
        this.dishService = dishService;
        this.drinkService = drinkService;
    }

    public List<DishMenu> getAll() {
        return dishMenuRepo.findAll();
    }

    public DishMenu findByName(String name){
        return dishMenuRepo.findByMenuName(name);
    }

    public List<DishMenuDTO> getAllMenusCurrentDate(LocalDateTime dateTime){
        return dishMenuRepo.getAllByDateTime(dateTime).stream().map(DishMenu -> mapper.map(DishMenu, DishMenuDTO.class)).collect(Collectors.toList());
    }

    public DishMenu addDishMenuDTO(DishMenuDTO dishMenuDTO) throws MenuExpection {
        if(findByName(dishMenuDTO.getMenuName()) != null){
            throw new MenuExpection(dishMenuDTO.getMenuName());
        }
        checkMenu(dishMenuDTO);
        DishMenu dishMenu = mapper.map(dishMenuDTO,DishMenu.class);
        Set<Dish> dishes = dishMenuDTO.getDishes().stream().map(dishNoIngredientsDTO -> dishService.findByName(dishNoIngredientsDTO.getName())).collect(Collectors.toSet());
        dishMenu.setDishes(dishes);
        return dishMenuRepo.save(dishMenu);
    }
    public DishMenu updateDishMenu(DishMenuDTO dishMenuDTO){
        checkMenu(dishMenuDTO);
        DishMenu dishMenu = findByName(dishMenuDTO.getMenuName());
        if(dishMenu == null){
            throw new MenuExpection(dishMenuDTO.getMenuName());
        }
        Long id = dishMenu.getId();
        dishMenu = mapper.map(dishMenuDTO,DishMenu.class);
        Set<Dish> dishes = dishMenuDTO.getDishes().stream().map(dishNoIngredientsDTO -> dishService.findByName(dishNoIngredientsDTO.getName())).collect(Collectors.toSet());
        dishMenu.setDishes(dishes);
        dishMenu.setId(id);
        return dishMenuRepo.save(dishMenu);
    }

    public void deleteDishMenu(String name) throws MenuExpection{
        DishMenu dishMenu = findByName(name);
        if(dishMenu == null){
            throw new MenuExpection(name);
        }
        dishMenuRepo.delete(dishMenu);
    }

    public void checkMenu(DishMenuDTO dishMenuDTO) throws MenuExpection {
        if (dishMenuDTO.getStartDate().isAfter(dishMenuDTO.getEndDate())) {
            throw new MenuExpection(dishMenuDTO.getMenuName());
        }
        ;
        if (dishMenuDTO.getEndDate().isBefore(LocalDateTime.now())) {
            throw new MenuExpection(dishMenuDTO.getMenuName());
        }
    }
}
