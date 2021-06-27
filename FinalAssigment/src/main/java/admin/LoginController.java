package admin;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import admin.GUI;

public class LoginController {
	
	DBConnector dbConnector;
	private static Scene scene;
	
	@FXML
	private TextField textField;

	@FXML
	private PasswordField passwordField;

    @FXML
	private Label errorLabel;
	@FXML
	private void showLoginWindow(MouseEvent mouseEvent) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
			scene = new Scene(root);
			GUI.getPrimaryStage().setScene(scene);
			GUI.getPrimaryStage().show();
		}
		catch(Exception ex) {System.err.println(ex.toString());};
		
	}
	
	 @FXML
	 private void handleLogin(MouseEvent mouseEvent) {
	    String login = textField.getText();
	    String password = passwordField.getText();
	    int logged = 0;
	        if (makeConnection()) {
	            try {
	            	CallableStatement stm = dbConnector.getConnection().prepareCall("{CALL loginAdmin(?,?) }");
	            	stm.setString(1, login);
	            	stm.setString(2, password);
	            	ResultSet rs = stm.executeQuery();
	            	if(rs.next()) 
	            		logged = rs.getInt("wynik");     
	                if(logged!=0) {
	                	admin.DBConnector.setIdAdmina(logged);
	                	try {
	            			Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
	            			scene = new Scene(root);
	            			GUI.getPrimaryStage().setScene(scene);
	            			GUI.getPrimaryStage().show();
	            		 }
	            			catch(Exception ex) {System.err.println(ex.toString());};
	                }
	                else {
	                	textField.clear();
			            passwordField.clear();
			            errorLabel.setText("BĹ�Ä�DNE HASĹ�O LUB LOGIN!");
	                }
	            } catch (SQLException ex) {
	            	System.out.println(ex.toString());
		            errorLabel.setText("Blad sql");
	            }
	        }
	    }
	
	 private boolean makeConnection() {
		 boolean connectedToSql = false;
		 dbConnector = DBConnector.getInstance();
		 try {
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_company?noAccessToProcedureBodies=true", "admin", "password");
		        dbConnector.setConnection(connection);
		        connectedToSql = true;
		 } 
		 catch (SQLException ex) {
		        textField.clear();
		        passwordField.clear();
		        errorLabel.setText("Blad logowania do mysql");
		 }
		 return connectedToSql;
	 }
	 
}