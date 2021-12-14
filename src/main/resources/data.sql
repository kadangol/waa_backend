-- Inserting User data
-- INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
-- INSERT INTO roles (id, name) VALUES (2, 'ROLE_SELLER');
-- INSERT INTO roles (id, name) VALUES (3, 'ROLE_BUYER');
-- INSERT INTO users (id, email, password, username) VALUES (1, 'admin@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'admin');
-- INSERT INTO users (id, email, password, username) VALUES (2, 'seller@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'seller');
-- INSERT INTO users (id, email, password, username) VALUES (3, 'buyer@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'buyer');
-- INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
-- INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
-- INSERT INTO user_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_SELLER');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_BUYER');
INSERT INTO users (id, email, password, username, FULLNAME) VALUES (100, 'admin@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'admin', 'admin');
INSERT INTO users (id, email, password, username, FULLNAME) VALUES (102, 'seller@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'seller', 'admin');
INSERT INTO users (id, email, password, username, FULLNAME) VALUES (103, 'buyer@miu.edu', '$2a$10$cED/Z/W4Z0dhKy.gYqhLleWYYXDZZ/1NXtnIL.tFJsdsAIi6BAstm', 'buyer', 'admin');
INSERT INTO user_roles (user_id, role_id) VALUES (100, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (102, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (103, 3);
-- SELECT * from buyer;
--End of inserting User data
