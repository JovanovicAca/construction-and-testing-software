package com.example.KTS.Controller;

import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.Order;
import com.example.KTS.Model.Restaurant.OrderItem;
import com.example.KTS.Repo.DishRepo;
import com.example.KTS.Repo.DrinkRepo;
import com.example.KTS.Repo.EmployeeRepo;
import com.example.KTS.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/report")
public class ReportController {

    private final ReportService reportService;
    private final EmployeeService employeeService;
    private final DishService dishService;
    private final DrinkService drinkService;
    private final OrderService orderService;

    @Autowired
    public ReportController(ReportService reportService,EmployeeService employeeService,DishService dishService,DrinkService drinkService,OrderService orderService) {
        this.reportService = reportService;
        this.employeeService = employeeService;
        this.dishService = dishService;
        this.drinkService = drinkService;
        this.orderService = orderService;
    }

    @GetMapping("/yearExpensesPay/{year}")
    public ResponseEntity<Float> yearExpensesPay(@PathVariable int year){
        return new ResponseEntity<Float>(reportService.getExpensesPay(year),new HttpHeaders(), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('MANAGER')")
//    @GetMapping("/monthIncomeDish/{dateFrom}/{dateTo}")
//    public Map<String, Integer> monthIncomeDish(@PathVariable String dateFrom, @PathVariable String dateTo){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dateFd = LocalDate.parse(dateFrom, formatter);
//        LocalDate dateTd = LocalDate.parse(dateTo, formatter);
//
//        LocalDateTime dateF = dateFd.atStartOfDay();
//        LocalDateTime dateT = dateTd.atStartOfDay();
//
//        Map<String, Integer> monthDishes = new HashMap<>();
//        List<Order> allOrders = reportService.findAllInBetweenPayed(dateF,dateT);
//
//        for(Order o : allOrders) {
//            List<OrderItem> orderItems = o.getOrderItems();
//            for (OrderItem ot : orderItems) {
//                if (ot.getMenuItem().getClass().equals(Dish.class)) {
//                    int count = monthDishes.getOrDefault(ot.getMenuItem().getName(), 0);
//                    if (monthDishes.containsKey(ot.getMenuItem().getName())) {
//                        monthDishes.put(ot.getMenuItem().getName(), count + 1);
//                    } else {
//                        monthDishes.put(ot.getMenuItem().getName(), 1);
//                    }
//                }
//            }
//        }
//        return monthDishes;
//    }

//    @PreAuthorize("hasRole('MANAGER')")
//    @GetMapping("/monthIncomeDrink/{dateFrom}/{dateTo}")
//    public Map<String, Integer> monthIncomeDrink(@PathVariable String dateFrom, @PathVariable String dateTo){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dateFd = LocalDate.parse(dateFrom, formatter);
//        LocalDate dateTd = LocalDate.parse(dateTo, formatter);
//
//        LocalDateTime dateF = dateFd.atStartOfDay();
//        LocalDateTime dateT = dateTd.atStartOfDay();
//
//        Map<String, Integer> monthDrinks = new HashMap<>();
//        List<Order> allOrders = reportService.findAllInBetweenPayed(dateF,dateT);
//
//        for(Order o : allOrders){
//            List<OrderItem> orderItems = o.getOrderItems();
//            for(OrderItem ot : orderItems){
//                if(ot.getMenuItem().getClass().equals(Drink.class)){
//                    int count = monthDrinks.getOrDefault(ot.getMenuItem().getName(), 0);
//                    if(monthDrinks.containsKey(ot.getMenuItem().getName()))
//                    {
//                        monthDrinks.put(ot.getMenuItem().getName(), count+1);
//                    }
//                    else {
//                        monthDrinks.put(ot.getMenuItem().getName(), 1);
//                    }
//                }
//            }
//        }
//        return monthDrinks;
//    }

//    @PreAuthorize("hasRole('MANAGER')")
//    @GetMapping("/monthExpenses/{dateFrom}/{dateTo}")
//    public double monthIncomeDish(@PathVariable String dateFrom, @PathVariable String dateTo){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dateFd = LocalDate.parse(dateFrom, formatter);
//        LocalDate dateTd = LocalDate.parse(dateTo, formatter);
//
//        LocalDateTime dateF = dateFd.atStartOfDay();
//        LocalDateTime dateT = dateTd.atStartOfDay();
//
//        List<Order> allOrders = reportService.findAllInBetweenPayed(dateF,dateT);
//
//        double sum = 0;
//        for(Order o : allOrders) {
//            List<OrderItem> orderItems = o.getOrderItems();
//            for (OrderItem ot : orderItems) {
//                sum += ot.getMenuItem().getPurchasePrice();
//            }
//        }
//        return sum;
//    }

//    @PreAuthorize("hasRole('MANAGER')")
//    @GetMapping("/monthExpenses")
//    public ResponseEntity<Map<Integer,Integer>> monthIncomeDish(@RequestParam("time") String time, @RequestParam(required = false) int year){
//        Map<Integer,Integer> report = new HashMap<Integer,Integer>();
//        if(time.equals("Mesecni")){
//            List<Order> allOrders = reportService.findAllInYear(year);
//            Map<LocalDateTime,List<Order>> data = allOrders.stream().collect(Collectors.groupingBy(d -> d.getdateFinished.withDayOfMonth(1)));
//            for(int i = 1;i<13;i++){
//                report.put(i,0);
//            }
//            for(LocalDateTime date: data.keySet()){
//                report.put(date.toLocalDate().getMonth().getValue(),data.get(date).size());
//            }
//        }
//        else if(time.equals("Goisnji")){
//            for(int i = 0;i<5;i++){
//                report.put(2018+i,reportService.findAllInYear(2018+i).size());
//            }
//        }
//        else if(time.equals("Kvartalni")){
//            List<Order> allOrders = reportService.findAllInYear(year);
//            Map<LocalDateTime,List<Order>> data = allOrders.stream().collect(Collectors.groupingBy(d -> d.getdateFinished.get(IsoFields.QUARTER_OF_YEAR)));
//            for(int i = 1;i<5;i++){
//                report.put(i,0);
//            }
//            for (Object date : data.keySet()) {
//                report.put((Integer) date, data.get(date).size());
//            }
//        }
//        return new ResponseEntity<Map<Integer,Integer>>(report, HttpStatus.OK);
//    }
}
