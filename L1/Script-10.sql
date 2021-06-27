USE Chinook;
SELECT Artist.Name, COUNT(AlbumId) FROM Artist INNER JOIN Album ON Artist.ArtistId = Album.ArtistId GROUP BY Name;