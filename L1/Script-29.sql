DELETE FROM Customer WHERE Customer.CustomerId IN (SELECT Customer.CustomerId
FROM Customer 
LEFT JOIN Invoice 
ON Customer.CustomerId = Invoice.CustomerId
WHERE Invoice.InvoiceId IS NULL);