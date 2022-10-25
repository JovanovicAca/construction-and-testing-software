package com.example.KTS.Controller;

import com.example.KTS.Service.AdministrationService;
import com.example.KTS.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/administration")
public class AdministrationController {

    private final AdministrationService administrationService;
    private final ClientService clientService;

    @Autowired
    public AdministrationController(AdministrationService administrationService, ClientService clientService) {
        this.administrationService = administrationService;
        this.clientService = clientService;
    }

//    @GetMapping(path = "/loginAdministration/{username}/{password}")
//    public Response<Administration> loginAdministration(@PathVariable String username, @PathVariable String password){
//        return administrationService.loginAdministration(username,password);
//    }



}
