import java.util.ArrayList;

import org.bson.Document;

public interface CreateListOfDocs {

	public static ArrayList<Document> getBands() {
		
		ArrayList<Document> lista = new ArrayList<>();
		Document doc1 = new Document("id", 1).append("nazwa", "Wu-Tang Clan").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "California").append("miasto", "Los Angeles"));
		lista.add(doc1);
		Document doc2 = new Document("id", 2).append("nazwa", "N.W.A").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "California").append("miasto", "Compton"));
		lista.add(doc2);
		Document doc3 = new Document("id", 3).append("nazwa", "Cypress Hill").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "California").append("miasto", "Los Angeles"));
		lista.add(doc3);
		Document doc4 = new Document("id", 4).append("nazwa", "A Tribe Called Quest").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "Nowy Jork").append("miasto", "Nowy Jork"));
		lista.add(doc4);
		Document doc5 = new Document("id", 5).append("nazwa", "Outkast").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "Georgia").append("miasto", "Atlanta"));
		lista.add(doc5);
		Document doc6 = new Document("id", 6).append("nazwa", "Three 6 Mafia").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "Tennessee").append("miasto", "Memphis"));
		lista.add(doc6);
		Document doc7 = new Document("id", 7).append("nazwa", "Public Enemy").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "Nowy Jork").append("miasto", "Nowy Jork"));
		lista.add(doc7);
		Document doc8 = new Document("id", 8).append("nazwa", "Run-D.M.C.").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "Nowy Jork").append("miasto", "Nowy Jork"));
		lista.add(doc8);
		Document doc9 = new Document("id", 9).append("nazwa", "Beastie Boys").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "Nowy Jork").append("miasto", "Nowy Jork"));
		lista.add(doc9);
		Document doc10 = new Document("id", 10).append("nazwa", "De La Soul").append("liczbaAlbumow", 0)
				.append("miejsceZalozenia", new Document("kraj", "USA").append("stan", "Nowy Jork").append("miasto", "Long Island"));
		lista.add(doc10);
		
		return lista;
	}
	
	public static ArrayList<Document> getAlbums(){
		
		ArrayList<Document> lista = new ArrayList<>();
	
		ArrayList<String> tmp = new ArrayList<>();
		tmp.add("Rock");
		tmp.add("Metal");
		//Document doc1 = new Document("tytul", "For Those About To Rock We Salute You").append("gatunek", tmp).append("zespol", 11);
		//lista.add(doc1);
		//Document doc2 = new Document("tytul", "Let There Be Rock").append("gatunek", tmp).append("zespol", 11);
		//lista.add(doc2);
		Document doc1 = new Document("tytul", "Southernplayalisticadillacmuzik").append("gatunek", "Hip Hop").append("zespol", 5);
		lista.add(doc1);
		Document doc2 = new Document("tytul", "Speakerboxxx/The Love Below").append("gatunek", "Hip Hop").append("zespol", 5);
		lista.add(doc2);
		Document doc3 =  new Document("tytul", "Revolverlution").append("gatunek", "Hip Hop").append("zespol", 7);
		lista.add(doc3);
		Document doc4 =  new Document("tytul", "New Whirl Odor").append("gatunek", "Hip Hop").append("zespol", 7);
		lista.add(doc4);
		Document doc5 =  new Document("tytul", "King of Rock").append("gatunek", "Rapcore").append("zespol", 8);
		lista.add(doc5);
		Document doc6 =  new Document("tytul", "Raising Hell").append("gatunek", "Hip Hop").append("zespol", 8);
		lista.add(doc6);
		Document doc7 =  new Document("tytul", "Hello Nasty").append("gatunek", "Rapcore").append("zespol", 9);
		lista.add(doc7);
		Document doc8 =  new Document("tytul", "Check Your Head").append("gatunek", "Rapcore").append("zespol", 9);
		lista.add(doc8);
		Document doc9 =  new Document("tytul", "3 Feet High and Rising").append("gatunek", "Jazz Hop").append("zespol", 10);
		lista.add(doc9);
		Document doc10 =  new Document("tytul", "Days Off EP").append("gatunek", "Hip Hop").append("zespol", 10);
		lista.add(doc10);
		Document doc11 =  new Document("tytul", "Wu-Tang Forever").append("gatunek", "Hardcore Rap").append("zespol", 1);
		lista.add(doc11);
		Document doc12 =  new Document("tytul", "Iron Flag").append("gatunek", "Hardcore Rap").append("zespol", 1);
		lista.add(doc12);
		Document doc13 =  new Document("tytul", "The Love Movement").append("gatunek", "Hip Hop").append("zespol", 4);
		lista.add(doc13);
		Document doc14 =  new Document("tytul", "Beats, Rhymes and Life").append("gatunek", "Hip Hop").append("zespol", 4);
		lista.add(doc14);
		Document doc15 =  new Document("tytul", "Straight Outta Compton").append("gatunek", "Gangsta Rap").append("zespol", 2);
		lista.add(doc15);
		Document doc16 =  new Document("tytul", "Niggaz4Life").append("gatunek", "Gangsta Rap").append("zespol", 2);
		lista.add(doc16);
		Document doc17 =  new Document("tytul", "Cypress Hill").append("gatunek", "Hip Hop").append("zespol", 3);
		lista.add(doc17);
		Document doc18 =  new Document("tytul", "Skul & Bones").append("gatunek", "Hip Hop").append("zespol", 3);
		lista.add(doc18);
		Document doc19 =  new Document("tytul", "Mystic Stylez").append("gatunek", "Gangsta Rap").append("zespol", 6);
		lista.add(doc19);
		Document doc20 =  new Document("tytul", "Last 2 Walk").append("gatunek", "Hip Hop").append("zespol", 6);
		lista.add(doc20);
		
		return lista;
		
	
	}
	
	
	//public static ArrayList<Document> getSongs(){
		
		
		
		
		
		
		
	//}
	
	

	
};
