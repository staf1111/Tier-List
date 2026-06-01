package application.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.widget.Items;
import application.widget.Tier;
import application.widget.TierType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

import model.ItemsM;
import model.TierM;
import services.data.Settings;
import services.data.TierList;
import services.screenManager.Manager;
import services.screenManager.PageType;

public class PlayController {

    @FXML Button AddTier, Reset, Save, Preview, Themes, back, TierSettings, Play, Export, AddItem;
    @FXML Label lastUpdate;
    @FXML VBox TierContainer;
    @FXML ScrollPane Scrollpane, Scrollpane2;
    @FXML TextField Title;
    @FXML FlowPane itemsContainer;

    private TierList tierlist = null;

    List<TierM> tiers = new ArrayList<>();
    List<ItemsM> items = new ArrayList<>();

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

        Title.setEditable(false);

        lastUpdate.setText("");

        if (Settings.getInstance().getCurrentTierList_play() != null) {

            tierlist = Settings.getInstance().getCurrentTierList_play();

            Title.setText(tierlist.getName());

            refreshTier(tierlist.getTier());
            refreshItems(tierlist.getItems());

            if (tierlist.getDate() != null) {
                setLastUpdate(tierlist.getDate());
            }
        }
    }

    // 🔥 ITEMS DRAGGABLE PROPRE
    private Items buildDraggableItem(ItemsM t) {

        Items item = new Items(t);

        item.setOnDragDetected(e -> {

            Dragboard db = item.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent cc = new ClipboardContent();
            cc.putString("ITEM_DRAG");

            db.setContent(cc);
            db.setDragView(item.snapshot(null, null));

            e.consume();
        });

        return item;
    }

    private void refreshTier(List<TierM> m) {
        if (m == null) return;

        for (TierM t : m) {
            Tier tier = new Tier(t, TierType.PLAY);
            tier.setWitdh(TierContainer.getWidth());
            TierContainer.getChildren().add(tier);
            tiers.add(t);
        }

        TierContainer.requestLayout();
    }

    private void refreshItems(List<ItemsM> m) {
        if (m == null) return;

        itemsContainer.getChildren().clear();

        for (ItemsM t : m) {
            Items item = buildDraggableItem(t);
            itemsContainer.getChildren().add(item);
            items.add(t);
        }
    }

    // 🔥 RESET PROPRE (NE CASSE PAS LES TIERS)
    @FXML
    public void resetTierList() {

        List<Node> allItems = new ArrayList<>();

        for (Node node : TierContainer.getChildren()) {

            if (node instanceof Tier tier) {

                // récupérer tous les items dans les tiers
                for (Node child : tier.getChildren()) {

                    if (child instanceof HBox box) {
                        allItems.addAll(box.getChildren());
                    }
                }

                // vider uniquement la zone items du tier (PAS le tier entier)
                for (Node child : tier.getChildren()) {
                    if (child instanceof HBox box) {
                        box.getChildren().clear();
                    }
                }
            }
        }

        itemsContainer.getChildren().addAll(allItems);

        System.out.println("Reset OK");
    }

    @FXML
    public void exportTierList() {
        // TODO
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
                date.getDayOfMonth(),
                date.getMonth(),
                date.getYear(),
                date.getHour(),
                date.getMinute()
        ));
    }
}