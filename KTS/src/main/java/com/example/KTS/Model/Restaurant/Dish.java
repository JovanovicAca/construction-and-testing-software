package com.example.KTS.Model.Restaurant;

import com.example.KTS.Model.DTO.DishDTO;
import com.example.KTS.Model.Enums.DISH_TYPE;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Dish")
@Table
@AllArgsConstructor
@JsonDeserialize(as = Dish.class)
public class Dish extends MenuItem {
    private Double prepareTime;
    private DISH_TYPE dishType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "dishes")
    private Set<DishMenu> dishMenus;
    public Dish(){

    }

    public void DTOConvert(DishDTO dto){
        setDishType(dto.getDishType());
        setPrepareTime(dto.getPrepareTime());
        setName(dto.getName());
        setPrepareTime(dto.getPrepareTime());
        setDescription(dto.getDescription());
        setPrice(dto.getPrice());
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

    public Set<DishMenu> getDishMenus() {
        return dishMenus;
    }

    public void setDishMenus(Set<DishMenu> dishMenus) {
        this.dishMenus = dishMenus;
    }
}
