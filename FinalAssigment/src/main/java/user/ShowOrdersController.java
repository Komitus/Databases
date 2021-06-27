package user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowOrdersController {
	
	DBConnector dbConnector;
	//ArrayList<OrderToShowParam> orders;
	ChoiceBox myChoiceBox;
	VBox myVBox;
	Button myButton;
	Button showButton;
	DatePicker startDate;
	DatePicker endDate;
	Scene scene;
	ListView<OrderToShowParam> listOfOrders;
	Label myErrorLabel;
	ShowOrdersController(){}
	
	public void createView() {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ShowOrders.fxml"));
			scene = new Scene(root);
			}
			catch(Exception ex) {System.err.println(ex.toString());};
			//orders = new ArrayList<OrderToShowParam>();
			listOfOrders = new ListView<OrderToShowParam>();
			myVBox = (VBox)scene.lookup("#myVBox");
			showButton = (Button)scene.lookup("#pokaz");
			showButton.setOnMouseClicked(e->showDetails());
			
			myButton = (Button)scene.lookup("#myButton");
			myButton.setOnMouseClicked(e->submit());
			myErrorLabel = (Label)scene.lookup("#myErrorLabel");
			startDate = (DatePicker)scene.lookup("#startDate");
			endDate = (DatePicker)scene.lookup("#endDate");
			myChoiceBox = (ChoiceBox)scene.lookup("#myChoiceBox");
			myChoiceBox.getItems().addAll("wszystkie","oczekujace","zrealizowane", "zakonczone");
			myChoiceBox.setValue("wszystkie");
			listOfOrders.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			myVBox.getChildren().add(listOfOrders);
			startDate.setValue(LocalDate.now());
			endDate.setValue(LocalDate.now());
		
			GUI.getPrimaryStage().setScene(scene);
			GUI.getPrimaryStage().show();
	}
	
	private void submit() {
		
		listOfOrders.getItems().clear();
		makeConnection();
		String status = (String)myChoiceBox.getSelectionModel().getSelectedItem();
		try {
			CallableStatement stm = dbConnector.getConnection().prepareCall("{CALL showOrders(?,?,?,?) }");
        	stm.setInt(1, dbConnector.getIdKlienta());
        	stm.setString(2,status);
        	stm.setDate(3, Date.valueOf(startDate.getValue()));
        	stm.setDate(4, Date.valueOf(endDate.getValue()));
        	ResultSet rs = stm.executeQuery();	
        	while(rs.next()) {
        		
        		OrderToShowParam tmpOrder = 
        			new OrderToShowParam(rs.getInt("idKlienta"), 
        					rs.getInt("id"), rs.getString("status"), rs.getDate("odKiedy"), rs.getDate("doKiedy"));
        		listOfOrders.getItems().add(tmpOrder);
        		
        	}
        	listOfOrders.refresh();
		}
		catch(SQLException ex){
			myErrorLabel.setText("Blad mysql \n usuwanie z koszyka");
		}
	}
	private void showDetails() {
		int index = listOfOrders.getSelectionModel().getSelectedIndex();
		VBox orderList = new VBox();
		ListView list = new ListView<String>();
		int tmpId = listOfOrders.getItems().get(index).getOrderId();
		String query = "{CALL selectComponents("+tmpId+") }";
		makeConnection();
		CallableStatement stm;
		try {
			stm = dbConnector.getConnection().prepareCall(query);
			ResultSet rs = stm.executeQuery();
	    	while (rs.next()) {
	    		System.out.println("wchodze do petelki");
	    		String str = "id: "+rs.getInt("id")+" | marka: "+rs.getString("marka")+" | typ: "+rs.getString("typ")+" | rozmiar: "+rs.getInt("rozmiar");
	    		list.getItems().add(str);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        orderList.setPadding(new Insets(20,20,20,20));
        orderList.getChildren().add(list);
        scene = new Scene(orderList, 625, 625);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Szczeg�y");
        stage.show();
		
	}
	
	private boolean makeConnection() {
		 boolean connectedToSql = false;
		 dbConnector = DBConnector.getInstance();
		 try {
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_company?noAccessToProcedureBodies=true", "user", "password");
		        dbConnector.setConnection(connection);
		        connectedToSql = true;
		 } 
		 catch (SQLException ex) {
			 //myErrorLabel.setText("Blad logowania do mysql");
		 }
		 return connectedToSql;
	 }
}
