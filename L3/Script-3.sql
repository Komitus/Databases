USE Music;
CREATE TABLE Muzycy(

	id INT NOT NULL AUTO_INCREMENT,
	imie varchar(70),
	nazwisko varchar(90),
	zespol INT,
	gaza INT NOT NULL CHECK(gaza > 0),
	PRIMARY KEY (id),
	FOREIGN KEY (zespol) REFERENCES Zespol(id)
);
DELIMITER $$
CREATE PROCEDURE wstawianie()
BEGIN 
set @liczbaZespolow = (SELECT count(*) FROM Zespol);

while @liczbaZespolow > 0 DO
	
	
	set @liczbaCzlonkow  = FLOOR(RAND()*(7+1))+1;
	
	while @liczbaCzlonkow > 0 DO
	
	set @gaza = FLOOR(RAND()*(1500-1+1))+1;
	
	
	INSERT IGNORE INTO Muzycy (imie, nazwisko, zespol, gaza) 
	VALUES (concat('Imie',@liczbaCzlonkow),concat('Nazwisko', @liczbaCzlonkow), @liczbaZespolow, @gaza);
	set @liczbaCzlonkow = @liczbaCzlonkow - 1;
	end while;



set @liczbaZespolow = @liczbaZespolow -1;

end while;

END $$
DELIMITER ;

CALL wstawianie();
DROP PROCEDURE IF EXISTS wstawianie;
