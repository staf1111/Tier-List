package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.controller.modal.AddTierController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Items;
import model.Tier;
import services.data.TierListManager;
import services.screenManager.Manager;

public class CreateTierListController {
	
	@FXML Button AddTier, Reset, Save, Preview, Themes, back, TierSettings, Play;
	
	@FXML Label Title, lastUpdate;
	
	@FXML VBox TierContainer;
	
	@FXML ScrollPane Scrollpane;
	
	
	private TierListManager tierlist = null;
	
	List<Tier> tiers = new ArrayList<Tier>();
	List<Items> items = new ArrayList<Items>();
	
	@FXML
	public void initialize() {
		Scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		Scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		
		TierContainer.setPrefWidth(Scrollpane.getWidth());
		TierContainer.setPrefHeight(Scrollpane.getHeight());
		
		
		tierlist = new TierListManager();
	}
	
	
	
	
	@FXML
	public void addTier()
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/modal/addTier.fxml"));
			Parent root = loader.load();
			
			AddTierController controller = loader.getController();
						
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Ajouter une Tier");
			
			stage.showAndWait();
			
			tiers = controller.getTier();
			
			for(Tier t : tiers)
			{
				TierContainer.getChildren().add(t);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@FXML
	public void saveTierList()
	{
		tierlist.setTier(this.tiers);
		tierlist.setItems(this.items);
	}
	
	
	
	
}
