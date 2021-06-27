USE Chinook;
SELECT TMP1.Name, TMP2.Name, TMP2.Title FROM 
	(SELECT Al1.AlbumId, Artist.Name, Al1.Title
	FROM Artist 
	INNER JOIN Album Al1 ON Artist.ArtistId = Al1.ArtistId) TMP1
JOIN 
	(SELECT Al2.AlbumId, Artist.Name, Al2.Title
	FROM Artist 
	INNER JOIN Album Al2 ON Artist.ArtistId = Al2.ArtistId) TMP2
ON TMP1.Title = TMP2.Title
WHERE TMP2.AlbumId < TMP1.AlbumId;
	