package com.example.KTS.Repo;

import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Model.Restaurant.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepo extends JpaRepository<RestaurantTable,Long> {

    List<RestaurantTable> findByTableStatus(TABLE_STATUS table_status);

    RestaurantTable findByTableNumber(String name);
}
