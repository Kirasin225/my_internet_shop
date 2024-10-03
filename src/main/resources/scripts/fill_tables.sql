INSERT INTO customers (customer_id, address, email, first_name, last_name, password, phone_number, role)
VALUES
    (1, 'qweqwe', 'user1@gmail.com', 'Jhon', 'Dou', '{noop}12345', '123 123 1234', 'USER'),
    (2, 'asdasd', 'user2@gmail.com', 'Koli', 'Ko', '{noop}12345', '234 234 2345', 'USER'),
    (3, 'zxczxc', 'user3@gmail.com', 'Doli', 'Fou', '{noop}12345', '345 345 3456', 'USER'),
    (4, 'rtyrty', 'admin1@gmail.com', 'Poli', 'Lou', '{noop}12345', '456 456 4567', 'ADMIN');

INSERT INTO products (product_id, product_name, description, price, quantity)
VALUES
    (1, 'Product 1', 'Description of Product 1', 10.00, 100),
    (2, 'Product 2', 'Description of Product 2', 20.00, 200),
    (3, 'Product 3', 'Description of Product 3', 30.00, 300),
    (4, 'Product 4', 'Description of Product 4', 40.00, 400);

SELECT SETVAL('customers_seq', (SELECT MAX(customer_id) FROM customers));
SELECT SETVAL('products_seq', (SELECT MAX(product_id) FROM products));