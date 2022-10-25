package com.example.KTS.Service;

import com.example.KTS.Controller.wrapper.Response;
import com.example.KTS.Model.Restaurant.Dish;
import com.example.KTS.Model.Staff.Administration;
import com.example.KTS.Model.Staff.Employee;
import com.example.KTS.Repo.AdministrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrationService {

    private final AdministrationRepo administrationRepo;

    @Autowired
    public AdministrationService(AdministrationRepo administrationRepo) {
        this.administrationRepo = administrationRepo;
    }

    public Response<Administration> loginAdministration(String username, String password) {
        for(Administration administration : administrationRepo.findAll()){
            if(administration.getUsername().equals(username) &&
                administration.getPassword().equals(administration.hashPassword(password,administration.getSalt()))){
                return new Response<>(administration);
            }
        }
        return null;
    }

}
