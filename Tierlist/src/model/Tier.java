package model;

import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Tier extends HBox{
	private String name;
	private List<Items> content;
	
	private Color backColor;
	private Color nameColor;
	
	private int rank;
	
	private static final float DEFAULT_WITDH = 150;
	private static final float DEFAULT_HEIGHT = 50;
	
	public Tier(String name, Color bacground, Color textfield)
	{
		this(name, DEFAULT_WITDH, DEFAULT_HEIGHT, bacground, textfield);
	}
	
	public Tier(String name, float w, float h, Color bacground, Color textfield)
	{
		this.name = name;
		
		this.setPrefHeight(h);
		this.setPrefWidth(w);
		
		this.backColor = bacground;
		this.nameColor = textfield;
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
}
