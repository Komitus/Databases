USE Chinook;
SELECT * FROM(
	(SELECT Artist.Name, Album.Title, COUNT(Track.TrackId) Tracks FROM Artist 
	INNER JOIN Album ON Artist.ArtistId = Album.ArtistId
	INNER JOIN Track ON Album.AlbumId = Track.AlbumId
	INNER JOIN Genre ON Track.GenreId = Genre.GenreId
	WHERE Genre.Name = "Metal" OR Genre.Name = "Heavy Metal"
	GROUP BY Artist.Name) TMP
)WHERE TMP.Tracks = (SELECT MAX(TMP2.Tracks) 
	FROM (SELECT Artist.Name, Album.Title, COUNT(Track.TrackId) Tracks FROM Artist 
	INNER JOIN Album ON Artist.ArtistId = Album.ArtistId
	INNER JOIN Track ON Album.AlbumId = Track.AlbumId
	INNER JOIN Genre ON Track.GenreId = Genre.GenreId
	WHERE Genre.Name = "Metal" OR Genre.Name = "Heavy Metal"
	GROUP BY Artist.Name) TMP2);
