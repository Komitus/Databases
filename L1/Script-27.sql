DROP TABLE IF EXISTS Tabela;
USE Chinook;
UPDATE Customer,
 ( SELECT  COUNT_FOR_ID.CustomerId AS CustomerId, COUNT_FOR_ID.GenreId AS FavGenre 
	FROM
	(
	
		(
			SELECT  C.CustomerId, COUNT(T.TrackId) Counter, T.GenreId
			FROM ((SELECT TrackId, GenreId FROM Track ) T
				INNER JOIN 
					(SELECT InvoiceLineId, InvoiceId, TrackId FROM InvoiceLine ) IL
					ON T.TrackId = IL.TrackId 
				INNER JOIN (SELECT CustomerId, InvoiceId FROM Invoice ) I
					ON IL.InvoiceId = I.InvoiceId 
				RIGHT JOIN (SELECT CustomerId FROM Customer) C
				ON C.CustomerId = I.CustomerId) GROUP BY T.GenreID, C.CustomerId ORDER BY C.CustomerId DESC	
		) AS COUNT_FOR_ID
		
			INNER JOIN
		
		(
			SELECT TMP.CustomerId, MAX(TMP.Counter) AS MAX_VAL, TMP.GenreId FROM
			(
				SELECT  C.CustomerId, COUNT(T.TrackId) Counter, T.GenreId
				FROM ((SELECT TrackId, GenreId FROM Track ) T
					INNER JOIN 
						(SELECT InvoiceLineId, InvoiceId, TrackId FROM InvoiceLine ) IL
						ON T.TrackId = IL.TrackId 
					INNER JOIN (SELECT CustomerId, InvoiceId FROM Invoice ) I
						ON IL.InvoiceId = I.InvoiceId 
					RIGHT JOIN (SELECT CustomerId FROM Customer) C
					ON C.CustomerId = I.CustomerId) GROUP BY T.GenreID, C.CustomerId ORDER BY C.CustomerId DESC	
			) AS TMP 
			GROUP BY TMP.CustomerId
		) AS MAX_COUNT
			
			ON MAX_COUNT.CustomerId = COUNT_FOR_ID.CustomerId
	)
    WHERE COUNT_FOR_ID.Counter = MAX_COUNT.MAX_VAL
 ) AS FINAL_TAB

SET Customer.FavGenre = FINAL_TAB.FavGenre
WHERE Customer.CustomerId = FINAL_TAB.CustomerId;
  
  
  
  
  
  
  


