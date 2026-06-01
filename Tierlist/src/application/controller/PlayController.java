package application.controller;
 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.widget.Items;
import application.widget.Tier;
import application.widget.TierType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.ItemsM;
import model.TierM;
import services.data.Settings;
import services.data.TierList;
import services.screenManager.Manager;
import services.screenManager.PageType;
 
public class PlayController {
 
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
	

	List<TierM> tiers = new ArrayList<TierM>();
	List<ItemsM> items = new ArrayList<ItemsM>();
	private static final String DRAG_KEY = "ITEM_DRAG";
	private Items draggedItem  = null;
	private Node  draggedSource = null;
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
		lastUpdate.setText("");
		enableDropOnFlowPane(itemsContainer);
 
		if (Settings.getInstance().getCurrentTierList_play() != null) {
			tierlist = Settings.getInstance().getCurrentTierList_play();
			Title.setText(tierlist.getName());
			refreshTier(tierlist.getTier());
			refreshItems(tierlist.getItems());
			if (tierlist.getDate() != null) setLastUpdate(tierlist.getDate());
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
 
	@FXML
	public void resetTierList() {
	    System.out.println("Reset button clicked!");
	}
	
	private void refreshItems(List<ItemsM> m) {
		if (m == null || m.isEmpty()) {
			return;
		}
		for (ItemsM t : m) {
			itemsContainer.getChildren().add(buildDraggableItem(t));
			items.add(t);
		}
		TierContainer.requestLayout();
	}
 
	@FXML
	public void exportTierList() {
		// TODO exportTierList
	}
 
	@FXML
	public void goBack() {
		Settings.getInstance().setCurrentTierList_play(null);
		Manager.getInstance().setPage(PageType.HOME);
	}
 
	private void setLastUpdate(LocalDateTime date) {
		if (date == null) return;
		lastUpdate.setText(String.format(
				"Dernière modification le %d/%s/%d à %02d:%02d",
				date.getDayOfMonth(), date.getMonth(), date.getYear(),
				date.getHour(), date.getMinute()
		));
	}
	private Items buildDraggableItem(ItemsM model) {
	    Items widget = new Items(model);

	    widget.setOnDragDetected(e -> {
	        draggedItem   = widget;
	        draggedSource = widget.getParent();
	        Dragboard db  = widget.startDragAndDrop(TransferMode.MOVE);
	        ClipboardContent cc = new ClipboardContent();
	        cc.putString(DRAG_KEY);
	        db.setContent(cc);
	        db.setDragView(widget.snapshot(null, null));
	        e.consume();
	    });

	    widget.setOnDragDone(e -> {
	        draggedItem   = null;
	        draggedSource = null;
	        e.consume();
	    });

	    return widget;
	}

	private void enableDropOnFlowPane(FlowPane target) {

	    target.setOnDragOver(e -> {
	        if (e.getGestureSource() != target
	                && e.getDragboard().hasString()
	                && DRAG_KEY.equals(e.getDragboard().getString())) {
	            e.acceptTransferModes(TransferMode.MOVE);
	        }
	        e.consume();
	    });

	    target.setOnDragDropped(e -> {
	        if (e.getDragboard().hasString()
	                && DRAG_KEY.equals(e.getDragboard().getString())
	                && draggedItem != null) {

	            if (draggedSource instanceof FlowPane src) {
	                src.getChildren().remove(draggedItem);
	            }
	            target.getChildren().add(draggedItem);
	            e.setDropCompleted(true);
	        } else {
	            e.setDropCompleted(false);
	        }
	        e.consume();
	    });
	}
}