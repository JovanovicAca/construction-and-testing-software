package com.example.KTS.Repo;

import com.example.KTS.Model.Staff.Bartender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BartenderRepo extends JpaRepository<Bartender,Long> {

}
