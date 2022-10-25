//package com.example.KTS.Config;
//
//import com.example.KTS.Model.Enums.DISH_TYPE;
//import com.example.KTS.Model.Enums.DRINK_TYPE;
//import com.example.KTS.Model.Restaurant.*;
//import com.example.KTS.Repo.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//import java.util.Set;
//
//@Configuration
//public class DishConfig {
//    @Bean
//    CommandLineRunner DishConfigCMDLineRunner(DrinkRepo drinkRepo, DishRepo dishRepo, IngredientRepo ingredientRepo) {
//        return args -> {
//            Ingredient ingredient01 = new Ingredient();
//            ingredient01.setName("Belo meso");
//            ingredient01.setPrice(350.0);
//            ingredient01.setIsAllergic(false);
////            ingredient01.setAllergic(false);
//
//            Ingredient ingredient02 = new Ingredient();
//            ingredient02.setName("Tuzlansa So");
//            ingredient02.setPrice(80.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient03 = new Ingredient();
//            ingredient03.setName("Mleko");
//            ingredient03.setPrice(120.0);
//
//
//            Ingredient ingredient04 = new Ingredient();
//            ingredient04.setName("Ulje");
//            ingredient04.setPrice(300.0);
//            ingredient01.setIsAllergic(false);
//
//
//
//            Ingredient ingredient05 = new Ingredient();
//            ingredient05.setName("Cokolada");
//            ingredient05.setPrice(155.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient06 = new Ingredient();
//            ingredient06.setName("Kikiriki");
//            ingredient06.setPrice(750.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient07 = new Ingredient();
//            ingredient07.setName("Pecurke");
//            ingredient07.setPrice(220.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient08 = new Ingredient();
//            ingredient08.setName("Neutralna Pavlaka");
//            ingredient08.setPrice(90.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient09 = new Ingredient();
//            ingredient09.setName("Secer");
//            ingredient09.setPrice(50.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient10 = new Ingredient();
//            ingredient10.setName("Kackavalj");
//            ingredient10.setPrice(510.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient11 = new Ingredient();
//            ingredient11.setName("Soda");
//            ingredient11.setPrice(20.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient12 = new Ingredient();
//            ingredient12.setName("Jagoda");
//            ingredient12.setPrice(720.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient13 = new Ingredient();
//            ingredient13.setName("Kokain");
//            ingredient13.setPrice(10000000.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient14 = new Ingredient();
//            ingredient14.setName("Narandza");
//            ingredient14.setPrice(250.0);
//            ingredient01.setIsAllergic(false);
//
//
//            Ingredient ingredient15 = new Ingredient();
//            ingredient15.setName("Snicla");
//            ingredient15.setPrice(850.0);
//            ingredient01.setIsAllergic(false);
//
//
//            ingredientRepo.saveAll(List.of(ingredient01,ingredient02,ingredient03,ingredient04,ingredient05,
//                    ingredient06,ingredient07,ingredient08,ingredient10,ingredient11,
//                    ingredient12,ingredient14,ingredient15));
//
//            Drink drink01 = new Drink();
//            drink01.setName("Sok od Jagode");
//            drink01.setDescription("Cedjeni sok");
//            drink01.setPurchasePrice(150.0);
//            drink01.setPrice(260.0);
//            drink01.setDiscount(0.0);
//            drink01.setDrinkType(DRINK_TYPE.JUICE);
//            drink01.setIngredients(Set.of(ingredient12,ingredient11));
//
//            Drink drink02 = new Drink();
//            drink02.setName("Koka Kola");
//            drink02.setDescription("Napitak od koke");
//            drink02.setPurchasePrice(70.0);
//            drink02.setPrice(150.0);
//            drink02.setDiscount(0.0);
//            drink02.setDrinkType(DRINK_TYPE.COLD_DRINK);
//            drink02.setIngredients(Set.of(ingredient12));
//
//            Drink drink03 = new Drink();
//            drink03.setName("Fanta");
//            drink03.setDescription("Fantastican Sok");
//            drink03.setPurchasePrice(80.0);
//            drink03.setPrice(160.0);
//            drink03.setDiscount(0.0);
//            drink03.setDrinkType(DRINK_TYPE.COLD_DRINK);
//            drink03.setIngredients(Set.of(ingredient12,ingredient14));
//
//            Drink drink04 = new Drink();
//            drink04.setName("Sveps");
//            drink04.setDescription("Gaziran sok");
//            drink04.setPurchasePrice(90.0);
//            drink04.setPrice(160.0);
//            drink04.setDiscount(0.0);
//            drink04.setDrinkType(DRINK_TYPE.COLD_DRINK);
//            drink04.setIngredients(Set.of(ingredient12,ingredient14));
//
//            Drink drink05 = new Drink();
//            drink05.setName("Sok od Jabuke");
//            drink05.setDescription("Cedjeni Sok");
//            drink05.setPurchasePrice(50.0);
//            drink05.setPrice(200.0);
//            drink05.setDiscount(0.0);
//            drink05.setDrinkType(DRINK_TYPE.JUICE);
//            drink05.setIngredients(Set.of(ingredient12,ingredient14));
//
//            drinkRepo.saveAll(List.of(drink01,drink02,drink03,drink04,drink05));
//
//            Dish dish01 = new Dish();
//            dish01.setName("Piletina u Besamel Sosu");
//            dish01.setDescription("Description");
//            dish01.setPurchasePrice(280.0);
//            dish01.setPrice(460.0);
//            dish01.setDiscount(0.0);
//            dish01.setPrepareTime(10.0);
//            dish01.setRecipe("Recipe");
//            dish01.setDishType(DISH_TYPE.MAIN_COURSE);
//            dish01.setIngredients(Set.of(ingredient01,ingredient02,ingredient04,ingredient08,ingredient10));
//
//            Dish dish02 = new Dish();
//            dish02.setName("Engleski Dorucak");
//            dish02.setDescription("Description");
//            dish02.setPurchasePrice(80.0);
//            dish02.setPrice(220.0);
//            dish02.setDiscount(0.0);
//            dish02.setPrepareTime(9.0);
//            dish02.setRecipe("Recipe");
//            dish02.setDishType(DISH_TYPE.BREAKFAST);
//            dish02.setIngredients(Set.of(ingredient02,ingredient04,ingredient07));
//
//            Dish dish06 = new Dish();
//            dish06.setName("Srpski Dorucak");
//            dish06.setDescription("Description");
//            dish06.setPurchasePrice(180.0);
//            dish06.setPrice(420.0);
//            dish06.setDiscount(0.0);
//            dish06.setPrepareTime(15.0);
//            dish06.setRecipe("Recipe");
//            dish06.setDishType(DISH_TYPE.BREAKFAST);
//            dish06.setIngredients(Set.of(ingredient02,ingredient04,ingredient14,ingredient15));
//
//            Dish dish07 = new Dish();
//            dish07.setName("Meksicki Dorucak");
//            dish07.setDescription("Description");
//            dish07.setPurchasePrice(300.0);
//            dish07.setPrice(520.0);
//            dish07.setDiscount(0.0);
//            dish07.setPrepareTime(20.0);
//            dish07.setRecipe("Recipe");
//            dish07.setDishType(DISH_TYPE.BREAKFAST);
//            dish07.setIngredients(Set.of(ingredient02,ingredient08,ingredient10,ingredient01));
//
//            Dish dish03 = new Dish();
//            dish03.setName("Muffin Od Cokolade");
//            dish03.setDescription("Description");
//            dish03.setPurchasePrice(120.0);
//            dish03.setPrice(300.0);
//            dish03.setDiscount(10.0);
//            dish03.setPrepareTime(15.0);
//            dish03.setRecipe("Recipe");
//            dish03.setDishType(DISH_TYPE.DESERT);
//            dish03.setIngredients(Set.of(ingredient03,ingredient05,ingredient06));
//
//            Dish dish04 = new Dish();
//            dish04.setName("File Minjon");
//            dish04.setDescription("Description");
//            dish04.setPurchasePrice(480.0);
//            dish04.setPrice(960.0);
//            dish04.setDiscount(0.0);
//            dish04.setPrepareTime(23.0);
//            dish04.setRecipe("Recipe");
//            dish04.setDishType(DISH_TYPE.MAIN_COURSE);
//            dish04.setIngredients(Set.of(ingredient15,ingredient04,ingredient07));
//
//            Dish dish05 = new Dish();
//            dish05.setName("Potaz");
//            dish05.setDescription("Description");
//            dish05.setPurchasePrice(150.0);
//            dish05.setPrice(360.0);
//            dish05.setDiscount(0.0);
//            dish05.setPrepareTime(12.0);
//            dish05.setRecipe("Recipe");
//            dish05.setDishType(DISH_TYPE.SOUP);
//            dish05.setIngredients(Set.of(ingredient07,ingredient08));
//
//            dishRepo.saveAll(List.of(dish01,dish02,dish03,dish04,dish05,dish06,dish07));
//        };
//    }
//}