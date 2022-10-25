package com.example.KTS.Service;

import com.example.KTS.Exception.ClientExists;
import com.example.KTS.Exception.ClientNotFound;
import com.example.KTS.Model.DTO.EmployeeDTO;
import com.example.KTS.Model.Staff.Client;
import com.example.KTS.Model.Staff.Employee;
import com.example.KTS.Repo.ClientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {

    private final ClientRepo clientRepo;
    private ModelMapper mapper;

    @Autowired
    public ClientService(ClientRepo clientRepo, ModelMapper mapper) {
        this.clientRepo = clientRepo;
        this.mapper = mapper;
    }

    public Optional<Client> findOneByEmail(String email) throws ClientNotFound, ClientExists{
        return clientRepo.findOneByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepo.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return client;
        }
    }

    public Client findByUsername(String username) throws ClientNotFound{
        if(clientRepo.findByUsername(username) == null){
            throw new ClientNotFound(username);
        }
        else{
            return clientRepo.findByUsername(username);
        }
    }

    public void updateFromDto(EmployeeDTO employeeDTO) throws ClientNotFound {
        Employee client = (Employee) findByUsername(employeeDTO.getUsername());
        if(client == null){
            throw new ClientNotFound(employeeDTO.getUsername());
        }
        System.out.println(employeeDTO.getEmail() + employeeDTO.getUsername());
        Long id = client.getId();
        client = mapper.map(employeeDTO,Employee.class);
        client.setId(id);
        clientRepo.save(client);
    }

    public void add(Employee employee) {
        clientRepo.save(employee);
    }

    public void delete(Employee employee) {
        clientRepo.deleteById(employee.getId());
    }

    public void findByUsernameAdd(String username) throws ClientExists{
        if(clientRepo.findByUsername(username) != null)
            throw new ClientExists(username);

    }

    public void findOneByEmailAdd(String email) {
        if(clientRepo.findOneByEmail(email).isPresent())
            throw new ClientExists(email);
    }
}
