package com.example.KTS.Model.Mappers;

import com.example.KTS.Model.DTO.EmployeeDTO;
import com.example.KTS.Model.Staff.Employee;
import org.modelmapper.ModelMapper;

public class EmployeeMapper {

    public static EmployeeDTO convertToEmployeeDto(Employee emp){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(emp,EmployeeDTO.class);
    }

    public static Employee convertToEmployee(EmployeeDTO empdto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(empdto, Employee.class);
    }
}
