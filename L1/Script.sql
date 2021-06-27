USE Chinook;
SELECT tr.Name, mt.Name
FROM Track tr INNER JOIN MediaType mt 
ON tr.GenreId = mt.MediaTypeId AND mt.Name LIKE "%audio file"
WHERE tr.Milliseconds > 250000;