package com.example.KTS.Service;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.Staff.Administration;
import com.example.KTS.Model.Staff.Employee;
import com.example.KTS.Repo.ClientRepo;
import com.example.KTS.Repo.EmployeeRepo;
import org.dom4j.io.ElementModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getEmployees(int pageNo,int pageSize,String sortBy){
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = employeeRepo.findAll(paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }
        else
            return null;
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        return employee.orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }


    public Response<Employee> loginEmployee(String password) {
        for(Employee employee : employeeRepo.findAll()){
            String hashPassword = employee.hashPassword(password,employee.getSalt());
            if(hashPassword.equals(employee.getPassword())){
                return new Response<>(employee);
            }
        }
        return null;
    }

}
