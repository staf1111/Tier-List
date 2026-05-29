package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.controller.modal.AddItemController;
import application.controller.modal.AddTierController;
import application.widget.Items;
import application.widget.Tier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ItemsM;
import model.TierM;
import services.data.DataManager;
import services.data.TierList;

public class CreateTierListController {

	@FXML
	Button AddTier, Reset, Save, Preview, Themes, back, TierSettings, Play, Export, AddItem;

	@FXML
	Label lastUpdate;

	@FXML
	VBox TierContainer;

	@FXML
	ScrollPane Scrollpane, Scrollpane2;

	@FXML
	TextField Title;

	@FXML FlowPane itemsContainer;
	
	private TierList tierlist = null;

	List<TierM> tiers = new ArrayList<TierM>();
	List<ItemsM> items = new ArrayList<ItemsM>();

	@FXML
	public void initialize() {
		Scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		Scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		Scrollpane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		Scrollpane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		Scrollpane.setFitToWidth(true);

		TierContainer.setSpacing(10);
		TierContainer.setAlignment(Pos.TOP_CENTER);
		TierContainer.setFillWidth(true);

		itemsContainer.setPrefWidth(Scrollpane2.getWidth());
		
		Title.setStyle("-fx-background-color: transparent;-fx-background-insets: 0;-fx-background-radius: 0;-fx-padding: 0;-fx-text-fill: #FFE45E");
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
			List<TierM> newTier = controller.getTier();
			tiers.addAll(newTier);
			if (newTier != null) {
				for (TierM t : newTier) {
					Tier tier = new Tier(t);
					tier.setWitdh(TierContainer.getWidth());
					TierContainer.getChildren().add(tier);
					tiers.add(t);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@FXML
	public void addItems() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/modal/importItem.fxml"));
	        Parent root = loader.load();

	        AddItemController controller = loader.getController();

	        Stage stage = new Stage();
	        Stage ownerStage = (Stage) TierContainer.getScene().getWindow();
	        stage.initOwner(ownerStage);
	        stage.initModality(Modality.WINDOW_MODAL);
	        stage.setScene(new Scene(root));
	        stage.setTitle("Ajouter une Items");

	        stage.showAndWait();

	        List<ItemsM> newItems = controller.getItems();
	        items.addAll(newItems);
	        if (!newItems.isEmpty()) {
	            for (ItemsM t : newItems) {
	                itemsContainer.getChildren().add(new Items(t));
	                items.add(t);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	@FXML
	public void saveTierList() // fonction pour initier la sauvegarde
	{
		tierlist = new TierList(Title.getText(), tiers, items);
		DataManager.getInstance().saveTierList(tierlist);
	}

}
