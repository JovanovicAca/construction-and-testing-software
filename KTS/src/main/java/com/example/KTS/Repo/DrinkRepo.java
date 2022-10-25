package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepo extends JpaRepository<Drink,Long> {

    public Drink findByName(String name);

    Page<Drink> findByNameContainingIgnoreCase(Pageable page, String searchInput);
}
