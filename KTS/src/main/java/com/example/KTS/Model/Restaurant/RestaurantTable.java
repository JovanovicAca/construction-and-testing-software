package com.example.KTS.Model.Restaurant;

import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Staff.Waiter;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @GeneratedValue
    private Long id;

    private TABLE_STATUS tableStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private Waiter waiter;

    @OneToOne(mappedBy = "restaurantTable",cascade = CascadeType.ALL)
    private Order order;

    private int x;
    private int y;
    private String tableNumber;
    private int cols;
    private int rows;
}
