/*USE Music;
PREPARE stm2 FROM 'SELECT Utwor.tytul, czas FROM Utwor 
	INNER JOIN Album 
	ON Utwor.album = Album.tytul
	WHERE Album.gatunek = ? AND Utwor.czas < ? ORDER BY Utwor.czas DESC LIMIT 1';  
	
SET @genre = 'Rock';
SET @tresh = 300;

EXECUTE stm2 USING @genre, @tresh;

DEALLOCATE PREPARE stm2;
*/

#wersja z MAX()
PREPARE stm3 FROM 'SELECT Utwor.tytul, czas FROM Utwor 
	INNER JOIN Album 
	ON Utwor.album = Album.tytul
	WHERE Album.gatunek = ? AND Utwor.czas < ?
		AND Utwor.czas = (SELECT MAX(czas)
		FROM Utwor 
		INNER JOIN Album 
		ON Utwor.album = Album.tytul
		WHERE Album.gatunek = ? AND Utwor.czas < ?)';
	
SET @genre = 'Rock';
SET @tresh = 300;

EXECUTE stm3 USING @genre, @tresh, @genre, @tresh;
DEALLOCATE PREPARE stm3;
