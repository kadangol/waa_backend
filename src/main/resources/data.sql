
-- INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN') ;
-- INSERT INTO roles (id, name) VALUES (2, 'ROLE_SELLER') ;
-- INSERT INTO roles (id, name) VALUES (3, 'ROLE_BUYER') ;
-- INSERT INTO users (id, email, password, username, FULLNAME) VALUES (100, 'admin@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'admin', 'admin') ;
-- INSERT INTO users (id, email, password, username, FULLNAME) VALUES (102, 'seller@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'seller', 'admin') ;
-- INSERT INTO users (id, email, password, username, FULLNAME) VALUES (103, 'buyer@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'buyer', 'admin') ;
-- INSERT INTO user_roles (user_id, role_id) VALUES (100, 1) ;
-- INSERT INTO user_roles (user_id, role_id) VALUES (102, 2) ;
-- INSERT INTO user_roles (user_id, role_id) VALUES (103, 3) ;
-- INSERT INTO seller(id, approvalstatus, userid) VALUES (1,0,102) ;
-- insert into  buyer(id, points, city, state, street, zipcode, userid) values(1, 50, 'Fairfield','IA', '100 North', '52557', 103) ;



-- for POSTGRESQL
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, name) VALUES (2, 'ROLE_SELLER') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, name) VALUES (3, 'ROLE_BUYER') ON CONFLICT DO NOTHING;
INSERT INTO users (id, email, password, username, FULLNAME) VALUES (100, 'admin11@yopmail.com', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'admin', 'admin') ON CONFLICT DO NOTHING;
INSERT INTO users (id, email, password, username, FULLNAME) VALUES (102, 'seller11@yopmail.com', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'seller', 'admin') ON CONFLICT DO NOTHING;
INSERT INTO users (id, email, password, username, FULLNAME) VALUES (103, 'buyer11@yopmail.com', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'buyer', 'admin') ON CONFLICT DO NOTHING;
INSERT INTO user_roles (user_id, role_id) VALUES (100, 1) ON CONFLICT DO NOTHING;
INSERT INTO user_roles (user_id, role_id) VALUES (102, 2) ON CONFLICT DO NOTHING;
INSERT INTO user_roles (user_id, role_id) VALUES (103, 3) ON CONFLICT DO NOTHING;
INSERT INTO seller(id, approvalstatus, userid) VALUES (1,0,102) ON CONFLICT DO NOTHING;
insert into  buyer(id, points, city, state, street, zipcode, userid) values(1, 50, 'Fairfield','IA', '100 North', '52557', 103) ON CONFLICT DO NOTHING;

INSERT INTO product (id, description, name, priceperunit, quantity, seller_id) VALUES (10,'description','product name', 100, 1000,1) ON CONFLICT DO NOTHING;
INSERT INTO product (id, description, name, priceperunit, quantity, seller_id) VALUES (11,'description2','product name2', 100, 1000,1) ON CONFLICT DO NOTHING;
INSERT INTO orderline (id, orderstatus, quantity, city, state, street, zipcode, shippingstatus, buyer_id, product_id) VALUES (1,0, 12, 'city name', 'statename', 'street name', 52556, 0,1, 10) ON CONFLICT DO NOTHING;
INSERT INTO orderline (id, orderstatus, quantity, city, state, street, zipcode, shippingstatus, buyer_id, product_id) VALUES (1,1, 10, 'city name2', 'statename2', 'street name2', 52556, 0,1, 11) ON CONFLICT DO NOTHING;

