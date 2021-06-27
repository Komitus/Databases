DROP VIEW IF EXISTS utwor120;
CREATE VIEW utwor120 AS
SELECT Zespol.nazwa, COUNT(Utwor.id) liczbaTrackow FROM Utwor INNER JOIN Zespol ON Utwor.zespol = Zespol.id WHERE czas > 120 GROUP BY zespol;
	
SELECT * FROM utwor120;