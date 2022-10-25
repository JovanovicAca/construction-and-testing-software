package com.example.KTS.Service;

import com.example.KTS.Repo.ChefRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChefService {
    private final ChefRepo chefRepo;
    @Autowired
    public ChefService(ChefRepo chefRepo) {
        this.chefRepo = chefRepo;
    }
}
