package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.controller.modal.AddTierController;
import application.widget.Tier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Items;
import model.TierM;
import services.data.TierListManager;

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
		
		Scrollpane.setFitToWidth(true);
		
		
		tierlist = new TierListManager();
		
		TierContainer.setSpacing(10);
		TierContainer.setAlignment(Pos.TOP_CENTER);
		TierContainer.setFillWidth(true);
	}
	
	
	
	
	@FXML
	public void addTier() // fonction pour ajouter des tiers
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/modal/addTier.fxml"));
			Parent root = loader.load();
			
			AddTierController controller = loader.getController();
						
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Ajouter une Tier");
			
			stage.showAndWait();
			
			
			
			if(controller.getTier() != null)
			{
				for(TierM t : controller.getTier())
				{
					Tier tier = new Tier(t);
					tier.setWitdh(TierContainer.getWidth());
					TierContainer.getChildren().add(tier);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@FXML
	public void saveTierList() // fonction pour initier la sauvegarde
	{
		tierlist.setTier(this.tiers);
		tierlist.setItems(this.items);
	}
	
	
	
	
}
