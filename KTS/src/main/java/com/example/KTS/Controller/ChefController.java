package com.example.KTS.Controller;

import com.example.KTS.Service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;

public class ChefController {

    private final ChefService chefService;

    @Autowired
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }


}
