package com.example.KTS.Service;

import org.springframework.stereotype.Service;

@Service
public class BartenderService {

//    private final BartenderRepo bartenderRepo;
//    private final ModelMapper mapper;
//    private final OrderItemService orderItemService;
//    private final DrinkService drinkService;
//    private final OrderService orderService;
//    private final RestaurantTableService tableService;
//    @Autowired
//    public BartenderService(BartenderRepo bartenderRepo, ModelMapper mapper, OrderItemService orderItemService, DrinkService drinkService, OrderService orderService, RestaurantTableService tableService) {
//        this.bartenderRepo = bartenderRepo;
//        this.mapper = mapper;
//        this.orderItemService = orderItemService;
//        this.drinkService = drinkService;
//        this.orderService = orderService;
//        this.tableService = tableService;
//    }


//    public String addNewOrder(OrderDTO orderDTO) throws OrderException{
//        if(orderDTO.getOrderStatus() != ORDER_STATUS.PENDING){
//            throw new OrderException("order");
//        }
//
//        Order o = new Order();
//        //pronadjemo sto
//        o.setRestaurantTable(tableService.findByTableNumber(orderDTO.getTable()));
//        o.setOrderStatus(orderDTO.getOrderStatus());
//
//        // za svaki orderItem napravimo novi objekat
//        List<OrderItem> orderItems = orderDTO.getOrderItemDTOs().stream().map(orderItemDTO -> orderItemService.createOrderItem(orderItemDTO,orderDTO.getAllergyDescription())).collect(Collectors.toList());
//        //TODO: SORTIRANJE PO PRIORITETU
//        o.setOrderItems(orderItems);
//        o.setOrder_price(orderDTO.getOrder_price());
//        orderService.save(o);
//    }

}
