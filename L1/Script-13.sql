USE Chinook;
SELECT Employee.LastName, Employee.Firstname FROM Customer RIGHT JOIN Employee 
ON Customer.SupportRepId = Employee.EmployeeId WHERE Customer.SupportRepId IS NULL;