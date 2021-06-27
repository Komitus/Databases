USE Chinook;
SELECT Artist.Name ArtName, Track.Name TrcName
	FROM Track INNER JOIN Album 
	ON Track.AlbumId = Album.AlbumId 
	INNER JOIN Artist 
	ON Artist.ArtistId = Album.ArtistId
	WHERE Artist.Name LIKE "%Santana%"
UNION 
	SELECT Track.Composer ArtName, Track.Name TrcName
	FROM Track WHERE Track.Composer LIKE "%Santana";