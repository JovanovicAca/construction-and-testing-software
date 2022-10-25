package com.example.KTS.Controller;

import com.example.KTS.Exception.ClientNotFound;
import com.example.KTS.Model.DTO.EmployeeDTO;
import com.example.KTS.Model.Mappers.EmployeeMapper;
import com.example.KTS.Model.Staff.Employee;
import com.example.KTS.Service.ClientService;
import com.example.KTS.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ClientService clientService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,ClientService clientService) {
        this.employeeService = employeeService;
        this.clientService = clientService;
    }


    @GetMapping(path = "/getEmployees/{pageNo}/{pageSize}/{sortBy}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Employee>> getEmployees(
            @PathVariable int pageNo,
            @PathVariable int pageSize,
            @PathVariable String sortBy)
    {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployees(pageNo,pageSize,sortBy),new HttpHeaders(),HttpStatus.OK);
    }

    @GetMapping(path = "/getEmployeeById/{id}")
    public Employee getEmployee(@PathVariable Long id){

        return this.employeeService.getEmployeeById(id);
    }

//    @GetMapping(path = "/loginEmployee/{password}")
//    public Response<Employee> loginEmployee(@PathVariable String password){
//        return this.employeeService.loginEmployee(password);
//    }

    @GetMapping(value = "/getEmployee/{username}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> getEmployee(@PathVariable String username){
        Employee emp;
        try{
            emp = (Employee) clientService.findByUsername(username);
        }
        catch(ClientNotFound e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        EmployeeDTO empdto = EmployeeMapper.convertToEmployeeDto(emp);
        return new ResponseEntity(empdto,HttpStatus.FOUND);
    }

    @PutMapping(value = "/editEmployee")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> editEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            clientService.updateFromDto(employeeDTO);
        }catch (ClientNotFound e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity("Employee updated!",HttpStatus.OK);
    }

    @PostMapping(value = "/addEmployee")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDto){
        employeeDto.setUsername(employeeDto.getPassword());
        try{
            clientService.findByUsernameAdd(employeeDto.getUsername());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        try{
            clientService.findOneByEmailAdd(employeeDto.getEmail());
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        clientService.add(EmployeeMapper.convertToEmployee(employeeDto));
        return new ResponseEntity(employeeDto,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteEmployee/{username}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> deleteEmployee(@PathVariable String username) throws ClientNotFound{
        Employee employee;
        try{
            employee = (Employee) clientService.findByUsername(username);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        clientService.delete(employee);
        return new ResponseEntity("Employee deleted!",HttpStatus.OK);
    }
}
