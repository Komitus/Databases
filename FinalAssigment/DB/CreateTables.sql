-- drop database rental_company;
create database if not exists rental_company character set utf8 collate utf8_unicode_ci;

use rental_company;

create table if not exists pracownicy (
	id int not null auto_increment,
	login varchar(90) not null,
	haslo varchar(90) not null,
	imie varchar(90) not null,
	nazwisko varchar(90) not null,
	primary key(id)
);

create table if not exists klienci (
	id int not null auto_increment,
	login varchar(90) not null,
	haslo varchar(90) not null,
	imie varchar(90) not null,
	nazwisko varchar(90) not null,	
	phoneNumb CHAR(9) not null,
	primary key(id)
); 
create table if not exists menadzerowie (
	id int not null auto_increment,
	login varchar(90) not null,
	haslo varchar(90) not null,
	imie varchar(90) not null,
	nazwisko varchar(90) not null,
	primary key(id)
);
create table if not exists towar (
	id int not null auto_increment,
	marka varchar(90) not null,
	typ ENUM('kask', 'narty', 'snowboard', 'buty'),
	rozmiar int not null,
	doKiedy DATE,
	odKiedy Date,
	primary key(id)
);

create table if not exists zamowienia (
	id int not null auto_increment,
	status ENUM('oczekujace', 'zarezerowane', 'odebrane', 'zakonczone'),
	idKlienta int not null,
	idPracownika int,
	naKiedy DATE,
	primary key(id),
	foreign key(idklienta) references klienci(id),
	foreign key(idPracownika) references pracownicy(id)
);

create table if not exists skladniki_zamowien (
	idTowaru int not null,
	idZamowienia int not null,
	foreign key(idtowaru) references towar(id),
	foreign key(idZamowienia) references zamowienia(id)
);


 
