USE Chinook;
SELECT Track.Name, Track.UnitPrice 
FROM Track INNER JOIN Genre 
ON Track.GenreId = Genre.GenreId
WHERE Genre.Name ="Sci Fi & Fantasy" OR Genre.Name = "ScienceFiction"; 