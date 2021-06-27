USE Music;

CREATE TABLE Zespol (
	
	id INT NOT NULL AUTO_INCREMENT,
	nazwa varchar(90),
	liczbaAlbumow INT DEFAULT NULL ,
	PRIMARY KEY (id)
);

CREATE TABLE Album (

	tytul varchar(90),
	gatunek varchar(30),
	zespol INT,
	PRIMARY KEY (tytul, zespol)
	
);

CREATE TABLE Utwor(

	id INT NOT NULL AUTO_INCREMENT,
	tytul varchar(90),
	czas INT NOT NULL CHECK(czas > 0),
	album varchar(90),
	zespol INT,
	PRIMARY KEY (id),
	FOREIGN KEY (album) REFERENCES Album(tytul),
	FOREIGN KEY (zespol) REFERENCES Zespol(id)
);




