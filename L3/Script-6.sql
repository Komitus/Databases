DELIMiTER $$
CREATE PROCEDURE wyplacanie(nazwaZespolu varchar(90), pieniadze int)
BEGIN
	
	DECLARE wyplata int;
	DECLARE koniec smallint unsigned;
	declare kursor cursor for select Muzycy.gaza
	    from Muzycy, Zespol
	    where Zespol.id = Muzycy.zespol and 
	        Zespol.nazwa = nazwaZespolu;
    declare continue handler for not found set koniec = 1;
   set @kasa = pieniadze;
   set @licznik = 0;
    start transaction;
   	savepoint sejwik;
    	open kursor;
    	petla : loop
    		fetch kursor into wyplata;
    		set @kasa = @kasa - wyplata;
    		set @licznik = @licznik + 1;
    		if @kasa < 0 then
    			select 'Za malo hajsu';
    			rollback to savepoint sejwik;
    			RELEASE SAVEPOINT sejwik;
    			leave petla;
    		end if;
    		if koniec = 1 then 	
    			select concat('Udalo sie wyplacic ',@licznik,' osoba. ', 'Pozostalo ', @kasa);
				leave petla;
			end if;
		end loop petla;
	commit;
	#jesli zrobimy commita to sie usuwaja savepointy
	
END $$
DELIMITER ;

CALL wyplacanie('AC/DC', 100);

DROP PROCEDURE IF EXISTS wyplacanie;

