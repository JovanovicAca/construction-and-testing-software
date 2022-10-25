package com.example.KTS.Config;

import com.example.KTS.Model.Helper.Role;
import com.example.KTS.Model.Staff.Administration;
import com.example.KTS.Model.Staff.Employee;
import com.example.KTS.Model.Staff.Manager;
import com.example.KTS.Model.Staff.Waiter;
import com.example.KTS.Repo.ClientRepo;
import com.example.KTS.Repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {
    @Bean
    CommandLineRunner commandLineRunnerEmployee(ClientRepo clientRepo, RoleRepo roleRepo){
        return args -> {

            Employee employee01 = new Employee(
                    "Uros",
                    "Blagojevic",
                    100000,
                    "1234",
                    "$2a$12$BKZlNSMhhIVTVsiPt9Qz8eXX/eEoCkP851wlR3/mxJqbfdWsOjgM6"
            );

            Employee employee02 = new Employee(
                    "Marko",
                    "Vukotic",
                    100000,
                    "pema",
                    "$2a$12$Z4f..H6.J7jypCZIxoBQ7eZOBBa3xzkLwumpwg4Yd1KRUrODfCWJi"
            );

            //Novi konstruktor
            Employee employee03 = new Employee(
                    "Nikola",
                    "Petrovic",
                    100000,
                    "user",
                    "$2a$12$Z4f..H6.J7jypCZIxoBQ7eZOBBa3xzkLwumpwg4Yd1KRUrODfCWJi"
            );
            Role role01 = new Role("ROLE_CLIENT");
            Role role02 = new Role("ROLE_ADMIN");
            Role role03 = new Role("ROLE_CHEF");
            Role role04 = new Role("ROLE_MANAGER");
            roleRepo.saveAll(List.of(role01,role02,role03,role04));
            employee03.getRoles().add(role01);
            employee03.getRoles().add(role02);
            employee02.getRoles().add(role03);
            employee02.getRoles().add(role04);
            employee01.getRoles().add(role01);
            employee01.getRoles().add(role02);
            employee01.getRoles().add(role03);
            employee01.getRoles().add(role04);
            Manager employee04 = new Manager(
                    "Aleksandar",
                    "Jovanovic",
                    100000,
                    "mena",
                    "$2a$12$Z4f..H6.J7jypCZIxoBQ7eZOBBa3xzkLwumpwg4Yd1KRUrODfCWJi"
            );
            employee04.getRoles().add(role04);
            Administration administration01 = new Administration(
                    "Administrator",
                    "Administrator",
                    10000,
                    "admin",
                    "admin"
            );
            administration01.setRoles(List.of(role02));

//            Waiter waiter = new Waiter(
//                    "Konobar",
//                    "Konobarovic",
//                    300000,
//                    "konobar"
//            );
            Waiter waiter = new Waiter();
            waiter.setName("Konobar");
            waiter.setSurname("Konobarovic");
            waiter.setPassword("konobar");
            waiter.setPaycheck(30000);
            waiter.setUsername("asda");

            clientRepo.saveAll(List.of(employee01,employee02,employee03,employee04,administration01,waiter));
        };
    }
}
