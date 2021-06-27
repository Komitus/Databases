package user;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderToShowParam {
	
	private int clientId;
	private int orderId;
	private String status;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	
	public OrderToShowParam(int clientId, int orderId, String status, java.sql.Date startDate, java.sql.Date endDate) {
		this.clientId = clientId;
		this.orderId = orderId;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public java.sql.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() { 
	    return String.format("id: "+orderId+" "+status+" od "+startDate+" do "+endDate); 
	} 

}
