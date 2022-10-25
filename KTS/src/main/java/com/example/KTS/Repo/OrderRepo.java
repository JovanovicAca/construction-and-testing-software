package com.example.KTS.Repo;

import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {


    //    @Query("SELECT o FROM Order o WHERE (o.dateFinished BETWEEN ?1 AND ?2) AND o.order_status = ORDER_STATUS.PAYED")
//    List<Order> findAllInBetweenPayed(LocalDateTime dateF, LocalDateTime dateT);
//
//    @Query("SELECT o from ORDER o WHERE EXTRACT(YEAR FROM o.dateFinished) =:year")
//    List<Order> findAllInYear(int year);

    List<Order> findByOrderStatus(ORDER_STATUS order_status);

    @Query(value="SELECT o from Order o where o.orderStatus = :PENDING")
    List<Order> findAllNotFinished();


    @Query(value="SELECT o from Order o where o.orderStatus = 2")
    List<Order> findAllFinished();

    @Query(value="SELECT o from Order o where o.restaurantTable.id = :id")
    Order findByRestaurantTable(long id);
}
