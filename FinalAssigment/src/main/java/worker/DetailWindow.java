package worker;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import com.sun.glass.ui.MenuItem.Callback;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailWindow {

	private Scene scene;
	private DBConnector dbConnector;
	private ListView<String> list;
	private int orderId;
	private VBox orderList;
	
	public DetailWindow(int orderId) {
		try {
			this.orderId = orderId;
			dbConnector = DBConnector.getInstance();
			createWindow();
		} catch (Exception ex) {}
	}
	
	private void createWindow() throws Exception {
		orderList = new VBox();
		list = new ListView<String>();
		String query = "{CALL selectComponents("+orderId+") }";
		CallableStatement stm = dbConnector.getConnection().prepareCall(query);
    	ResultSet rs = stm.executeQuery();
    	while (rs.next()) {
    		System.out.println("wchodze do petelki");
    		String str = "id: "+rs.getInt("id")+" | marka: "+rs.getString("marka")+" | typ: "+rs.getString("typ")+" | rozmiar: "+rs.getInt("rozmiar");
    		list.getItems().add(str);
    	}
        orderList.setPadding(new Insets(20,20,20,20));
        orderList.getChildren().add(list);
        scene = new Scene(orderList, 625, 625);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Szczegó³y");
        stage.show();
	}
}
