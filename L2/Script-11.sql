#INSERT INTO Zespol (nazwa) VALUES ('testBand');
#SELECT id INTO @id FROM Zespol WHERE Zespol.nazwa = 'testBand';
#INSERT INTO Album (tytul, gatunek, zespol) VALUES ('bandtest', 'Rock', @id);

DELIMITER $$
CREATE PROCEDURE deletePat(IN patt varchar(30))
BEGIN
	SET @pattC = CONCAT('%', patt, '%');
	IF (patt = '') THEN
		SELECT 'Pusty ciag, error';
	ELSE 
		CREATE TEMPORARY TABLE tmp (SELECT z.id FROM Zespol z WHERE z.nazwa LIKE @pattC);
		#SELECT * FROM tmp;
		#SELECT @pattC;
		DELETE FROM Album WHERE Album.zespol IN (SELECT id FROM tmp);
		DROP TEMPORARY TABLE tmp;
	END IF;


END $$
DELIMITER ;

CALL deletePat('tBand');
DROP PROCEDURE IF EXISTS deletePat;




