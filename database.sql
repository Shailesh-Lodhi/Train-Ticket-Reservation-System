
CREATE DATABASE train_db;
USE train_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE trains (
    train_id INT AUTO_INCREMENT PRIMARY KEY,
    train_name VARCHAR(100),
    source VARCHAR(50),
    destination VARCHAR(50),
    seats INT
);

CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    train_id INT,
    seats_booked INT
);

INSERT INTO trains VALUES (1, 'Rajdhani Express', 'Delhi', 'Mumbai', 50);
