USE Music;
PREPARE stm1 FROM 'SELECT COUNT (Utwor.id) WYNIK FROM Utwor 
	INNER JOIN Album 
	INNER JOIN Zespol
	ON Utwor.album = Album.tytul AND Album.zespol = Zespol.id 
	WHERE Zespol.nazwa = ? AND Album.gatunek = ?';

SET @band = 'Apocalyptica';
SET @genre = 'Metal';

EXECUTE stm1 USING @band, @genre;

DEALLOCATE PREPARE stm1;