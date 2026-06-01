package application.controller;

import java.io.IOException;

import application.widget.Tierlist_Preview;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.TierList_previewM;
import services.data.DataManager;
import services.data.TierList;
import services.screenManager.Manager;
import services.screenManager.PageType;

public class HomeController {

    @FXML
    private HBox accueil, mestierlist, importer, parametres, aide;
    @FXML
    private Button newtierlist, imptierlist;
    
    @FXML
    private ScrollPane tierlistPreview;
    @FXML
    private FlowPane tierlistPreviewContainer;
    
    @FXML
    public void initialize() {
    	//on récupère les 6 première tierlist
    	showRecently();
    }

    private void showRecently() {
    	tierlistPreviewContainer.getChildren().clear();
        for (String t : DataManager.getInstance().getRecentTierList()) {
            TierList imported = DataManager.getInstance().importTierList(t);
            if (imported != null) { 
                Tierlist_Preview TP = createTierListPreview(imported, t);
                tierlistPreviewContainer.getChildren().add(TP);
            }
        }
    }
    private Tierlist_Preview createTierListPreview(TierList tierList, String path) {
        TierList_previewM TPm = new TierList_previewM(tierList, path);
        Tierlist_Preview TP = new Tierlist_Preview(TPm);
        
        TP.setOnDuplicate(() -> {
            DataManager.getInstance().duplicateTierList(tierList); 
            showRecently();
        });
        TP.setOnDelete(()->{
        	Platform.runLater(()->{
        		DataManager.getInstance().deleteTierlist(path);
        		System.gc(); //on lance un garbage collector
        		showRecently();
        	});
        });
        return TP;
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
        //TODO
    }

    @FXML
    private void ClickParametres(MouseEvent event) throws IOException {
        //TODO
    }

    @FXML
    private void ClickAide(MouseEvent event) throws IOException {
        //TODO
    }

    @FXML
    private void ClickNouvelleTierList(ActionEvent event) throws IOException {
        Manager.getInstance().setPage(PageType.CREATETIERLIST);
    }

    @FXML
    private void ClickImporterTierList(ActionEvent event) throws IOException {
        //TODO
    }
}