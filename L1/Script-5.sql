USE Chinook;
SELECT tr.Name, mt.Name, tr.Milliseconds 
FROM Track tr INNER JOIN MediaType mt
ON tr.MediaTypeId = mt.MediaTypeId AND mt.Name LIKE "%audio file"
WHERE tr.Milliseconds > 250000;