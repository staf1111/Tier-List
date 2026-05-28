package application.controller.modal;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Tier;

public class AddTierController {
	private List<Tier> tier = new ArrayList<Tier>();
	
	@FXML ColorPicker textColor, backColor;
	
	@FXML TextField name;
	
	@FXML Pane PanePreview;
	
	@FXML Label TextPreview;
	
	
	@FXML
	public void initialize() {
		
		
		TextPreview.textProperty().bind(name.textProperty());
		
		textColor.valueProperty().addListener((observable, oldColor, newColor) ->{
			if(newColor != null)
			{
				TextPreview.setTextFill(newColor);
			}
		});
		backColor.valueProperty().addListener((observable, oldColor, newColor) ->{
			if(newColor != null)
			{
				PanePreview.setBackground(new Background(new BackgroundFill(newColor, null, null)));
			}
		});
		
		init();
	}
	
	
	private void init()
	{
		textColor.setValue(Color.BLACK);
		TextPreview.setTextFill(textColor.getValue());
		PanePreview.setBackground(new Background(new BackgroundFill(backColor.getValue(), null, null)));
	}
	
	@FXML
	public void save()
	{
		tier.add(new Tier(name.getText(), backColor.getValue(), textColor.getValue()));
	}
	
	public void reset() {
		init();
	}
	
	
	public List<Tier> getTier()
	{
		return tier;
	}
	
	@FXML
	private void leave()
	{
		Stage stage = (Stage) name.getScene().getWindow();
		stage.close();
	}
}
