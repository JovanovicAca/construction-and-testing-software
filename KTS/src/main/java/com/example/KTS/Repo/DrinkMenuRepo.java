package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.DrinkMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkMenuRepo extends JpaRepository<DrinkMenu,Long> {
    DrinkMenu findByMenuName(String name);
}
