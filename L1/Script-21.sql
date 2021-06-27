SELECT TMP1.Name, TMP1.Average, TMP2.Counter
	FROM
	(SELECT Artist.Name, AVG(Track.Milliseconds) Average FROM Track
	INNER JOIN Album ON Track.AlbumId = Album.AlbumId 
	INNER JOIN Artist ON Artist.ArtistId = Album.AlbumId 
	INNER JOIN Genre ON Track.GenreId = Genre.GenreId 
	GROUP BY Artist.Name) TMP1
INNER JOIN 
	(SELECT Artist.Name, COUNT(Track.TrackId) Counter FROM (Track
	INNER JOIN Album ON Track.AlbumId = Album.AlbumId 
	INNER JOIN Artist ON Artist.ArtistId = Album.AlbumId 
	INNER JOIN Genre ON Track.GenreId = Genre.GenreId)
	WHERE Genre.Name = "Rock"
	GROUP BY Artist.Name) TMP2
ON TMP1.Name = TMP2.Name AND TMP2.Counter > 12 ORDER BY TMP1.Average DESC;