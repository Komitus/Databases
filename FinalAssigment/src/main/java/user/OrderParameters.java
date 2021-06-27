package user;

import java.util.ArrayList;
import java.time.LocalDate;
public class OrderParameters {
	
	private static OrderParameters instance;
	public  static ArrayList<ProductData> trolley;
	private  static LocalDate startDate;
	private static LocalDate  endDate;

	public OrderParameters() {
		trolley = new ArrayList<ProductData>();
	}
	
	public static OrderParameters getInstance(){
		if (instance == null) {
            instance = new OrderParameters();
        }
        return instance;
	}
	public LocalDate getStartDate() {
		
		return startDate;
	}
	public void setStartDate(LocalDate data) {
		OrderParameters.startDate = data;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate  endDate) {
		OrderParameters.endDate = endDate;
	}
	
}
