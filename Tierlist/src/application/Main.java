package application;

import javafx.application.Application;
import javafx.stage.Stage;
import services.api.Api;
import services.data.DataManager;
import services.data.Settings;
import services.screenManager.Manager;
import services.screenManager.PageType;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Settings.init();  //on initialise la classe qui va gérer les paramètre de l'application
			DataManager.init();  //on initialise la classe qui va géré la sauvegarde des donnée et l'importation des tier list ainsi que 

			Manager.init(primaryStage); //on initialise la classe qui va géré les différente page et modal
			Settings.getInstance().setCurrentTierList_play(DataManager.getInstance().importTierList("C:\\Users\\mustafa\\Documents\\TierList_TD11A\\Nom de la tiers list.mmlc"));
            Manager.getInstance().setPage(PageType.PLAY);
			DataManager.getInstance().getAllTierList();
			Api.init();
				
			
			primaryStage.setTitle("Tierlist");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
