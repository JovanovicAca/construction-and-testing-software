package com.example.KTS.Service;

import com.example.KTS.Model.DTO.RestaurantTableDTO;
import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Mappers.TableMapper;
import com.example.KTS.Model.Restaurant.RestaurantTable;
import com.example.KTS.Repo.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableService {

    private final TableRepo tableRepo;
//    private final OrderService orderService;
    @Autowired
    public RestaurantTableService(TableRepo tableRepo) {
        this.tableRepo = tableRepo;
//        this.orderService = orderService;
    }

   /* public void add(RestaurantTable rt){
        tableRepo.save(rt);
    }*/

    public List<RestaurantTable> getTables(){
        List<RestaurantTable> result = tableRepo.findAll();
        return result;
    }

    public void save(RestaurantTable rt) {
        tableRepo.save(rt);
    }

    public RestaurantTable findByTableNumber(String name){

        return tableRepo.findByTableNumber(name);
    }
    public void reset(){
        tableRepo.deleteAll();
    }


    public void updateTable(RestaurantTableDTO restaurantTable) throws Exception{
        RestaurantTable restTable  = tableRepo.findById(restaurantTable.getId()).get();
        if(restTable == null){
            throw new Exception();
        }

        Long id = restTable.getId();
        restTable = TableMapper.convertToTable(restaurantTable);
        restTable.setId(id);
        try{
//            restTable.setOrder(orderService.findById(restaurantTable.getOrder()));
            tableRepo.save(restTable);
        }
        catch(Exception e){
                throw new Exception();
        }

    }
    public void createTableOrder(RestaurantTable restaurantTable) throws Exception{
//        RestaurantTable restTable  = tableRepo.findById(restaurantTable.getId()).get();
//        if(restTable == null){
//            throw new Exception();
//        }
//        restTable = restaurantTable;
//        System.out.println(restaurantTable.getOrder());
//        orderService.save(restaurantTable.getOrder());
        tableRepo.save(restaurantTable);

    }
    public RestaurantTable findById(String id) throws Exception{
        try {
            return tableRepo.findById(Long.parseLong(id)).get();
        }
        catch(Exception e){
            throw new Exception();
        }
    }

    public void updateTableStatus(Long id, TABLE_STATUS status)throws Exception {
        RestaurantTable table = tableRepo.findById(id).get();
        if(table == null){
            throw new Exception();
        }
        table.setTableStatus(status);
        tableRepo.save(table);
    }
}
