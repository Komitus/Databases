USE Chinook;
UPDATE Customer SET FavGenre = ROUND((25*RAND() + 1), 0)

