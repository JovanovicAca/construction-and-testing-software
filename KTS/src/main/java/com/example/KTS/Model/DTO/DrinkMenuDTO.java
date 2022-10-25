package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Restaurant.Drink;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrinkMenuDTO {
    private String menuName;
    //yyyy-MM-dd-HH-mm-ss.zzz
    @JsonDeserialize
    private LocalDateTime startDate;
    @JsonDeserialize
    private LocalDateTime endDate;
    private List<Drink> drinks = new ArrayList<Drink>();

    public DrinkMenuDTO(String menuName) {
        this.menuName = menuName;
    }
}
