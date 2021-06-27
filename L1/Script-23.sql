USE Chinook;
LOCK TABLES Customer WRITE ;


INSERT INTO `Customer` (`CustomerId`, `FirstName`, `LastName`, `Company`, `Address`, `City`, `Country`, `PostalCode`, `Phone`, `Email`) 
VALUES (60, N'Jan', N'Kowalski', N'Januszex', N'Kosciuszki 98', N'Warsaw', N'Poland', N'13-200', N'+48 111 222 333', N'januszkowal@gmail.com');

UNLOCK TABLES;