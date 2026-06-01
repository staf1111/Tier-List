package application.controller;

import java.io.IOException;

import application.widget.Tierlist_Preview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.TierList_previewM;
import services.data.DataManager;
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
    public void initialize()
    {
    	
    	for(String t : DataManager.getInstance().getRecentTierList())
    	{
    		TierList_previewM TPm = new TierList_previewM(DataManager.getInstance().importTierList(t));
    		Tierlist_Preview TP = new Tierlist_Preview(TPm);
    		
    		tierlistPreviewContainer.getChildren().add(TP);
    		
    	}
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