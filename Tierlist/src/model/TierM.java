package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class TierM implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<ItemsM> content = new ArrayList<ItemsM>();
	
	//on stocke les couleur mais quand on save le fichier binaire on ne sauvegarde pas la couleur
	private transient Color backColor;
	private transient Color nameColor; //TODO a l'import il faut recrée la couleur;
	
	private double backColor_bleu;
	private double backColor_red;
	private double backColor_green;

	
	private double nameColor_bleu;
	private double nameColor_red;
	private double nameColor_green;
	
	private static final float DEFAULT_WITDH = 150;
	private static final float DEFAULT_HEIGHT = 50;
	
	float width, height;
	
	public TierM(String name, Color background, Color foreground)
	{
		this.name = name;
		this.width = DEFAULT_WITDH;
		this.height = DEFAULT_HEIGHT;
		this.backColor = background;
		this.nameColor = foreground;
		
		backColor_bleu = backColor.getBlue();
		backColor_red = backColor.getRed();
		backColor_green = backColor.getGreen();
		
		nameColor_green = nameColor.getGreen();
		nameColor_red = nameColor.getRed();
		nameColor_bleu = nameColor.getBlue();

	}
	
	public TierM(String name, float w, float h, Color background, Color foreground)
	{
		this(name, background, foreground);
		this.width = w;
		this.height = h;
	}
	
	public void addItem(ItemsM c)
	{
		content.add(c);
	}
	
	public void removeItem(ItemsM it)
	{
		for(int i = 0; i < content.size(); i++)
		{
			if(content.get(i) == it)
			{
				content.remove(i);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}
	
	public Color getNameColor() {
		return nameColor;
	}

	public void setNameColor(Color nameColor) {
		this.nameColor = nameColor;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	
}
