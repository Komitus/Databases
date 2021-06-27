delimiter $$
create function daj_gaze(nazwa_zespolu varchar(90)) returns varchar(80) deterministic
begin
    declare placa int;
    declare koniec smallint unsigned;
    declare kursor cursor for select Muzycy.gaza
    from Muzycy, Zespol
    where Zespol.id = Muzycy.zespol and 
        Zespol.nazwa = nazwa_zespolu;
    declare continue handler for not found
        set koniec = 1;
    open kursor;
    set @wynik = '';
    pętla : loop
        fetch kursor into placa;
        set @wynik = concat(@wynik, placa, ', '); 
        if koniec = 1 then
            leave pętla;
        end if;
    end loop pętla;
    close kursor;
    return @wynik;
end $$
delimiter ;

 

select daj_gaze('AC/DC');

DROP FUNCTION IF EXISTS daj_gaze;
