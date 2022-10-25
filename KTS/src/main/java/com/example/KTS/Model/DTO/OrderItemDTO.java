package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Enums.DISH_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private String name;
    private double quantity;
    private DISH_STATUS dish_status;
}
