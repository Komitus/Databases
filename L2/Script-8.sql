DELIMITER $$
CREATE PROCEDURE showLongest(IN var varchar(90))
BEGIN 
	
	
	
	IF EXISTS (SELECT  id FROM Zespol WHERE Zespol.nazwa = var)
	THEN
		SELECT id INTO @id FROM Zespol WHERE Zespol.nazwa = var;
		SELECT tytul FROM (
			SELECT Album.tytul, SUM(Utwor.czas) suma FROM Album INNER JOIN Utwor ON Album.tytul = Utwor.album 
				WHERE Album.zespol = @id GROUP BY Utwor.album ORDER BY suma DESC LIMIT 1) AS TMP;
	
	
	ELSE
		SELECT 'Nie ma takiego zespolu';
	END IF;
	
	
END $$
DELIMITER ;

CALL showLongest('AC/DC');
DROP PROCEDURE IF EXISTS showLongest;