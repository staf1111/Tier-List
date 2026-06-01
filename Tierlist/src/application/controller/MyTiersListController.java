package application.controller;

import java.io.IOException;
import java.util.List;

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

public class MyTiersListController {

    @FXML
    public HBox accueil, mestierlist, importer, parametres, aide;
    @FXML
    public Button newtierlist, imptierlist;
    @FXML
    public FlowPane tierlistPreviewContainer;
    
    @FXML
    public ScrollPane tierlistPreview;
    
    private static final int MAX_LOAD_PER_STEP = 6; //on charge 12 preview par 12 preview pour eviter les crashs;
    private int currentLimit = 6;
    public void initialize() {
    	show();
    	tierlistPreview.vvalueProperty().addListener((observable, oldValue, NewValue) ->{
    		if(NewValue.doubleValue() > 0.95)
    		{
    			List<String> all = DataManager.getInstance().getAllTierList();
    			if(all != null && currentLimit < all.size())
    			{
    				Platform.runLater(()->{
    					currentLimit += MAX_LOAD_PER_STEP;
    					showMore();
    				});
    			}
    		}
    	});
    }

    private void show() {
    	tierlistPreviewContainer.getChildren().clear();
    	showMore();
    }
    private void showMore()
    {
    	List<String> all = DataManager.getInstance().getAllTierList();
    	
    	int currentShow = tierlistPreviewContainer.getChildren().size();
    	
    	int maxToload = Math.min(currentLimit, all.size());
    	
    	for(int i = currentShow; i < maxToload; i++)
    	{
    		String t = all.get(i);
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
            show();
        });
        TP.setOnDelete(()->{
        	Platform.runLater(()->{
        		DataManager.getInstance().deleteTierlist(path);
        		System.gc(); //on lance un garbage collector
        		show();
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
}