SELECT RIGHT('254623', 2) into @var;
SET @UserName = CONCAT('Adam', @var);

#CREATE DATABASE Music;
CREATE USER 'Adam23'@'localhost' IDENTIFIED BY 'pass';
GRANT SELECT, DELETE, UPDATE ON Music.* TO 'Adam23'@'localhost';

