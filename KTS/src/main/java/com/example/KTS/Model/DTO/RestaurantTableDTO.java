package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Staff.Waiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTableDTO {

    private Long id;
    private int numberOfSeats;
    private TABLE_STATUS tableStatus;
    private Waiter waiter;
    private Long order;
    private int x;
    private int y;
    private String tableNumber;

    private int cols;
    private int rows;

}
