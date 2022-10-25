package com.example.KTS.Repo;

import com.example.KTS.Model.Staff.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {

    public Optional<Client> findOneByEmail(String email);

    public Client findByUsername(String username);
}
