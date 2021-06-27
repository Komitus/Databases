DROP PROCEDURE IF EXISTS genres;
DELIMITER $$
CREATE PROCEDURE genres()
BEGIN 
	
	SELECT Name INTO @var FROM Chinook.Genre LIMIT 1;
	UPDATE Album
	SET gatunek  = @var
	WHERE gatunek NOT IN (SELECT Name FROM Chinook.Genre);

	
END $$
DELIMITER ;

CALL genres();

