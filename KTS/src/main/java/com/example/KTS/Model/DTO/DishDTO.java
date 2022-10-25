package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Enums.DISH_TYPE;

import java.util.HashSet;
import java.util.Set;

public class DishDTO {

    private String name;
    private String description;
    private Double price;
    //TODO: NACIN DA SE DODA SLIKA
    private Double prepareTime;
    private DISH_TYPE dishType;
    private Set<IngredientDTO> ingredients = new HashSet<IngredientDTO>();
    public DishDTO(){

    }

    public DishDTO(String name, String description, Double price, Double prepareTime) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.prepareTime = prepareTime;
        this.dishType = dishType;
    }

    public DishDTO(String name, String description, Double price, Double prepareTime, DISH_TYPE dishType, Set<IngredientDTO> ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.prepareTime = prepareTime;
        this.dishType = dishType;
        this.ingredients = ingredients;
    }

    public Set<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Double prepareTime) {
        this.prepareTime = prepareTime;
    }

    public DISH_TYPE getDishType() {
        return dishType;
    }

    public void setDishType(DISH_TYPE dishType) {
        this.dishType = dishType;
    }
}
