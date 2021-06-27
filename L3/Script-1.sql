USE Music;
DELIMITER $$
CREATE PROCEDURE procedura(IN tab varchar(20), IN nrow varchar(150))
BEGIN

	IF tab = 'Album' AND nrow LIKE '%|%|%' THEN
		set @tytul = (select SUBSTRING_INDEX(nrow, '|', 1));
		set @gatunek = (select SUBSTRING_INDEX(SUBSTRING_INDEX(nrow, '|', 2),'|',-1));
		set @zespol = (select SUBSTRING_INDEX(nrow, '|', -1));
		if(select count(*) from Zespol where id = @zespol) > 0 THEN 
			set @s1 = 'insert into Album values(?, ?, ?)';
			prepare stmt from @s1;
			execute stmt using @tytul, @gatunek, @zespol;
			deallocate prepare stmt;
		else 
			select 'Zle dane';
		end if;

	ELSE IF tab = 'Utwor'  AND nrow LIKE '%|%|%|%' THEN 
		set @tytul = (select SUBSTRING_INDEX(nrow, '|', 1));
		set @czas = (select SUBSTRING_INDEX(SUBSTRING_INDEX(nrow, '|', 2),'|', -1));
		set @album = (select SUBSTRING_INDEX(SUBSTRING_INDEX(nrow, '|', -2),'|', 1));
		set @zespol = (select SUBSTRING_INDEX(nrow, '|', -1));
		#SELECT @tytul,@czas,@album,@zespol;
		if (select count(*) from Zespol where id = @zespol) > 0 AND (select count(*) from Album where tytul = @album) > 0 THEN
			set @s2 = 'insert into Utwor (tytul, czas, album, zespol) values(?, ?, ?, ?)';
			prepare stmt from @s2;
			execute stmt using @tytul, @czas, @album, @zespol;
			deallocate prepare stmt;
		else 
			select 'Zle dane';
		end if;
	
	ELSE IF tab = 'ZespolTMP'  AND nrow LIKE '%|%' THEN 
		set @nazwa = (select SUBSTRING_INDEX(nrow, '|', 1));
	    set @liczba = (select SUBSTRING_INDEX(nrow, '|', -1));
	    set @s3 = 'insert into Zespol (nazwa, liczbaAlbumow) values(?, ?)';
			prepare stmt from @s3;
			execute stmt using @nazwa, @liczba;
			deallocate prepare stmt;
	
	
	END IF;
	END IF;
	END IF;

END; $$
DELIMITER ;

#dla Albumu
#CALL procedura('Album', 'Album|Rock|1'); 
#dla Utworu
#CALL procedura('Utwor', 'Elo|300|For Those About To Rock We Salute You|1');   

#dla Zespolu
CREATE TEMPORARY TABLE ZespolTMP SELECT * FROM Zespol;
SELECT * FROM ZespolTMP;
CALL procedura('ZespolTMP', ''AAA',0); DROP TABLE ZespolTMP--|0');

DROP PROCEDURE IF EXISTS procedura;