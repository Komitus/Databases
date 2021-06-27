DELIMITER $$
CREATE PROCEDURE returnLast(IN t varchar(30), IN k varchar(30))
BEGIN
	
	   IF ((SELECT COUNT(*) from information_schema.columns where TABLE_NAME = t and COLUMN_NAME = k) > 0) THEN
		SET @tmp = CONCAT('SELECT MAX(', k, ') AS maksik FROM ', t);	
		#SELECT @tmp;
		PREPARE stmR FROM @tmp;	
		EXECUTE stmR;
		DEALLOCATE PREPARE stmR;
	ELSE 
		SELECT 'Nie ma takiej kolumny';
	END IF;
	
END; $$
DELIMITER $$

CALL returnLast('dadas', 'czdasda');

DROP PROCEDURE IF EXISTS returnLast;
