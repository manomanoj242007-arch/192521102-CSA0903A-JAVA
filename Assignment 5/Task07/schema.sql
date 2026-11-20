CREATE DATABASE IF NOT EXISTS assignment5;
USE assignment5;
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(80) PRIMARY KEY,
    pwd VARCHAR(120) NOT NULL
);
INSERT INTO users(id, pwd) VALUES ('student1', 'correct-password')
ON DUPLICATE KEY UPDATE pwd = VALUES(pwd);
