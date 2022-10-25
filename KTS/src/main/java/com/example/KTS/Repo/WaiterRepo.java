package com.example.KTS.Repo;

import com.example.KTS.Model.Staff.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepo extends JpaRepository<Waiter,Long> {
}
