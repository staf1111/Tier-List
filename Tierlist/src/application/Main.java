package application;

import javafx.application.Application;
import javafx.stage.Stage;
import services.data.DataManager;
import services.data.Settings;
import services.screenManagement.Manager;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Settings.init();  //on initialise la classe qui va gérer les paramètre de l'application
			DataManager.init();  //on initialise la classe qui va géré la sauvegarde des donnée et l'importation des tier list ainsi que 
			
			Manager.init(primaryStage); //on initialise la classe qui va géré les différente page et modal
						
			primaryStage.setTitle("Tierlist");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
