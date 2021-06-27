package worker;
import worker.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.List;




public class MainWindow {
	
	private Scene scene;
	private DBConnector dbConnector;
    private VBox orderList;
    private ListView<String> list; 
    private int currId = 0;
    

	public MainWindow() {
    	try {
    		dbConnector = DBConnector.getInstance();
			createWindow();
			
		 }
			catch(Exception ex) {System.err.println(ex.toString());};
	}


	
	private void createWindow() throws Exception {
		orderList = new VBox();
		list = new ListView<String>();
		CallableStatement stm = dbConnector.getConnection().prepareCall("{CALL selectOrders() }");
    	ResultSet rs = stm.executeQuery();
    	while (rs.next()) {
    		String str = "id: "+rs.getInt("id")+" | status: "+rs.getString("status")+" | imie: "+rs.getString("imie")+" | nazwisko: "+rs.getString("nazwisko");
    		list.getItems().add(str);
    	}
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        orderList.setPadding(new Insets(20,20,20,20));
        orderList.getChildren().add(list);
        
        Button detail = new Button("Details");
        detail.setOnAction(e->showDetails());
        detail.setPrefSize(600, 20);
        orderList.getChildren().add(detail);
        
        Button confirm = new Button("Confirm");
        confirm.setOnAction(e->confirmOrder());
        confirm.setPrefSize(600, 20);
        orderList.getChildren().add(confirm);
        
        Button issue = new Button("Issue");
        issue.setOnAction(e->issueOrder());
        issue.setPrefSize(600, 20);
        orderList.getChildren().add(issue);
        
        Button finalize = new Button("Finalize");
        finalize.setOnAction(e->finalizeOrder());
        finalize.setPrefSize(600, 20);
        orderList.getChildren().add(finalize);
        
        TextField tf = new TextField();
        
        
        scene = new Scene(orderList, 625, 625);
        GUI.getPrimaryStage().setTitle("Pracownik");
        GUI.getPrimaryStage().setScene(scene);


	}

	private void finalizeOrder() {
		String message = list.getSelectionModel().getSelectedItem();
		setValues(message);
		try {
			CallableStatement stm = dbConnector.getConnection().prepareCall("{ CALL changeStatusToFinilized(" + currId + ")}");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				MainWindow mainWindow = new MainWindow();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private void issueOrder() {
		String message = list.getSelectionModel().getSelectedItem();
		setValues(message);
		try {
			CallableStatement stm = dbConnector.getConnection().prepareCall("{ CALL changeStatusToIssued(" + currId + ")}");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				MainWindow mainWindow = new MainWindow();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private void confirmOrder() {
		String message = list.getSelectionModel().getSelectedItem();
		setValues(message);
		try {
			CallableStatement stm = dbConnector.getConnection().prepareCall("{ CALL changeStatusToReserved(" + currId + ")}");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				MainWindow mainWindow = new MainWindow();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private void showDetails() {
		String message = list.getSelectionModel().getSelectedItem();
		setValues(message);
		DetailWindow detailWindow = new DetailWindow(currId);
	}
	
	private void setValues(String message) {
		String[] splitStr = message.split("\\s+");
		try {
			currId = Integer.parseInt(splitStr[1]);
		} catch (Exception e) {}
		
	}

	
}
