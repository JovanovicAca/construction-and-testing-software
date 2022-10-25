package com.example.KTS.Service;

import com.example.KTS.Model.Enums.ORDER_STATUS;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Model.Staff.Employee;
import com.example.KTS.Repo.DishRepo;
import com.example.KTS.Repo.DrinkRepo;
import com.example.KTS.Repo.EmployeeRepo;
import com.example.KTS.Repo.OrderRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final OrderRepo orderRepo;
    private final EmployeeRepo employeeRepo;
    private final DishRepo dishRepo;
    private final DrinkRepo drinkRepo;


    public ReportService(OrderRepo orderRepo,EmployeeRepo employeeRepo,DishRepo dishRepo,DrinkRepo drinkRepo) {
        this.orderRepo = orderRepo;
        this.employeeRepo = employeeRepo;
        this.dishRepo = dishRepo;
        this.drinkRepo = drinkRepo;
    }
    public float getExpensesPay(int year) {
        List<Employee> employeeList = employeeRepo.findAll();
        float sumPaycheck = 0;
        for (Employee e : employeeList)
        {
            sumPaycheck += e.getPaycheck();
        }

        float ret = sumPaycheck * 12;
        return ret;
    }

//    public List<Order> findAllInBetweenPayed(LocalDateTime dateF, LocalDateTime dateT) {
//        return orderRepo.findAllInBetweenPayed(dateF,dateT);
//    }

//    public List<Order> findAllInYear(int year) {
//        return orderRepo.findAllInYear(year);
//    }
}
