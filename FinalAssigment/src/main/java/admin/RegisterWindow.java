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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import user.DBConnector;

public class RegisterWindow {
	
	 DBConnector dbConnector;
	 @FXML
	 private TextField nameField;
	 @FXML
	 private TextField lastNameField;
	 @FXML
	 private TextField loginField;
	 @FXML
	 private PasswordField passwordField;
	
	 @FXML
	 private Label errorRegisterLabel;
	 
	 @FXML
	 private void handleRegister(MouseEvent mouseEvent) {
	    String name = nameField.getText();
	    String lastName = lastNameField.getText();
	    String login = loginField.getText();
	    String password = passwordField.getText();
	    String result = "failure";
	    errorRegisterLabel.setText("");		
	    	
		if (makeConnection()) {
			try {
	            	
				CallableStatement stm = dbConnector.getConnection().prepareCall("{CALL addWorker(?,?,?,?) }");
	            stm.setString(1, name);
	            stm.setString(2, lastName);
	            stm.setString(3, login);
	            stm.setString(4, password);
	            ResultSet rs = stm.executeQuery();
	            if(rs.next()) 
	            	result = rs.getString("resultat");
	            
	            if(result.equals("success"))
	            	errorRegisterLabel.setText("Udalo sie zarejestrowac");
	            else 
	            	errorRegisterLabel.setText("Uzytkownik zajety");
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
		        passwordField.clear();
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
