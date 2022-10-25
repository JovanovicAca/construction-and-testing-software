package com.example.KTS.Controller;

import com.example.KTS.Service.BartenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bartender")
public class BartenderController {

    private final BartenderService bartenderService;

    @Autowired
    public BartenderController(BartenderService bartenderService) {
        this.bartenderService = bartenderService;
    }

//    @PreAuthorize("hasRole('CHEF')")
//    @PostMapping(value = "order/add")
//    public ResponseEntity<String> addNewOrder(@RequestBody OrderDTO orderDTO){
//        System.out.println(orderDTO.getOrder_price());
//        try{
//            return new ResponseEntity<>(bartenderService.addNewOrder(orderDTO), HttpStatus.OK);
//        }
//        catch(Exception e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

}
