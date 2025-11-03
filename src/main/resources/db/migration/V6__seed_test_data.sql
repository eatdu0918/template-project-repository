-- Seed deterministic test data for user, category (hierarchical), and product

-- Users
INSERT INTO user (id, username, email, password_hash, created_at, updated_at) VALUES
    (1, 'admin', 'admin@example.com', 'password', NOW(), NOW()),
    (2, 'demo', 'demo@example.com', 'password', NOW(), NOW()),
    (3, 'user1', 'user1@example.com', 'password', NOW(), NOW());

-- Categories (roots: parent_id NULL)
INSERT INTO category (id, name, description, parent_id, created_at, updated_at) VALUES
    (100, 'Electronics', 'Electronic devices and accessories', NULL, NOW(), NOW()),
    (200, 'Clothing', 'Apparel and fashion', NULL, NOW(), NOW()),
    (300, 'Home', 'Home and living', NULL, NOW(), NOW()),
    -- Children of Electronics
    (110, 'Laptops', 'Portable computers', 100, NOW(), NOW()),
    (120, 'Smartphones', 'Mobile phones', 100, NOW(), NOW()),
    -- Children of Clothing
    (210, 'Men', 'Mens clothing', 200, NOW(), NOW()),
    (220, 'Women', 'Womens clothing', 200, NOW(), NOW()),
    -- Children of Home
    (310, 'Kitchen', 'Kitchen appliances and tools', 300, NOW(), NOW()),
    (320, 'Furniture', 'Home furniture', 300, NOW(), NOW());

-- Products (referencing category_id)
INSERT INTO product (id, name, description, price, stock, category_id, created_at, updated_at) VALUES
    (1000, 'MacBook Air 13"', 'MacBook Air 13" is a portable computer', 1299.99, 50, 110, NOW(), NOW()),
    (1001, 'ThinkPad X1 Carbon', 'ThinkPad X1 Carbon is a portable computer', 1599.00, 30, 110, NOW(), NOW()),
    (1002, 'iPhone 15', 'iPhone 15 is a mobile phone', 999.00, 100, 120, NOW(), NOW()),
    (1003, 'Galaxy S24', 'Galaxy S24 is a mobile phone', 899.00, 120, 120, NOW(), NOW()),
    (1004, 'Men T-Shirt', 'Men T-Shirt is a t-shirt', 19.99, 200, 210, NOW(), NOW()),
    (1005, 'Women Dress', 'Women Dress is a dress', 59.99, 150, 220, NOW(), NOW()),
    (1006, 'Chef Knife', 'Chef Knife is a knife', 39.90, 80, 310, NOW(), NOW()),
    (1007, 'Office Chair', 'Office Chair is a chair', 149.00, 40, 320, NOW(), NOW());

-- Bump AUTO_INCREMENT to avoid collisions with fixed IDs
-- ALTER TABLE user AUTO_INCREMENT = 10000;
-- ALTER TABLE category AUTO_INCREMENT = 10000;
-- ALTER TABLE product AUTO_INCREMENT = 10000;
