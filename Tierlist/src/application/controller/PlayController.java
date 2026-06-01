package application.controller;


import application.controller.modal.AddItemController;
import application.controller.modal.AddTierController;
import application.controller.modal.ConfigureTierList;
import application.widget.Items;
import application.widget.Tier;
import application.widget.TierType;
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
import services.data.Settings;
import services.data.TierList;
import services.screenManager.Manager;
import services.screenManager.PageType;

public class PlayController {

	@FXML
	Button AddTier, Reset, Save, Preview, Themes, back, TierSettings, Play, Export, AddItem;



	@FXML
	VBox TierContainer;

	@FXML
	ScrollPane Scrollpane, Scrollpane2;

	@FXML
	TextField Title;
	
	@FXML
	FlowPane itemsContainer;

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
		Scrollpane2.setFitToWidth(true);

		TierContainer.setSpacing(10);
		TierContainer.setAlignment(Pos.TOP_CENTER);
		TierContainer.setFillWidth(true);

		Title.setStyle(
				"-fx-background-color: transparent;-fx-background-insets: 0;-fx-background-radius: 0;-fx-padding: 0;-fx-text-fill: #FFE45E");
		Title.setEditable(false);
		
		if (Settings.getInstance().getCurrentTierList_play() != null) {
			tierlist = Settings.getInstance().getCurrentTierList_play();
			
			Title.setText(tierlist.getName());
			//on ajoute les tiers importer
			refreshTier(tierlist.getTier());
			
			//on ajoute les items importer
			refreshItems(tierlist.getItems());
			
			setLastUpdate(tierlist.getDate());
		}
	}


	

	private void refreshTier(List<TierM> m) {
		if (m.isEmpty() || m == null) {
			return;
		}

		if (m != null) {
			for (TierM t : m) {
				Tier tier = new Tier(t, TierType.PLAY);
				tier.setWitdh(TierContainer.getWidth());
				TierContainer.getChildren().add(tier);
				tiers.add(t);
			}
			TierContainer.requestLayout();
		}
	}

	private void refreshItems(List<ItemsM> m) {
		if (m.isEmpty() || m == null) {
			return;
		}

		if (m != null) {

			for (ItemsM t : m) {
				itemsContainer.getChildren().add(new Items(t));
				item.add(t);
			}

			TierContainer.requestLayout();
		}
	}

	@FXML
	public void exportTierList() {
		// TODO exportTierList
	}

	@FXML
	public void goBack() {
		Settings.getInstance().setCurrentTierList_edit(null);
		Manager.getInstance().setPage(PageType.HOME);
	}
}
