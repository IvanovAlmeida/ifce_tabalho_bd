\c ecommerce
\dt -- lista tabelas

CREATE DATABASE ecommerce
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE IF NOT EXISTS items (
	id INT GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(80) NOT NULL,
	description VARCHAR(200) NOT NULL,
	image VARCHAR(255) NOT NULL,
	price decimal(10,2) NOT NULL,
	active boolean NOT NULL DEFAULT true,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users (
	id INT GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(80) NOT NULL,
	email VARCHAR(80) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	type INT NOT NULL DEFAULT 2,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS address (	
	id INT GENERATED ALWAYS AS IDENTITY,
	street VARCHAR(80) NOT NULL,
	number VARCHAR(20) NOT NULL,
    district VARCHAR(40) NOT NULL,
	city VARCHAR(40) NOT NULL,
	state VARCHAR(20) NOT NULL,
	zipcode VARCHAR(10) NOT NULL,
	user_id int NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS orders (
	id INT GENERATED ALWAYS AS IDENTITY,
	user_id int NOT NULL,
	date timestamptz NOT NULL DEFAULT NOW(),
	total decimal(10, 2) NOT NULL,
	address varchar(255) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS orders_items (	
	order_id int NOT NULL,
	item_id int NOT NULL,
	quantity int NOT NULL,
    itemPrice decimal(10, 2) NOT NULL,
	totalPrice decimal(10, 2) NOT NULL,
	CONSTRAINT fk_order_id FOREIGN KEY(order_id) REFERENCES orders(id),
	CONSTRAINT fk_item_id FOREIGN KEY(item_id) REFERENCES items(id)
);

INSERT INTO users (name, email, type, password)
VALUES 
('admin', 'admin@email.com', 1, '$e0801$eA7vOltmQZDzeGPlDeqTV72BuSyd67fYx25mxMZIX5gJFSkEcIiECgz/6U35a/2y3sCeiKVuo+LLi+x99weB1g==$P2nWTRmYl7zJxg/PeruxOxoywkIuLPzdY47BT/MS3Sc='),
('client', 'client@email.com', 2, '$e0801$eA7vOltmQZDzeGPlDeqTV72BuSyd67fYx25mxMZIX5gJFSkEcIiECgz/6U35a/2y3sCeiKVuo+LLi+x99weB1g==$P2nWTRmYl7zJxg/PeruxOxoywkIuLPzdY47BT/MS3Sc=');

INSERT INTO items (name, description, price, active, image)
VALUES
('Caneca .NET', 'Caneca com a logo do .NET', 35.99, true, 'https://cdn.awsli.com.br/600x450/180/180275/produto/15444468/07c05c2bae.jpg'),
('Camisa .NET', 'Camisa com a logo do .NET', 55.99, true, 'https://cdn.awsli.com.br/600x450/608/608801/produto/31676838/4f52ce0953.jpg'),
('Camisa Python', 'Camisa com a logo do Python', 56.85, false, 'https://cdn.awsli.com.br/600x450/608/608801/produto/28494548/6755b1c35c.jpg'),
('Caneca Python', 'Caneca com a logo do Python', 26.75, true, 'https://cdn.awsli.com.br/1000x1000/608/608801/produto/25546845/36762d31c9.jpg'),
('Caneca coding', 'Caneca generica', 15.85, false, 'https://a-static.mlcdn.com.br/618x463/caneca-coding-presente-criativo-programador-ti-personalizado-minha-caneca/minhacaneca/221c10v/c261161b7b453814467d9110d867cc39.jpg'),
('Canega debugging', 'Camisa debugging', 37.85, true, 'https://m.media-amazon.com/images/I/71HfC5uoJvL._AC_SY450_.jpg');