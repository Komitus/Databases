
/* Zespol */
/*
CREATE TEMPORARY TABLE Update_Zespol(

	SELECT Artist.Name
	FROM Chinook.Artist



);

INSERT INTO Zespol(nazwa) SELECT Name FROM Update_Zespol;
#DROP TABLE Update_Zespol;

/* Album */

/*
CREATE TEMPORARY TABLE Update_Album(

	SELECT alb.Title, g.Name gatunek, z.id


	FROM Chinook.Album alb , Chinook.Genre g , Chinook.Track t , Chinook.Artist art, Music.Zespol z
	WHERE t.AlbumId = alb.AlbumId AND
	g.GenreId = t.GenreId AND
	art.ArtistId = alb.ArtistId AND 
	t.MediaTypeId <> 3 AND 
	art.Name = z.nazwa 
	GROUP BY alb.AlbumId 

);


#INSERT IGNORE INTO Album (tytul, gatunek, zespol) SELECT Title, gatunek, id FROM Update_Album;
*/
#DROP TABLE Update_Album;

CREATE TEMPORARY TABLE Update_Utwor(

	SELECT t.Name, FLOOR(t.Milliseconds/1000) czas, alb.tytul, alb.zespol 
	FROM Chinook.Track t, Album alb, Chinook.Album a
	WHERE a.AlbumId = t.AlbumId AND 
	alb.tytul = a.Title

);


INSERT IGNORE INTO Utwor (tytul, czas, album, zespol) SELECT Name, czas, tytul, zespol FROM Update_Utwor;

#DROP TABLE Update_Utwor;






