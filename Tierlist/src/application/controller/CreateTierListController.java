package application.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ItemsM;
import model.TierM;
import services.data.DataManager;
import services.data.Temporary;
import services.data.TierList;
import services.screenManager.Manager;
import services.screenManager.PageType;

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

	@FXML
	FlowPane itemsContainer;

	private TierList tierlist = null;

	private List<TierM> tiers = new ArrayList<TierM>();
	private List<ItemsM> items = new ArrayList<ItemsM>();
	
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

		lastUpdate.setText("");
		
		if (Temporary.getInstance().getCurrentTierList_edit() != null) {
			tierlist = Temporary.getInstance().getCurrentTierList_edit();
			
			Title.setText(tierlist.getName());
			//on ajoute les tiers importer
			refreshTier(tierlist.getTier());
			
			//on ajoute les items importer
			refreshItems(tierlist.getItems());
			
			setLastUpdate(tierlist.getDate());
		}
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
			stage.setResizable(false);

			stage.showAndWait();

			List<TierM> newTier = controller.getTier();
			if (newTier != null) {
				refreshTier(newTier);
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
			stage.setResizable(false);

			stage.showAndWait();

			List<ItemsM> newItems = controller.getItems();
			refreshItems(newItems);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void saveTierList() // fonction pour initier la sauvegarde
	{
		tierlist = new TierList(Title.getText(), tiers, items);
		tierlist.setDate(LocalDateTime.now());
		setLastUpdate(tierlist.getDate());
		DataManager.getInstance().saveTierList(tierlist);
	}
	@FXML
	public void exportTierList() {
		tierlist = new TierList(Title.getText(), tiers, items);
		tierlist.setDate(LocalDateTime.now());
		setLastUpdate(tierlist.getDate());
		DataManager.getInstance().exportFileTierlist(tierlist);

	}
	@FXML
	public void resetTierList() {
		this.tiers.clear();
		this.items.clear();

		this.TierContainer.getChildren().clear();
		this.itemsContainer.getChildren().clear();

		this.Title.setText("Nom de la tiers list");
		tierlist = null;
	}

	@FXML
	public void deleteTierList() {
		if(tierlist != null)
		{
			DataManager.getInstance().deleteTierlist(tierlist.getName());
			resetTierList();
		}
	}

	@FXML
	public void configureTierList() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/modal/editTierslist.fxml"));
			Parent root = loader.load();

			ConfigureTierList controller = loader.getController();
			controller.setTiersAndItems(tiers, items);

			Stage stage = new Stage();
			Stage ownerStage = (Stage) TierContainer.getScene().getWindow();
			stage.initOwner(ownerStage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("Configurer la TiersList");

			stage.showAndWait();

			// on efface les tiers avant de mettre les nouveau

			if (controller.getTiers() != null) {
				// on delete la liste des tiers
				tiers.clear();
				// on delete tout les tiers afficher
				TierContainer.getChildren().clear();
				refreshTier(controller.getTiers());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void refreshTier(List<TierM> m) {
	    if (m == null || m.isEmpty()) {
	        return;
	    }

	    for (TierM t : m) {
	        Tier tier = new Tier(t, TierType.PLAY);
	        tier.setWitdh(TierContainer.getWidth());
	        TierContainer.getChildren().add(tier);
	        tiers.add(t);
	    }
	    TierContainer.requestLayout();
	}

	private void refreshItems(List<ItemsM> m) {
	    if (m == null || m.isEmpty()) {
	        return;
	    }

	    for (ItemsM t : m) {
	        itemsContainer.getChildren().add(new Items(t));
	        items.add(t);
	    }
	    TierContainer.requestLayout();
	}

	private void setLastUpdate(LocalDateTime date) {
	    if (date == null) {
	        lastUpdate.setText("Aucune modification enregistrée");
	        return;
	    }
	    lastUpdate.setText(String.format("Dernière modification le %02d/%02d/%d à %02d:%02d", 
	            date.getDayOfMonth(), 
	            date.getMonthValue(), 
	            date.getYear(), 
	            date.getHour(), 
	            date.getMinute()));
	}

	@FXML
	public void goBack() {
		Temporary.getInstance().setCurrentTierList_edit(null);
		Manager.getInstance().setPage(PageType.HOME);
	}
	
    @FXML
    private void ClickAccueil(MouseEvent event) throws IOException {
        Manager.getInstance().setPage(PageType.HOME);
    }

    @FXML
    private void ClickMesTierList(MouseEvent event) throws IOException {
        Manager.getInstance().setPage(PageType.MYTIERLIST);
    }

    @FXML
    private void ClickImporter(MouseEvent event) throws IOException {
		DataManager.getInstance().importFileTierlist();
    }

    @FXML
    private void ClickParametres(MouseEvent event) throws IOException {
        //TODO Param
    }

    @FXML
    private void ClickAide(MouseEvent event) throws IOException {
        //TODO
    }
}
