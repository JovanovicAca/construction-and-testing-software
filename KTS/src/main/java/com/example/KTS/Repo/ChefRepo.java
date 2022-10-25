package com.example.KTS.Repo;

import com.example.KTS.Model.Staff.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepo extends JpaRepository<Chef,Long> {

}
