package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.DishMenu;
import com.example.KTS.Model.Restaurant.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public interface DishMenuRepo extends JpaRepository<DishMenu,Long> {
    DishMenu findByMenuName(String name);

    @Query(value = "SELECT m from Menu m where :date BETWEEN m.startDate and m.endDate")
    public List<DishMenu> getAllByDateTime(LocalDateTime date);


}
