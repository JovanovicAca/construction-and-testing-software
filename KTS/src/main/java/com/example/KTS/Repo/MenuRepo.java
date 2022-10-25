package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.DishMenu;
import com.example.KTS.Model.Restaurant.DrinkMenu;
import com.example.KTS.Model.Restaurant.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu,Long> {



    @Query(value = "SELECT m from Menu m where :date BETWEEN m.startDate and m.endDate")
    public List<Menu> getAllByDateTime(LocalDateTime date);

    Menu findByMenuName(String name);

    DrinkMenu findDrinkMenuByMenuName(String menuName);
}
