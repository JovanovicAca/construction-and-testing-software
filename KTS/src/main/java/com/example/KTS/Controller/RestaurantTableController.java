package com.example.KTS.Controller;

import com.example.KTS.Model.DTO.RestaurantTableDTO;
import com.example.KTS.Model.Enums.TABLE_STATUS;
import com.example.KTS.Model.Mappers.TableMapper;
import com.example.KTS.Model.Restaurant.RestaurantTable;
import com.example.KTS.Service.RestaurantTableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/table")
public class RestaurantTableController {
    private final RestaurantTableService rts;

    public RestaurantTableController(RestaurantTableService rts) {
        this.rts = rts;
    }

    @GetMapping(path = "/getTables")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RestaurantTableDTO>> getTables(){

        List<RestaurantTable> list;
        list = rts.getTables();
        if (list == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List<RestaurantTableDTO> dtos = list.stream().map(table -> TableMapper.convertToDTO(table)).collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @PostMapping(path = "/updateTables")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateTables(@RequestBody List<RestaurantTableDTO> listDTO){
        rts.reset(); // Cistimo bazu pa je opet punimo sa novim arzuriranim stolovima
        for (RestaurantTableDTO rtDTO : listDTO) {
            rts.save(TableMapper.convertToTable(rtDTO));
        }
        return new ResponseEntity<>("Tables saved!",HttpStatus.OK);
    }

    @GetMapping(path = "/updateTableStatus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateTable(@RequestParam String id, @RequestParam TABLE_STATUS status) throws Exception {
        try{
            rts.updateTableStatus(Long.parseLong(id),status);
            return new ResponseEntity<>("Table saved!",HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Table not found!");
        }
    }
    @GetMapping(path = "/getById")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantTable> getById(@RequestParam String id) throws Exception {
        try{
            return new ResponseEntity<>(rts.findById(id),HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Table not found!");
        }


    }
}
