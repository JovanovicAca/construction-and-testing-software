package com.example.KTS.Service;

import com.example.KTS.Model.Restaurant.MenuItem;
import com.example.KTS.Repo.MenuItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepo menuItemRepo;

    @Autowired
    public MenuItemService(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    public List<MenuItem> findAllByIngredient(String name){
        return menuItemRepo.findAllByIngredient(name);
    }
}
