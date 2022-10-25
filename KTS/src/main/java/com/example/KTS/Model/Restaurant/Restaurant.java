package com.example.KTS.Model.Restaurant;

import com.example.KTS.Model.Staff.Client;
import com.example.KTS.Model.Restaurant.Menu;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long Id;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Menu> menus = new HashSet<Menu>();

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Client> users = new HashSet<Client>();


    public Restaurant(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Client> getUsers() {
        return users;
    }

    public void setUsers(Set<Client> users) {
        this.users = users;
    }
}
