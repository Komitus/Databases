DELIMITER $$
CREATE PROCEDURE generateAlbums(IN k INT)
BEGIN 
	SET @firstPart = k;
	#SET @secondPart = k-@firstPart;
	#SELECT @secondPart;
	SELECT COUNT(a.zespol) INTO @artistsAmount FROM Music.Album a;     
	SET @lastArtist = FLOOR(RAND()*@artistsAmount)+1;      #this handles case of 1 album added
	SELECT alb.gatunek INTO @lastGenre FROM Music.Album alb INNER JOIN Music.Zespol zesp ON zesp.id = alb.zespol WHERE zesp.id = @randArtist LIMIT 1; #


	#pierwsza czesc dla floor(k/2) wybiera losowych artystow z istniejacych tabel
  	WHILE @firstPart > 1 DO
    
	  	SET @randArtist = FLOOR(RAND()*@artistsAmount)+1;
	  	SET @randTrack = FLOOR(RAND()*20-5)+6;
	    SELECT CONCAT('Album', @firstPart) INTO @title;
	   	SELECT alb.gatunek INTO @genre FROM Music.Album alb INNER JOIN Music.Zespol zesp ON zesp.id = alb.zespol WHERE zesp.id = @randArtist LIMIT 1;
	    
	    INSERT INTO Music.Album (tytul, gatunek, zespol) VALUES (@title, @genre, @randArtist);
	   	
	   	WHILE @randTrack > 0 DO 
	   	
	   		SELECT CONCAT('Track', @firstPart, '-', @randTrack ) INTO @trackTitle;
	   		SET @randTime = FLOOR(RAND()*700-39)+40;
	   		SET @trackTitle = CONCAT('Title', @firstPart, '-', @randTrack); 
	   		INSERT IGNORE INTO Music.Utwor (tytul, czas, album, zespol) VALUES (@trackTitle, @randTime, @title, @randArtist);
	   		SET @randTrack = @randTrack - 1;
	   	
	  	END WHILE;
	  	
	  	SET @lastArtist = @randArtist;
	  	SET @lastGenre = @genre;
	    SET @firstPart = @firstPart - 1;
	    
  	END WHILE;
	#poza while dla ostatniego artysty dodaje album
	 SET @randTrack2 = FLOOR(RAND()*20-5)+6;
  	 SELECT CONCAT('Album', @firstPart) INTO @title;
  	 INSERT INTO Music.Album (tytul, gatunek, zespol) VALUES (@title, @lastGenre, @lastArtist);
  	
  	 WHILE @randTrack2 > 0 DO 
	   	
	   		SELECT CONCAT('Track', @firstPart, '-', @randTrack2 ) INTO @trackTitle;
	   		SET @randTime = FLOOR(RAND()*700-39)+40;
	   		SET @trackTitle = CONCAT('Title', @firstPart, '-', @randTrack2); 
	   		INSERT IGNORE INTO Music.Utwor (tytul, czas, album, zespol) VALUES (@trackTitle, @randTime, @title, @lastArtist);
	   		SET @randTrack2 = @randTrack2 - 1;
	   	
	 END WHILE;
  	
  
  
 	

	
END $$
DELIMITER ;

CALL generateAlbums(2);

DROP PROCEDURE IF EXISTS generateAlbums;


