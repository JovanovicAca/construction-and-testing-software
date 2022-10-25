package com.example.KTS.Service;

import com.example.KTS.Exception.IngredientFoundException;
import com.example.KTS.Exception.IngredientNotFoundException;
import com.example.KTS.Model.DTO.IngredientDTO;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Repo.IngredientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepo ingredientRepo;
    @Autowired
    private ModelMapper mapper;


    public Ingredient findByName(String name){
        return ingredientRepo.findByName(name);
    }

    public List<Ingredient> all(){
        return ingredientRepo.findAll();
    }
    public void add(Ingredient i){
        ingredientRepo.save(i);
    }

    public Ingredient save(Ingredient ingredient) {
        ingredientRepo.save(ingredient);
        return ingredient;
    }

    public Ingredient saveFromDTO(IngredientDTO ingredientDTO) throws Exception{
        if(findByName(ingredientDTO.getName()) != null){
            throw new IngredientFoundException();
        };
        Ingredient ingredient = mapper.map(ingredientDTO, Ingredient.class);
        System.out.println(ingredient);
        return ingredientRepo.save(ingredient);
    }
    public Ingredient updateIngredient(IngredientDTO ingredientDTO) throws Exception {
        Ingredient ingredient = findByName(ingredientDTO.getName());
        if(ingredient == null){
            throw new IngredientNotFoundException();
        }
        Long id = ingredient.getId();
        ingredient = mapper.map(ingredientDTO, Ingredient.class);
        ingredient.setId(id);
        return ingredientRepo.save(ingredient);
    }
    public void delete(Ingredient ingredient) {
        ingredientRepo.deleteById(ingredient.getId());
    }

    public List<Ingredient> getAll(Pageable pageable, HttpHeaders hh) {
        Page<Ingredient> ingredients = this.ingredientRepo.findAll(pageable);

        hh.add("Total-items", Long.toString(ingredients.getTotalElements()));

        return ingredients.getContent();
    }

    public List<String> getAllNames() {
        List<Ingredient> ingredients = this.ingredientRepo.findAll();
        List<String> names = new ArrayList<>();
        for (Ingredient i : ingredients) {
            names.add(i.getName());
        }
        return names;
    }

    public List<Ingredient> getAll(Pageable page, HttpHeaders header, String searchInput) {
        Page<Ingredient> ingredients = this.ingredientRepo.findByNameContainingIgnoreCase(page,searchInput);

        header.add("Total-items", Long.toString(ingredients.getTotalElements()));

        return ingredients.getContent();
    }
}
