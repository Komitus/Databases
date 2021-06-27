USE `Chinook`;
SELECT * FROM(SELECT Artist.Name AS Artist_Name, Album.Title, Track.Name AS Track_Name, SUM(Track.UnitPrice) tmp_sum FROM Artist 
INNER JOIN Album ON Artist.ArtistId = Album.ArtistId
INNER JOIN Track ON Album.AlbumId = Track.AlbumId
GROUP BY Album.Title) TMP 
WHERE TMP.tmp_sum = (SELECT MAX(TMP2.tmp_sum) FROM 
(SELECT Artist.Name AS Artist_Name, Album.Title, Track.Name AS Track_Name, SUM(Track.UnitPrice) tmp_sum FROM Artist 
INNER JOIN Album ON Artist.ArtistId = Album.ArtistId
INNER JOIN Track ON Album.AlbumId = Track.AlbumId
GROUP BY Album.Title) TMP2); 
