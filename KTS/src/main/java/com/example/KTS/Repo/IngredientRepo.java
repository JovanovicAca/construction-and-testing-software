package com.example.KTS.Repo;

import com.example.KTS.Model.Restaurant.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient,Long> {
    public Ingredient findOneByName(String name);

    public Ingredient findByName(String name);


    Page<Ingredient> findByNameContainingIgnoreCase(Pageable page, String name);
}
