package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Enums.ORDER_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private ORDER_STATUS orderStatus;
    private double order_price;
    private String table;
    private String allergyDescription;
    private List<OrderItemDTO> orderItems;

    public OrderDTO(ORDER_STATUS orderStatus, double order_price, String table, String allergyDescription) {
        this.orderStatus = orderStatus;
        this.order_price = order_price;
        this.table = table;
        this.allergyDescription = allergyDescription;
    }
}
