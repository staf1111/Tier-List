package model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Tier extends HBox{
	private String name;
	private List<Items> content = new ArrayList<Items>();
	private HBox itemsContainer;
	private Color backColor;
	private Color nameColor;
	
	private int rank;
	
	private static final float DEFAULT_WITDH = 150;
	private static final float DEFAULT_HEIGHT = 50;
	
	public Tier(String name, Color background, Color textfield)
	{
		this(name, DEFAULT_WITDH, DEFAULT_HEIGHT, background, textfield);
	}
	
	public Tier(String name, float w, float h, Color background, Color textfield)
	{
		this.name = name;
		
		this.setPrefHeight(h);
		this.setPrefWidth(w);
		this.setMaxWidth(Double.MAX_VALUE);
		
		this.backColor = background;
		this.nameColor = textfield;

	    this.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
	    
	    this.setBackground(new Background(new BackgroundFill(Color.BLACK, null,null)));
		
	    StackPane labelPane = new StackPane();
	    labelPane.setPrefWidth(h);
	    labelPane.setPrefHeight(h);
	    labelPane.setMinWidth(h);
	    labelPane.setMinHeight(h);
	    
	    labelPane.setBackground(new Background(new BackgroundFill(background, null, null)));
	    
	    Label text = new Label(name);
	    text.setTextFill(textfield);
	    
	    labelPane.getChildren().add(text);
	    
		itemsContainer = new HBox();
		itemsContainer.setSpacing(5);
		
		HBox.setHgrow(itemsContainer, Priority.ALWAYS);
		
		this.getChildren().addAll(labelPane, itemsContainer);
	}



	public void addContent(Items c)
	{
		content.add(c);
	}
	
	public void removeItem(Items it)
	{
		for(int i = 0; i < content.size(); i++)
		{
			if(content.get(i) == it)
			{
				content.remove(i);
			}
		}
	}
	
	public void setRank(int i)
	{
		this.rank = i;
	}
	
	public int getRank()
	{
		return this.rank;
	}
	
	
	public void setSize(double width, double height)
	{
		this.setPrefWidth(width);
		this.setPrefHeight(height);
	}
}
