USE Chinook;
SELECT Name, Title FROM Album INNER JOIN Artist ON Album.ArtistId = Artist.ArtistId;