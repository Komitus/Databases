create CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
#User privilages
GRANT EXECUTE ON PROCEDURE `rental_company`.loginKlient TO 'user'@'localhost';
GRANT EXECUTE ON PROCEDURE `rental_company`.addKlient TO 'user'@'localhost';
GRANT EXECUTE ON PROCEDURE `rental_company`.ifAvailable TO 'user'@'localhost';
GRANT EXECUTE ON PROCEDURE `rental_company`.dodajSkladniki TO 'user'@'localhost';
