package com.example.KTS.Model.DTO;

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
public class DishMenuDTO {

    private String menuName;
    //yyyy-MM-dd-HH-mm-ss.zzz
    @JsonDeserialize
    private LocalDateTime startDate;
    @JsonDeserialize
    private LocalDateTime endDate;
    private List<DishNoIngredientsDTO> dishes = new ArrayList<DishNoIngredientsDTO>();

    public DishMenuDTO(String menuName) {
        this.menuName = menuName;
    }
}
