//package com.example.KTS.Config;
//
//import com.example.KTS.Model.Helper.Role;
//import com.example.KTS.Repo.ClientRepo;
//import com.example.KTS.Repo.RoleRepo;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import java.util.List;
//
//@Configuration
//@DependsOn("ClientConfig")
//public class RoleConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerEmployee(RoleRepo roleRepo){
//        return args -> {
//            Role role1 = new Role("ROLE_WAITER");
//            roleRepo.saveAll(List.of(role1));
//        };
//
//    }
//}
