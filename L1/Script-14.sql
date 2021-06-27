USE Chinook;
SELECT COUNT(Employee.EmployeeId), Employee.LastName, Employee .FirstName FROM Customer RIGHT JOIN Employee
ON Customer.SupportRepId = Employee.EmployeeId GROUP BY Employee.EmployeeId 
EXCEPT
SELECT * FROM (SELECT COUNT(Employee.EmployeeId) ct, Employee.LastName, Employee .FirstName FROM Customer 
RIGHT JOIN Employee ON Customer.SupportRepId = Employee.EmployeeId WHERE Customer.City = Employee.City  ORDER BY Employee.EmployeeId) tmp WHERE tmp.ct > 0;






