package com.example.KTS.Repo;

import com.example.KTS.Model.Staff.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepo extends JpaRepository<Administration,Long> {

}
