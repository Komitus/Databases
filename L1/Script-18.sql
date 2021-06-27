USE Chinook;
SELECT Album.Title,Track.Name FROM Album INNER JOIN Track ON Album.AlbumId = Track.AlbumId WHERE Album.Title LIKE "Battlestar Galactica%";