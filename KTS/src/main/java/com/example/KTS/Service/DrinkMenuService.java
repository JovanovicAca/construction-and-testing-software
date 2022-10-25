package com.example.KTS.Service;

import com.example.KTS.Exception.MenuExpection;
import com.example.KTS.Model.DTO.DrinkMenuDTO;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.DrinkMenu;
import com.example.KTS.Repo.DrinkMenuRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DrinkMenuService {

    private final DrinkMenuRepo drinkMenuRepo;
    private final ModelMapper mapper;
    private final DrinkService drinkService;

    @Autowired
    public DrinkMenuService(DrinkMenuRepo drinkMenuRepo, ModelMapper mapper,DrinkService drinkService) {
        this.drinkMenuRepo = drinkMenuRepo;
        this.mapper = mapper;
        this.drinkService = drinkService;
    }
    public List<DrinkMenu> getAll(){return drinkMenuRepo.findAll();}

    public DrinkMenu findByName(String name){
        return drinkMenuRepo.findByMenuName(name);
    }
    public void checkDrinkMenu(DrinkMenuDTO drinkMenuDTO) throws MenuExpection{
        if(drinkMenuDTO.getStartDate().isAfter(drinkMenuDTO.getEndDate())){
            throw new MenuExpection(drinkMenuDTO.getMenuName());
        };
        if(drinkMenuDTO.getEndDate().isBefore(LocalDateTime.now())){
            throw new MenuExpection(drinkMenuDTO.getMenuName());
        }
    }

    public DrinkMenu addDrinkMenuDto(DrinkMenuDTO drinkMenuDTO) throws MenuExpection {
        if(findByName(drinkMenuDTO.getMenuName()) != null){
            throw new MenuExpection(drinkMenuDTO.getMenuName());
        }
        checkDrinkMenu(drinkMenuDTO);
        DrinkMenu drinkMenu = mapper.map(drinkMenuDTO,DrinkMenu.class);
        Set<Drink> drink = drinkMenuDTO.getDrinks().stream().map(drinks -> drinkService.findByName(drinks.getName())).collect(Collectors.toSet());
        drinkMenu.setDrinks(drink);
        return drinkMenuRepo.save(drinkMenu);
    }

    public DrinkMenu updateDrinkMenu(DrinkMenuDTO drinkMenuDTO) {
        checkDrinkMenu(drinkMenuDTO);
        DrinkMenu drinkMenu = findByName(drinkMenuDTO.getMenuName());
        if(drinkMenu == null){
            throw new MenuExpection(drinkMenuDTO.getMenuName());
        }
        Long id = drinkMenu.getId();
        drinkMenu = mapper.map(drinkMenuDTO,DrinkMenu.class);
        Set<Drink> drink = drinkMenuDTO.getDrinks().stream().map(drinks -> drinkService.findByName(drinks.getName())).collect(Collectors.toSet());
        drinkMenu.setDrinks(drink);
        drinkMenu.setId(id);
        return drinkMenuRepo.save(drinkMenu);
    }

    public void deleteDrinkMenu(String menuName) throws MenuExpection{
        DrinkMenu drinkMenu = findByName(menuName);
        if(drinkMenu == null){
            throw new MenuExpection(menuName);
        }
        drinkMenuRepo.delete(drinkMenu);
    }
}
