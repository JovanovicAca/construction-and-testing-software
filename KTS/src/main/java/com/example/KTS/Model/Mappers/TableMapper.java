package com.example.KTS.Model.Mappers;

import com.example.KTS.Model.DTO.RestaurantTableDTO;
import com.example.KTS.Model.Restaurant.RestaurantTable;
import org.modelmapper.ModelMapper;

public class TableMapper {

    public static RestaurantTable convertToTable(RestaurantTableDTO table) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(table, RestaurantTable.class);
    }
    public static RestaurantTableDTO convertToDTO(RestaurantTable table) {
        ModelMapper modelMapper = new ModelMapper();
        RestaurantTableDTO dto = modelMapper.map(table, RestaurantTableDTO.class);
//        dto.setOrder(table.getOrder().getId());
        return dto;
    }
}
