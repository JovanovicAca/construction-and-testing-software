package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem,Long> {


    @Query(value = "SELECT mi FROM menu_item mi inner join mi.ingredients i where i.name = :name")
    public List<MenuItem> findAllByIngredient(String name);

//    @Query(value = "SELECT mi FROM menu_item mi where mi.drink_type IS NOT NULL ")
//    Page<Drink> findAllDrinks();
}
