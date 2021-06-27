DELIMITER $$
CREATE PROCEDURE procedura(IN idpoj INT)
BEGIN
	SELECT Zawodnik1Id INTO @id1 FROM Pojedynek;
	SELECT Zawodnik2Id INTO @id1 FROM Pojedynek;
	SELECT Punkty INTO @wynik1 FROM Wynik WHERE Wynik.ZawodnikId = @id1 AND Wynik.PojedynekId = idpoj;
	SELECT Punkty INTO @wynik2 FROM Wynik WHERE Wynik.ZawodnikId = @id2 AND Wynik.PojedynekId = idpoj;;
	
	IF(@wynik1 > @wynik2) THEN
	INSERT INTO Pojedynek VALUES SELECT Imie AS ImieZwyciezczy,Nazwisko AS NazwiskoZwyciezcy FROM Zawodnicy WHERE ZawodnikId =@id1;
	ELSE
	INSERT INTO Pojedynek VALUES SELECT Imie AS ImieZwyciezczy,Nazwisko AS NazwiskoZwyciezcy FROM Zawodnicy WHERE ZawodnikId =@id2;
	END IF
END; $$
DELIMITER ;