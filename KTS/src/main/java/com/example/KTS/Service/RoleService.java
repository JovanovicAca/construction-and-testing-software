package com.example.KTS.Service;

import com.example.KTS.Model.Helper.Role;
import com.example.KTS.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role findById(Long id){
        Role auth = this.roleRepo.getOne(id);
        return auth;
    }

    public List<Role> findByName(String name){
        List<Role> roles = this.roleRepo.findByName(name);
        return roles;
    }
}
