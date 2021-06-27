package admin;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminController {
	private Scene scene;

	@FXML
	private void addWorker() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("RegisterWindow.fxml"));
			scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.setTitle("Dodaj pracownika");
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	private void addItem() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("AddItem.fxml"));
			scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.setTitle("Dodaj pracownika");
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void doBackUp() {
		 FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Zapisz kopiê");
	        File file = fileChooser.showSaveDialog(GUI.getPrimaryStage());
	        if (file != null) {
	            String dbname = "rental_company";

	            String executeCmd = "mysqldump --routines -u root -p1234 "
                        +" "+dbname+" -r "+ file.getAbsolutePath();

	            try {
	                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	                int processComplete = runtimeProcess.waitFor();
	                if (processComplete == 0) {
	                    System.out.println("Backup taken successfully");
	                } else {
	                    System.out.println("Could not take mysql backup");
	                }
	            }
	            catch (Exception ex) {
	                ex.printStackTrace();
	            }

	        }
	}
	
	@FXML
	private void restoreData() {
		 FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Otwórz kopiê");
	        File file = fileChooser.showOpenDialog(GUI.getPrimaryStage());
	        String dbname = "rental_company";
	        if (file != null) {
	            String[] executeCmd = new String[]{"mysql", "--user=root",
	                    "--password=1234", dbname,"-e", " source "+file.getAbsolutePath()};
	            try {
	                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	                int processComplete = runtimeProcess.waitFor();
	                if (processComplete == 0) {
	                    System.out.println("Restore success!");
	                } else {
	                    System.out.println("Restore failure.");
	                }
	            }
	            catch(Exception ex) {
	                System.err.println(ex.toString());
	            }
	        }
	}
}
