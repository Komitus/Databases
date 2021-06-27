package user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TrolleyHandler {
	
	OrderParameters order;
	DBConnector dbConnector;
	Scene scene;
	ChoiceBox myChoiceBox;
	TextField myTextField;
	Button add;
	Button remove;
	Button confirm;
	VBox myVbox;
	ListView<String> trolleyList;
	Label myErrorLabel;
	Savepoint savePoint;
	
	public TrolleyHandler(){}
	
	public void createWindow(){
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("StuffPicker.fxml"));
			scene = new Scene(root);
		}
		catch(Exception ex) {System.err.println(ex.toString());};
		
		myChoiceBox = (ChoiceBox)scene.lookup("#myChoiceBox");
		myChoiceBox.getItems().addAll("narty", "snowboard", "buty", "kask");
		myChoiceBox.setValue("Wybierz");
		
		myTextField = (TextField)scene.lookup("#myTextField");
		myErrorLabel = (Label)scene.lookup("#errorLabel");
		add = (Button)scene.lookup("#add");
		add.setOnMouseClicked(e->addClicked());
		
		remove = (Button)scene.lookup("#remove");
		remove.setOnMouseClicked(e->removeClicked());
		
		confirm = (Button)scene.lookup("#confirm");
		confirm.setOnMouseClicked(e->confirm());
		myVbox = (VBox)scene.lookup("#myVbox");
		
		trolleyList = new ListView();
		trolleyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//trolleyList.getItems().addAll("1","2","3","4","5");
		myVbox.getChildren().add(trolleyList);
		order = OrderParameters.getInstance();
		/*try {
			savePoint = savePoint = dbConnector.getConnection().setSavepoint("beforeTrolley");
			dbConnector.getConnection().setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		GUI.getPrimaryStage().setScene(scene);
		GUI.getPrimaryStage().show();
		
	}
	
	private void removeClicked() {
		
		int index = trolleyList.getSelectionModel().getSelectedIndex();
		if(index != -1) {
			int id = order.trolley.get(index).getId();
			try {
				CallableStatement stm = dbConnector.getConnection().prepareCall("{CALL setCart(?,?) }");
            	stm.setInt(1,id);
            	stm.setInt(2,0);
            	ResultSet rs = stm.executeQuery();	
			}
			catch(SQLException ex){
				myErrorLabel.setText("Blad mysql \n usuwanie z koszyka");
			}
			finally {
				
				order.trolley.remove(index);
				trolleyList.getItems().remove(index);
				for(ProductData item : order.trolley)
				{
					System.out.println(item.getId());
				}
				//System.out.println(order.trolley);
			}
		}
		
	}

	private void addClicked() {
		
		boolean condition = false;
		String size = myTextField.getText();
		String type = (String)myChoiceBox.getSelectionModel().getSelectedItem();
		int tmp = 0;
		int result = 0;
		if(!type.equals("Wybierz")) {
			
			if(sizeCorrectness(size)) { 
				makeConnection();
				try {
					CallableStatement stm = dbConnector.getConnection().prepareCall("{CALL ifAvailable(?,?,?,?) }");
	            	stm.setInt(1,Integer.parseInt(size));
	            	stm.setString(2,type);
	            	stm.setDate(3,  Date.valueOf(order.getStartDate()));
	            	stm.setDate(4, Date.valueOf(order.getEndDate()));
	            	ResultSet rs = stm.executeQuery();
	            	if(rs.next()) 
	            		result = rs.getInt("wynik");  
	            		System.out.println(result);
				}
				catch(SQLException ex){
					myErrorLabel.setText("Blad mysql");
				}
				if(result > 0 ) {
					ProductData produkt = new ProductData(Integer.parseInt(size), type, result);
					order.trolley.add(produkt);
					trolleyList.getItems().add(type+"   rozmiar: "+size);
				}
				else if(result == -1) {
					myErrorLabel.setText("Zła data");
				}
				else myErrorLabel.setText("Niedostępny");
				
			}
			else myErrorLabel.setText("podaj dobry\nrozmiar");
			
		}
		else myErrorLabel.setText("wybierz sprzet");
		
		
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
			 myErrorLabel.setText("Blad logowania do mysql");
		 }
		 return connectedToSql;
	 }
	 private boolean sizeCorrectness(String size) {
		    Pattern pattern = Pattern.compile("^\\d{2,3}$");
		    Matcher matcher = pattern.matcher(size);
		    return matcher.matches();
	 }
	 private void confirm() {
		 
		 if(!order.trolley.isEmpty()) {
			 
		 
		 CallableStatement stm;
		 //CallableStatement stm2;
		 ResultSet rs;
		 boolean success = false;
		 int orderId=0;
		 int clientId = dbConnector.getIdKlienta();
		 try {
			 
			savePoint = dbConnector.getConnection().setSavepoint("afterTrolley");
			dbConnector.getConnection().setAutoCommit(false);
			//System.out.println(clientId);
			
			stm = dbConnector.getConnection().prepareCall("{CALL createOrder(?,?,?) }");
         	stm.setInt(1,clientId);
         	stm.setDate(2,Date.valueOf(order.getStartDate()));
         	stm.setDate(3,Date.valueOf(order.getEndDate()));
         	stm.executeQuery();
         	
         	stm = dbConnector.getConnection().prepareCall("{CALL getOrderId(?) }");
         	stm.setInt(1, clientId);
         	rs = stm.executeQuery();
         	if(rs.next()) orderId = rs.getInt("id");
         	//System.out.println(orderId);
         	
         	//Iterator<ProductData> iterator = order.trolley.iterator();
         	
         	for(ProductData item : order.trolley)
         	{
         		System.out.println("teraz przy dodawaniu");
         		int itemId = item.getId();
         		System.out.println(itemId);
         		stm = dbConnector.getConnection().prepareCall("{CALL dodajSkladniki(?,?) }");
         		stm.setInt(1, itemId);
         		stm.setInt(2, orderId);
         		stm.executeQuery();
         	}
         	
         	dbConnector.getConnection().commit();
         	success = true;
         	
		 }
		 catch(SQLException ex){
			try {
				dbConnector.getConnection().rollback(savePoint);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myErrorLabel.setText("Blad mysql");
			ex.printStackTrace();
		 }
		 finally {
			 try {
				 	Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
					scene = new Scene(root);
					Label label = (Label)scene.lookup("#successLabel");
					if(success) label.setText("Zamówienie dodane");
					else label.setText("Wystąpił błąd");
					order.trolley = null;
					trolleyList = null;
					dbConnector.getConnection().close();
					GUI.getPrimaryStage().setScene(scene);
					GUI.getPrimaryStage().show();
				}
				catch(Exception ex) {System.err.println(ex.toString());};
		 }
		 
		 }
		 else myErrorLabel.setText("Dodaj coś");
		 
	 }
	
}
