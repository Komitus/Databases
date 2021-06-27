USE Music;

DELIMITER $$
CREATE PROCEDURE procedura(IN agg varchar(15), IN rel varchar(4))
BEGIN
	set @s = 'select tytul, czas 
		from Utwor 
		where(
			case concat(?,?)
			when "min<" then czas < (select min(czas) from Utwor)
			when "min<=" then czas <= (select min(czas) from Utwor)
			when "min>" then czas > (select min(czas) from Utwor)
			when "min>=" then czas >= (select min(czas) from Utwor)
			when "min=" then czas = (select min(czas) from Utwor)

			when "max<" then czas < (select max(czas) from Utwor)
			when "max<=" then czas <= (select max(czas) from Utwor)
			when "max>" then czas > (select max(czas) from Utwor)
			when "max>=" then czas >= (select max(czas) from Utwor)
			when "max=" then czas = (select max(czas) from Utwor)

			when "avg<" then czas < (select avg(czas) from Utwor)
			when "avg<=" then czas <= (select avg(czas) from Utwor)
			when "avg>" then czas > (select avg(czas) from Utwor)
			when "avg>=" then czas >= (select avg(czas) from Utwor)
			when "avg=" then czas = (select avg(czas) from Utwor)
			
			when "stddev" then czas BETWEEN (SELECT avg(czas) - 2*STDDEV(czas) FROM Utwor) AND (SELECT avg(czas) + 2*stddev(czas) FROM Utwor)
		end)';
	prepare stmt from @s;
	execute stmt using agg, rel;
    deallocate prepare stmt;
	
	
END; $$
DELIMITER ;

CALL procedura('stddev', '');

DROP PROCEDURE IF EXISTS procedura;