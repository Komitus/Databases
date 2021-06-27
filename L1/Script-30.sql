USE Chinook;
/*SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE Chinook.Track MODIFY COLUMN TrackId int(11) auto_increment NOT NULL;*/


/*INSERT INTO  Artist (Name) VALUES ('Within Temptation');
  //SET @tmp = SELECT ArtistId FROM Artist WHERE Artsit.Name = 'Within Temptation'; 
INSERT INTO Album (Title, ArtistId) VALUES ('The Unforgiving ', 276);

*/


/*INSERT INTO Track (Name, AlbumId, MediaTypeId, GenreId, Milliseconds, UnitPrice )
VALUES ('Why Not Me', 276, 4, 3,  34000, 0.99);*/

/* tutaj reszta trackow */


INSERT INTO Artist (Name) VALUES ('Pearl Jam') WHERE NOT EXIST (SELECT Artist.Name FROM Artist WHERE Artist.Name = 'Pearl Jam');
INSERT INTO Album (Title, ArtistId) VALUES ('Gigaton', SELECT @TMP = AR.ArtistId FROM Artist AR WHERE AR.Name = 'Pearl Jam')
WHERE NOT EXIST (SELECT * FROM Album WHERE Album.Title = 'Gigaton');

/*
INSERT INTO Track (Name, AlbumId, MediaTypeId, GenreId, Milliseconds, UnitPrice)
VALUES ('Who Ever Said', 277, 4, 3, 311000, 0.99);

*/





