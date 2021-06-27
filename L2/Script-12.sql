SHOW indexes FROM Album;

ALTER TABLE Album ADD INDEX indx (gatunek);  #defaultowo B-Tree no i to najlepiej tu bo najszybciej 

   # jak szukamy albumu where gatunek like 'Roc%' to użyje

   # jak szukamy albumu where gatunek like '%tal' to nie użyje   Stringi Btree porownuje alfabetycznie 
