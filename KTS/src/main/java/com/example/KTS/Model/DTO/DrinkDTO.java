package com.example.KTS.Model.DTO;

import com.example.KTS.Model.Enums.DRINK_TYPE;
import com.example.KTS.Model.Restaurant.Drink;
import com.example.KTS.Model.Restaurant.DrinkMenu;
import com.example.KTS.Model.Restaurant.Ingredient;
import com.example.KTS.Model.Restaurant.OrderItem;

import java.util.ArrayList;
import java.util.List;


public class DrinkDTO {

    private String name;
    private String description;
    private String recipe;
    private Double purchasePrice;
    private Double price;
    private Double discount;
    private String image;
    private OrderItem orderItem;
    private DRINK_TYPE drinkType;
    private DrinkMenu drinkMenu;
    private List<String> ingredients;

    public DrinkDTO(String name, String description, String recipe, Double purchasePrice, Double price, Double discount) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.discount = discount;
    }

    public DrinkDTO(String name, String description, String recipe, Double purchasePrice,
                    Double price, Double discount, String image,
                    OrderItem orderItem, DRINK_TYPE drinkType, DrinkMenu drinkMenu) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.orderItem = orderItem;
        this.drinkType = drinkType;
        this.drinkMenu = drinkMenu;
    }
    public DrinkDTO(String name, String description, String recipe, Double purchasePrice,
                    Double price, Double discount, String drinkType, DrinkMenu drinkMenu) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.discount = discount;
        this.drinkType = convertToDrinkType(drinkType);
        this.drinkMenu = drinkMenu;
 //       this.ingredients = ingredients;
//        this.ingredients = convertToIngredient(ingredients);
    }

    public DrinkDTO(Drink drink){
        this.name = drink.getName();
        this.description = drink.getDescription();
        this.recipe = drink.getRecipe();
        this.purchasePrice = drink.getPurchasePrice();
        this.price = drink.getPrice();
        this.discount = drink.getDiscount();
        this.image = drink.getImage();
        this.drinkType = drink.getDrinkType();
    }

    private DRINK_TYPE convertToDrinkType(String drinkType) {
        DRINK_TYPE d = DRINK_TYPE.valueOf(drinkType);
        return d;
    }

    private List<Ingredient> convertToIngredient(ArrayList<String> ingredients) {
        List<Ingredient> ings = new ArrayList<>();
        for (String ingredient: ingredients) {
            Ingredient i = new Ingredient();
            i.setName(ingredient);
            ings.add(i);
        }
        return ings;
    }

    public DrinkDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public DRINK_TYPE getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DRINK_TYPE drinkType) {
        this.drinkType = drinkType;
    }

    public DrinkMenu getDrinkMenu() {
        return drinkMenu;
    }

    public void setDrinkMenu(DrinkMenu drinkMenu) {
        this.drinkMenu = drinkMenu;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "DrinkDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", recipe='" + recipe + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", price=" + price +
                ", discount=" + discount +
                ", image='" + image + '\'' +
                ", orderItem=" + orderItem +
                ", drinkType=" + drinkType +
                ", drinkMenu=" + drinkMenu +
                '}';
    }
}
