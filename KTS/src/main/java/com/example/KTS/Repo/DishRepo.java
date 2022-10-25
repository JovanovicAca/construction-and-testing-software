package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepo extends JpaRepository<Dish,Long> {
    Dish findByName(String name);


    Page<Dish> findByNameContainingIgnoreCase(Pageable page, String name);
}
