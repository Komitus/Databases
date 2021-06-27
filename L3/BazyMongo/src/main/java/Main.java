import java.net.UnknownHostException;
import java.util.Random;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;

public class Main {


	public static MongoClient mongoClient;
	public static MongoDatabase database;
	public static void main(String[] args){
	
		
			
			mongoClient = new MongoClient();
		 	database = mongoClient.getDatabase("MDBMusic");
			MongoCollection<Document> zespol = database.getCollection("zespol");
			MongoCollection<Document> album = database.getCollection("album");
			MongoCollection<Document> utwor = database.getCollection("utwor");
			
			//ZADANIE 10
			//zespol.insertMany(CreateListOfDocs.getBands());
			
			/*MongoCursor<Document> cursor = zespol.find().iterator();
			while (cursor.hasNext()) {
		      Document document = cursor.next();
		      System.out.println(document);
		    }
		    cursor.close();
		    */
		    
		    
		    //ZADANIE 11
		    
		    //album.insertMany(CreateListOfDocs.getAlbums());
		    
		    //Zadanie 12
			/*
			long  countZespol = zespol.countDocuments();
		    System.out.println(countZespol);
		    
		    MongoCursor<Document> cursorForZespol;
		    Document document = new Document();
		    
		    Random rand = new Random();
		    int songid = 1;
		    int n = 600 - 100 + 1;
			
		    for(int i=1; i<=countZespol; i++) {
		    	
		    	Document query = new Document("zespol", new Document("$eq", i));
		    	cursorForZespol = album.find(query).iterator();
		    	while(cursorForZespol.hasNext()) 
		    	{
		    		document = cursorForZespol.next();
		    		String tytul = document.getString("tytul");
		    		int randSongs = rand.nextInt(9)+1;
		    		
		    		for(int j=0; j<randSongs; j++)
		    		{	
		    			int diff = rand.nextInt() % n;
		    			int randomNum =  100 + diff;
		    			int randTime = Math.abs(randomNum);
		    			Document song = new Document("id", songid).append("tytul", "Song" + j).
		    					append("czas", randTime).append("album", tytul).append("zespol", i);
		    			utwor.insertOne(song);
		    			songid++;
		    			
		    		}
		    		
		    	}
		    	
		    	cursorForZespol.close();
		    }
		    */
			
			/*
			ArrayList<Integer> tablica = new ArrayList<>();
			tablica.add(12);
			tablica.add(13);
			long  countUtwor = utwor.countDocuments()+1;
			Document songForTest = new Document("id", countUtwor).append("tytul", "Song" + 1).
					append("czas", 217).append("zespol", tablica);
			utwor.insertOne(songForTest);
			
			*/
			
			
			//Zadanie 13
			/*
			MongoCursor<String> colls = database.listCollectionNames().iterator();

			while(colls.hasNext())
			{
				String name = colls.next();
				System.out.println(name);	
			}
			*/
			//Zadanie 14
			/*
			int  countZespol = (int) zespol.countDocuments();
			
			System.out.println(countZespol);
			for(int i=1; i<=countZespol; i++)
			{
				Document queryBand = new Document("id", i);
				Document queryAlbum = new Document("zespol", i);
				long liczbaAlbumow = album.countDocuments(queryAlbum);
				System.out.println(liczbaAlbumow);
				Document newDocument = new Document("liczbaAlbumow", liczbaAlbumow);
				Document updateObject = new Document("$set", newDocument);
				zespol.updateOne(queryBand, updateObject);
			}
			*/
			//Zadanie 15
			/*
			Document queryGenre = new Document("gatunek", "Rock");
			//Document queryAlbum = new Document("zespol", i);
		
			MongoCursor<Document> cursorForAlbum = album.find(queryGenre).iterator();
			
		
			while(cursorForAlbum.hasNext()) 
	    	{
	    		Document albumTmp = cursorForAlbum.next();
	    		int zespolid = albumTmp.getInteger("zespol");
	    		String albumNazwa = albumTmp.getString("tytul");
	    		Document queryZespol = new Document("id", zespolid);
	    		//FindIterable<Document> cursorForZespol = zespol.find(queryZespol);
	    		System.out.println(zespol.find(queryZespol).first().getString("nazwa"));
	    		Document querySong = new Document("album", albumNazwa);
	    		
	    		MongoCursor<Document> cursorForSong = utwor.find(querySong).iterator();
	    		while(cursorForSong.hasNext())
	    		{
	    			Document song = cursorForSong.next();
	    			System.out.println(song);
	    		}
	    	}

			*/
			
			//ZADANIE 16
		    /*Document neQuery = new Document();
		    neQuery.append("liczbaAlbumow", new Document("$gt", 3));  //kazdy u mnie ma max 2 albumy
		    MongoCursor<Document> cursor = zespol.find(neQuery).iterator();
		    while(cursor.hasNext()) {
		        System.out.println(cursor.next());
		    }
		    */
		    
			//ZADANIE 17
			/*int zespolNum = (int) zespol.countDocuments();
			int max = 0;
			for (int i = 1; i <= zespolNum; i++) { 
			    Document band = new Document();
			    band.append("id", i);
			    long albums = zespol.find(band).first().getLong("liczbaAlbumow");
			    if (max < (int)albums) {
			    	max = (int) albums;
			    }
			}
			
			int numofBands = 0;
			for (int i = 0; i <= max; i++) {
				Document whereQuery = new Document();
			    whereQuery.append("liczbaAlbumow", i);
			    numofBands = (int) zespol.countDocuments(whereQuery);
			    if (numofBands > 3) {
			    	System.out.println(i);
			    	break;
			    }
			}
			*/
			//Zadanie 18
			/*ArrayList<Document> listaNad= new ArrayList<>();
			ArrayList<String> listaTmp = new ArrayList<>();
			listaTmp.add("Rock");
			listaTmp.add("Metal");
			listaNad.add(new Document("gatunek", new Document("$eq", "Rock")));
			listaNad.add(new Document("gatunek", new Document("$eq", "Metal")));
			Document query = new Document().append("$and", listaNad);
					
					//new Document().append("$in", listaTmp));
			MongoCursor<Document> cursor = album.find(query).iterator();
			
			while(cursor.hasNext())
			{
				System.out.println(cursor.next());
				
			}
			
			*/
			//ZAdanie 19
			/*
			Document query = new Document().append("miejsceZalozenia.kraj", "Macedonia");
			zespol.updateMany(query, new Document().append("$set", 
					new Document().append("miejsceZalozenia", new Document("kraj", "Macedonia Polnocna"))));
			MongoCursor<Document> cursor = zespol.find(query).iterator();
			while(cursor.hasNext())
			{
				System.out.println(cursor.next());
				
			}
			
			*/
			
			//Zadanie 20
			/*
			MongoCursor<Document> cursorForAlbum = album.find().iterator();
			int max =0;
			int sumTime = 0;
			String findedAlbum = "";
			while(cursorForAlbum.hasNext())
			{	
				String tytulAlbum = cursorForAlbum.next().getString("tytul");
				Document query = new Document("album", tytulAlbum); 
				MongoCursor<Document> cursorForAlbumsSongs = utwor.find(query).iterator();
				
				while(cursorForAlbumsSongs.hasNext())
				{
					int time = cursorForAlbumsSongs.next().getInteger("czas");
					sumTime = sumTime + time;	
				}
				if(sumTime>max) {
					
					max = sumTime;
					findedAlbum = tytulAlbum;
				}
				
			}
			System.out.println(findedAlbum +"  " + "time: " + String.valueOf(max));
			
			*/
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		    
		    
		    
		    
		    
		    
		    
		    
	}
}
