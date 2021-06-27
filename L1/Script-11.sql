USE Chinook;
SELECT MAX(SUM_CITY) mx, TMP.BillingCity, TMP.BillingCountry
FROM (SELECT SUM(Total) SUM_CITY, BillingCity, BillingCountry FROM Invoice GROUP BY BillingCity) TMP
GROUP BY BillingCountry ORDER BY mx DESC;