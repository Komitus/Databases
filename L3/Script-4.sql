#ALTER TABLE Muzycy ADD hasla binary; 
DELIMITER $$
CREATE PROCEDURE hasla(IN imie varchar(70), IN pass varchar(10))
BEGIN
	SET @hash = SHA1(pass);
	#SELECT @hash;
	IF (SELECT count(id) FROM (SELECT id FROM Muzycy where @hash = Muzycy.haslo AND Muzycy.imie = imie) AS tmp)> 0 THEN
	SELECT Zespol.nazwa FROM Muzycy INNER JOIN Zespol ON Muzycy.zespol = Zespol.id WHERE Muzycy.haslo = @hash AND Muzycy.imie = imie; 
	ELSE 
	SET @amount = (SELECT count(*) FROM Zespol);
	SET @randZespol = FLOOR(RAND()*@amount-1+1)+1;
	SELECT Zespol.nazwa FROM Zespol WHERE Zespol.id = @randZespol;
	
	END IF;
	
	
	
END $$
DELIMITER ;

#UPDATE Muzycy SET haslo = SHA1('test') WHERE imie = 'Imie1';
CALL hasla('Imie2', 'dadas');\
CALL hasla('Imie1', 'test'); # dalem wszysytkim o imieniu Imie1 haslo test czyli powinno wyspisać wszytskie zespoly

DROP PROCEDURE IF EXISTS hasla;