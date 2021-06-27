DELIMITER $$
CREATE TRIGGER zmianyLiczbaAlbIns AFTER INSERT ON Album
FOR EACH ROW 
BEGIN 
	UPDATE Zespol 
	SET liczbaAlbumow  = liczbaAlbumow + 1
	WHERE Zespol.id = NEW.zespol;
	
END; $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER zmianyLiczbaAlbDel BEFORE DELETE ON Album 
FOR EACH ROW 
BEGIN 
	UPDATE Zespol 
	SET liczbaAlbumow  = liczbaAlbumow - 1
	WHERE Zespol.id = OLD.zespol;

	#zadanie 10
	DELETE FROM Utwor WHERE OLD.tytul = Utwor.album;
	
	
END; $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER zmianyLiczbaAlbUpdate AFTER UPDATE ON Album 
FOR EACH ROW 
BEGIN 
	CALL licznikAlbumow ();
END; $$
DELIMITER ;

show triggers;
#DROP TRIGGER zmianyLiczbaAlbIns;
#DROP TRIGGER zmianyLiczbaAlbUpdate;
#DROP TRIGGER zmianyLiczbaAlbDel;

#INSERT INTO Album (tytul, gatunek, zespol) VALUES ('dadasda', 'rock', 50);
#DELETE FROM Album WHERE tytul = 'dadasda';


