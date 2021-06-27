DELIMITER $$
CREATE PROCEDURE licznikAlbumow ()
BEGIN 
	
	CREATE TEMPORARY TABLE TMP(SELECT COUNT(tytul) liczba, zespol FROM Album GROUP BY (zespol));
		#SELECT * FROM TMP;
		UPDATE Zespol INNER JOIN TMP
		ON Zespol.id = TMP.zespol
		SET Zespol.liczbaAlbumow = TMP.liczba;
		
	
END $$
DELIMITER ;

CALL licznikAlbumow ();

DROP TABLE IF EXISTS TMP;
#DROP PROCEDURE IF EXISTS licznikAlbumow;