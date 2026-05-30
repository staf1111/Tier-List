package application.controller.modal;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import model.ItemsM;
import model.TierM;

public class ConfigureTierList {
	
	@FXML TabPane pane;
	@FXML Tab Edit_tier, Edit_items;
	@FXML Pane windows;
	private List<TierM> tiers = new ArrayList<TierM>();
	private List<ItemsM> items = new ArrayList<ItemsM>();
	
	
	
	
	
	
	@FXML
	public void initialize()
	{
		//TODO
		pane.prefWidthProperty().bind(windows.widthProperty());
	    pane.prefHeightProperty().bind(windows.heightProperty());
		
	}
	
	
	
	
	
	
	public void setTiersAndItems(List<TierM> t, List<ItemsM> i)
	{
		tiers = t;
		items = i;
	}

	public List<TierM> getTiers() {
		return tiers;
	}


	public List<ItemsM> getItems() {
		return items;
	}
		
	 
}
