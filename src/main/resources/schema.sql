CREATE DATABASE quizdb;
USE quizdb;

CREATE TABLE quiz_question (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               question VARCHAR(255) NOT NULL,
                               option_a VARCHAR(100) NOT NULL,
                               option_b VARCHAR(100) NOT NULL,
                               option_c VARCHAR(100) NOT NULL,
                               option_d VARCHAR(100) NOT NULL,
                               correct_option CHAR(1) NOT NULL
);

CREATE TABLE quiz_result (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             username VARCHAR(100),
                             score INT,
                             total INT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
