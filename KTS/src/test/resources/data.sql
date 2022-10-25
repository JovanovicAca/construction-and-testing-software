-- drop table *;
-- drop table menu_item_ingredient;
-- drop table order_item;
-- drop table dish_menu;
-- drop table dish;
-- drop table ingredient;


--
-- drop table order_item;





-- CREATE TABLE public.ingredient
-- (
--     id bigint auto_increment,
--     is_allergic boolean,
--     name character,
--     price double precision
-- );
-- CREATE TABLE public.dish
-- (
--     id bigint auto_increment,
--     description character,
--     discount double precision,
--     image character,
--     name character,
--     price double precision,
--     purchase_price double precision,
--     recipe character,
--     dish_type integer,
--     prepare_time double precision,
-- );


INSERT INTO ingredient (is_allergic,name,price) VALUES (FALSE,'kikiriki',200.0);
INSERT INTO ingredient (is_allergic,name,price) VALUES (FALSE,'badem',100.0);

INSERT INTO menu_item(dtype, description, discount, name, price, purchase_price, recipe) VALUES ('dish', 'Opis', 10.0,'Sarma', 20.0,30.0,'Recept');
INSERT INTO menu_item(dtype, description, discount, name, price, purchase_price, recipe) VALUES ('drink', 'Opis', 10.0,'Koka Kola', 20.0,30.0,'Recept');
INSERT INTO menu(dtype, menu_name) VALUES ('dish_menu', 'Dorucak');
INSERT INTO menu(dtype, menu_name) VALUES ('drink_menu', 'Gazirana Pica');
INSERT INTO orders(id, order_price)VALUES (9999,2000.0);
--INSERT INTO dish(name,description,recipe,purchase_price,price,discount,prepare_time,dish_type) VALUES ("Belo Meso","Opis","Recept",100,200,10,20,"APPETIZER");
--INSERT INTO drink(name,description,recipe,purchase_price,price,discount,prepare_time,drink_type_type) VALUES ("Koka Kola","Opis","Recept",100,200,10,20,"GAZIRANO PICE");
--INSERT INTO drink_menu(name) VALUES ("Gazirani Sokovi Menu");
--INSERT INTO drink_menu(name) VALUES ("Cedjeni Sokovi Menu");
--INSERT INTO dish_menu(name) VALUES ("Dorucak Menu");
--INSERT INTO dish_menu(name) VALUES ("Rucak Menu");
--INSERT INTO orders(order_status,order_price) VALUE ("ORDER_READY",100);

INSERT INTO menu_item (dtype, description, discount, image, name, price, purchase_price, recipe, dish_type, prepare_time) VALUES
                  ('dish','asdsad', 0.0, 'asdsad', 'peceno meso', 120.0, 100.0, 'asdsadsad', 1, 100.0);
INSERT INTO menu_item (dtype, description, discount, image, name, price, purchase_price, recipe, dish_type, prepare_time) VALUES
    ('dish', 'asdsad', 0.0, 'asdsad', 'belo meso', 120.0, 100.0, 'asdsadsad', 1, 100.0);

    INSERT INTO menu_item (dtype, description, discount, image, name, price, purchase_price, recipe, dish_type, prepare_time) VALUES
        ('dish', 'Classic burger', 0.0, 'x', 'Burger', 300.0, 100.0, 'Meat, bread', 1, 100.0);
-- INSERT INTO public.dish(
--     id, description, discount, image, name, price, purchase_price, recipe, dish_type, prepare_time)
-- VALUES (1, '1', 100.0, 'x', 'sadasd', 100.0, 95.0, 'asdasd', 1, 100.0);
    INSERT INTO menu_item (dtype, description, discount, image, name, price, purchase_price, recipe, dish_type, prepare_time) VALUES
            ('dish', 'Classic burger', 0.0, 'x', 'Hamburger', 120.0, 100.0, 'Meat, bread', 1, 100.0);
     INSERT INTO menu_item (dtype, description, discount, image, name, price, purchase_price, recipe, dish_type, prepare_time, drink_type)
     	VALUES ('drink', 'Orange juice', 0.0, 'x', 'Juice', 220.0, 70.0, 'Two oranges', 1, 20.0, 1);
     INSERT INTO menu_item (dtype, description, discount, image, name, price, purchase_price, recipe, dish_type, prepare_time, drink_type)
         	VALUES ('drink', 'Apple juice', 0.0, 'x', 'Apple juice', 180.0, 40.0, 'Three apples', 1, 10.0, 1);
--
-- INSERT INTO menu_item_ingredient (menu_item_id, ingredient_id) VALUES (1, 1);

INSERT INTO client(
	dtype, birth_of_date, email, gender, name, password, paycheck, salt, surname, username, restaurant_id)
	VALUES ('Employee', null, 'vuketa@gmail.com', null, 'Mare', '$2a$12$Z4f..H6.J7jypCZIxoBQ7eZOBBa3xzkLwumpwg4Yd1KRUrODfCWJi', 1000000.0, null, 'Care', 'MareCare', null);

INSERT INTO role(id,name) VALUES (1,'ROLE_CHEF');
INSERT INTO role(id,name) VALUES (2,'ROLE_MANAGER');
INSERT INTO client_role(user_id, role_id) VALUES (1, 1);
INSERT INTO client_role(user_id, role_id) VALUES (1, 2);

-- INSERT INTO order_item(
--     id, allergy_description, dish_status, quantity, menu_item_id)
-- VALUES (1, 'asdasdasdas', 3, 3, 1);
--
--
-- INSERT INTO order_item(
--     id, allergy_description, dish_status, quantity, menu_item_id)
-- VALUES (2, 'asdasdasdas', 1, 3, 1);
-- INSERT INTO public.orders(
--     id, order_status, order_price, restaurant_table_id)
-- VALUES (?, ?, ?, ?);
--
-- INSERT INTO dish_menu(
--     id, end_date, menu_name, start_date, restaurant_id)
-- VALUES (1, 2022-11-26T15:53:16, "asdasdas", 2021-11-26T15:53:16, null);

-- INSERT INTO restaurant_table(id, cols,rows,table_number,table_status,x,y)
--     VALUES(1,3,3,'3',1,3,4)
-- INSERT INTO restaurant_table(id, cols,rows,table_number,table_status,x,y)
--     VALUES(2,3,3,'3',1,3,4)
--
INSERT INTO orders(id, order_status,order_price)
    values(1L,null,2500)