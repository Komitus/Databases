package user;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
public class OrderHandler {
	
	ShowOrdersController controller;
	TrolleyHandler trolley;
	OrderParameters order;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	@FXML 
	private Label successLabel;
	@FXML
	void showDataPicker(MouseEvent mouseEvent){
		try {
		Parent root = FXMLLoader.load(getClass().getResource("DataPicker.fxml"));
		Scene scene = new Scene(root);
		GUI.getPrimaryStage().setScene(scene);
		GUI.getPrimaryStage().show();
		
		}
		catch(Exception ex) {System.err.println(ex.toString());};
	}
	@FXML
	void getStartDate(ActionEvent event) {
		order = OrderParameters.getInstance();
		order.setStartDate(startDate.getValue());
		//System.out.println(order.getStartDate());
	
	}
	@FXML 
	void getEndDate(ActionEvent event) {
		order = OrderParameters.getInstance();
		order.setEndDate(endDate.getValue());
		//System.out.println(OrderParameters.getEndDate());
		//System.out.println(DBConnector.getIdKlienta());
	}
	@FXML
	void submitDate(MouseEvent mouseEvent) {
		//TODO in if check date 
		trolley = new  TrolleyHandler();
		trolley.createWindow();	
		//else successLabel.setText("Błędna data");
	}
	@FXML
	void showOrders(MouseEvent mouseEvent) {
		controller = new ShowOrdersController();
		controller.createView();
		
	}
	
	
}
