package admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import user.DBConnector;

public class AddItemWindow {

	 DBConnector dbConnector;
	 @FXML
	 private TextField brandField;
	 @FXML
	 private TextField typeField;
	 @FXML
	 private TextField sizeField;
	
	 @FXML
	 private Label errorRegisterLabel;
	 
	 @FXML
	 private void handleAddItem(MouseEvent mouseEvent) {
	    String brand = brandField.getText();
	    String type = typeField.getText();
	    String size = sizeField.getText();
	    int realSize = Integer.parseInt(size);
	    String result = "failure";
	    errorRegisterLabel.setText("");		
	    	
		if (makeConnection()) {
			try {
	            	
				CallableStatement stm = dbConnector.getConnection().prepareCall("{CALL addItem(?,?,?) }");
	            stm.setString(1, brand);
	            stm.setString(2, type);
	            stm.setInt(3, realSize);
	            ResultSet rs = stm.executeQuery();
	            if(rs.next()) 
	            	result = rs.getString("resultat");
	            
	            if(result.equals("success"))
	            	errorRegisterLabel.setText("Udalo sie dodac");
	            else 
	            	errorRegisterLabel.setText("Nieprawid³owe dane");
	            stm.close();  
	            	
	        } catch (SQLException ex) {
	        	System.out.println(ex.toString());
	            errorRegisterLabel.setText("Blad sql");
	        }
		}
	    
	 }
	 
	 private boolean makeConnection() {
		 boolean connectedToSql = false;
		 dbConnector = DBConnector.getInstance();
		 try {
		        Connection connection = DriverManager.getConnection(
		        		"jdbc:mysql://localhost:3306/rental_company?noAccessToProcedureBodies=true", 
		        		"admin", "password");
		        dbConnector.setConnection(connection);
		        connectedToSql = true;
		 } 
		 catch (SQLException ex) {
		        errorRegisterLabel.setText("Blad logowania do mysql");
		 }
		 return connectedToSql;
	 }
	 public boolean numberCorrectness(String number) {
		    Pattern pattern = Pattern.compile("^\\d{9}$");
		    Matcher matcher = pattern.matcher(number);
		    return matcher.matches();
	 }
}
