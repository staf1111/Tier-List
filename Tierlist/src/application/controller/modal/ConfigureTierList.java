package application.controller.modal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.widget.Tier;
import application.widget.TierType;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ItemsM;
import model.TierM;

public class ConfigureTierList {
	
	@FXML TabPane pane;
	@FXML Tab Edit_tier, Edit_items;
	@FXML Pane windows;
	@FXML ScrollPane Scrollpane;
	@FXML VBox TierContainer;
	@FXML FlowPane itemsContainer;
	
	private List<TierM> tiers = new ArrayList<TierM>();
	private List<ItemsM> items = new ArrayList<ItemsM>();
	

	
	@FXML
	public void initialize()
	{
		pane.prefWidthProperty().bind(windows.widthProperty());
	    pane.prefHeightProperty().bind(windows.heightProperty());
	    
	    TierContainer.prefWidthProperty().bind(Scrollpane.widthProperty());
	    TierContainer.prefHeightProperty().bind(Scrollpane.heightProperty());
	}
	
	
	
	@FXML
	public void quitter()
	{
		Stage stage = (Stage) windows.getScene().getWindow();
		stage.close();
	}
	
	
	public void setTiersAndItems(List<TierM> t, List<ItemsM> i)
	{
		tiers.addAll(t);
		items.addAll(i);
		
		TierContainer.getChildren().clear();
		
		
		//on affiche les tiers;
	    for(TierM tr : tiers)
	    {
	    	Tier tier = new Tier(tr, TierType.EDIT);
	    	tier.setOnDown(()->{
	    		moveTier(tier, tr, 1);
	    	});
	    	tier.setOnUp(()->{
	    		moveTier(tier, tr, -1);
	    	});
	    	
	    	tier.setOnDelete(()->{
	    		TierContainer.getChildren().remove(tier);
	    		tiers.remove(tr);
	    		TierContainer.requestLayout();
	    		//TODO a finir
	    	});
	    	
	    	TierContainer.getChildren().add(tier);
	    }
	}

	//recupération des tiers et items
	public List<TierM> getTiers() {
		return tiers;
	}


	public List<ItemsM> getItems() {
		return items;
	}
		
	 
	
	private void moveTier(Tier tier, TierM tierM, int direction) //fonction pour bouger graphique une tier et modifier sa position dans le tableau qui stock le model
	{
		int currentIndex = TierContainer.getChildren().indexOf(tier); // index actuel de la tier;
		int newIndex = currentIndex + direction; // on modifi sont index;

		if(newIndex >= 0 && newIndex < TierContainer.getChildren().size())
		{
			//actualisation de manière graphique
			TierContainer.getChildren().remove(currentIndex);
			TierContainer.getChildren().add(newIndex, tier);
			
			//actualisation dans le tableau;
			
			int index = tiers.indexOf(tierM);
			if(index != -1)
			{
				//on change les items
				Collections.swap(tiers, index, index + direction);
			}
			
			TierContainer.requestLayout(); // on force l'actualisation de l'affichage
		}
	}
}
