package com.example.KTS.Model.Restaurant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "menu_item")
@JsonDeserialize(as = MenuItem.class)

@ToString
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private String recipe;
    private Double purchasePrice;
    private Double price;
    private Double discount;
    private String image;
    @OneToOne(mappedBy = "menuItem")
    private OrderItem orderItem;
    @ManyToMany()
    @JoinTable(name = "menu_item_ingredient",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    public MenuItem(Long id, String name, String description, String recipe, Double purchasePrice, Double price, Double discount, String image, Set<Ingredient> ingredients) {
    }
}