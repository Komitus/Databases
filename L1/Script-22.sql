USE Chinook;
SELECT Domena, COUNT(Domena) Counter FROM
	(SELECT SUBSTRING(Email, LOCATE('@', Email) +1, 
			LOCATE('.', Email, Locate('@',Email)) - LOCATE('@', Email)-1) Domena FROM Customer) TMP
	GROUP BY TMP.Domena ORDER BY Counter DESC;
	
		
 

