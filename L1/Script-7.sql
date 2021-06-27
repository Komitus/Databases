USE Chinook;
SELECT Track.Name, Album.Title, Track.Composer, Genre.Name FROM Playlist INNER JOIN PlaylistTrack ON Playlist.PlaylistId = PlaylistTrack.PlaylistId
INNER JOIN Track ON Track.TrackId = PlaylistTrack.TrackId 
INNER JOIN Genre ON Track.GenreId = Genre.GenreId 
INNER JOIN Album ON Album.AlbumId = Track.AlbumId 
WHERE Playlist.Name = "90’s Music";  