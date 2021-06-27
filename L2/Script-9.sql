CREATE VIEW widok AS
	SELECT Zespol.nazwa, Album.tytul, Album.gatunek FROM Zespol INNER JOIN Album ON Album.zespol = Zespol.id GROUP BY Zespol.nazwa;


SELECT * FROM widok;
DROP VIEW widok;