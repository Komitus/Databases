USE Chinook;
/*ALTER TABLE Invoice
  UPDATE CONSTRAINT FK_InvoiceInvoiceId FOREIGN KEY (InvoiceId) 
      REFERENCES InvoiceLine (InvoiceId) ON DELETE NO ACTION; 
ALTER TABLE InvoiceLine
  UPDATE CONSTRAINT FK_InvoiceLineInvoiceId FOREIGN KEY (InvoiceId) 
      REFERENCES Chinook.Invoice (InvoiceId) ON DELETE CASCADE;*/

DELETE  FROM Invoice WHERE Invoice.InvoiceDate < '2010-01-01';