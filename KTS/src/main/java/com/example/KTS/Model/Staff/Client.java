package com.example.KTS.Model.Staff;

import com.example.KTS.Model.Enums.Gender;
import com.example.KTS.Model.Helper.Role;
import com.example.KTS.Model.Restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.hash.Hashing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthOfDate")
    private LocalDate birthOfDate;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "paycheck")
    private double paycheck;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles= new ArrayList<Role>();

    public Client(){

    }

    public Client(String name, String surname, double paycheck, String password) {
        this.salt = generateSalt();
        this.name = name;
        this.surname = surname;
        this.paycheck = paycheck;
        this.password = hashPassword(password,this.salt);
    }

    public Client(String name, String surname, double paycheck, String username,String password) {
        this.name = name;
        this.surname = surname;
        this.paycheck = paycheck;
        this.username = username;
        this.password = password;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getPaycheck() {
        return paycheck;
    }

    public void setPaycheck(double paycheck) {
        this.paycheck = paycheck;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(LocalDate birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String generateSalt(){

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 64;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String hashPassword(String password, String salt){
        password += salt;

        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Double.compare(client.paycheck, paycheck) == 0 && Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(birthOfDate, client.birthOfDate) && gender == client.gender && Objects.equals(email, client.email) && Objects.equals(password, client.password) && Objects.equals(salt, client.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthOfDate, gender, paycheck, email, password, salt);
    }


}
