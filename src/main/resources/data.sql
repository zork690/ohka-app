-- Categories
INSERT INTO category (id, name) VALUES (100, 'Electronics');
INSERT INTO category (id, name) VALUES (101, 'Clothing');

-- Users
INSERT INTO users (id, username, password) VALUES (200, 'admin', '$2a$10$VzAEmLY.RvIk0YhNdEa2/.CJHRa86x/iuHwK6ub8KQ5B883dDP4jW');
INSERT INTO users (id, username, password) VALUES (201, 'user', '$2a$10$2XF5Vvtn1cDnURounE/oau4Mc14YP6mbzQJZXl3xHcywxRmpQ9CaG');

-- Roles
INSERT INTO user_roles (user_id, role) VALUES (200, 'ADMIN');
INSERT INTO user_roles (user_id, role) VALUES (201, 'USER');

-- Products
INSERT INTO product (name, description, price, category_id) VALUES ('Smartphone', 'Latest model', 799.99, 100);
INSERT INTO product (name, description, price, category_id) VALUES ('T-Shirt', 'Cotton, size M', 19.99, 101);