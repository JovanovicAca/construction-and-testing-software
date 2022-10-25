package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Enums.Gender;
import com.example.KTS.Model.Helper.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {
    private String name;
    private String username;
    private String surname;
    private Gender gender;
    private LocalDate birthOfDate;
    private double paycheck;
    private String email;
    private String password;
    private List<Role> roles= new ArrayList<Role>();


    public EmployeeDTO(String name, String username, String surname, Gender gender, LocalDate birthOfDate, double paycheck, String email, String password, List<Role> roles) {
        this.name = name;
        this.username = password;
        this.surname = surname;
        this.gender = gender;
        this.birthOfDate = birthOfDate;
        this.paycheck = paycheck;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    
    public EmployeeDTO(String name, String surname, String gender, LocalDate birthOfDate, double paycheck, String email, String password, List<String> roles) {
        this.name = name;
        this.surname = surname;
        this.gender = convertToGender(gender);
        this.birthOfDate = birthOfDate;
        this.paycheck = paycheck;
        this.email = email;
        this.password = password;
        this.roles = convertToRole(roles);
        this.username = password;
    }

    private List<Role> convertToRole(List<String> roles) {
        List<Role> rls = new ArrayList<Role>();
        for (String role: roles) {
            Role r = new Role();
            r.setName(role);
            rls.add(r);
        }
        return rls;
    }

    private Gender convertToGender(String gender) {
        Gender g = Gender.valueOf(gender);
        return g;
    }

    public EmployeeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(LocalDate birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public double getPaycheck() {
        return paycheck;
    }

    public void setPaycheck(double paycheck) {
        this.paycheck = paycheck;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", birthOfDate=" + birthOfDate +
                ", paycheck=" + paycheck +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
