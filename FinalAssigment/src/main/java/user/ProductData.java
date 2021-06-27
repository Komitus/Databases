package user;
import java.sql.Date;
public class ProductData {
	
	private int id;
	private String typ;
	private int rozmiar;
	
	public ProductData(int rozmiar, String typ, int id) {
		this.rozmiar = rozmiar;
		this.typ = typ;
		this.id = id;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public int getRozmiar() {
		return rozmiar;
	}
	public void setRozmiar(int rozmiar) {
		this.rozmiar = rozmiar;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
