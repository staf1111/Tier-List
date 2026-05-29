package application.widget;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.TierM;

public class Tier extends HBox{

	private HBox itemsContainer;
	
	public Tier(TierM model)
	{
		
		this.setPrefHeight(model.getHeight());
		this.setPrefWidth(model.getWidth());
		this.setMaxWidth(Double.MAX_VALUE);
		

	    this.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
	    
	    this.setBackground(new Background(new BackgroundFill(Color.BLACK, null,null)));
		
	    StackPane labelPane = new StackPane();
	    double squareSize = model.getHeight();
	    
	    labelPane.setPrefWidth(squareSize);
	    labelPane.setPrefHeight(squareSize);
	    labelPane.setMinWidth(squareSize);
	    labelPane.setMinHeight(squareSize);
	    labelPane.setMaxWidth(squareSize); 
	    labelPane.setMaxHeight(squareSize);
	    
	    labelPane.setBackground(new Background(new BackgroundFill(model.getBackColor(),CornerRadii.EMPTY,Insets.EMPTY)));
	    labelPane.setAlignment(javafx.geometry.Pos.CENTER);
	    
	    Label text = new Label(model.getName());
	    text.setTextFill(model.getNameColor());
	    
	    labelPane.getChildren().add(text);
	    
		itemsContainer = new HBox();
		itemsContainer.setSpacing(5);
		
		HBox.setHgrow(itemsContainer, Priority.ALWAYS);
		this.borderProperty().set(new Border(new BorderStroke(
			    model.getBackColor(), 
			    BorderStrokeStyle.SOLID, 
			    CornerRadii.EMPTY, 
			    BorderWidths.DEFAULT
			)));
		
		this.getChildren().addAll(labelPane, itemsContainer);
	}
	
	public void setWitdh(double w)
	{
		this.setWidth(w);
	}
}
