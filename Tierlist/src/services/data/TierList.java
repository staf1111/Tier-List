package services.data;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import model.ItemsM;
import model.TierM;

public class TierList implements Serializable { // class qui stocke la tierlist
	private static final long serialVersionUID = 1L;
	
	private List<TierM> tier;
	private List<ItemsM> items;
	private String name;
	private LocalDateTime date;
	private byte[] imagePres;
	
	public TierList(String n, List<TierM> tier,List<ItemsM> items){
		name = n;
		this.tier = tier;
		this.items = items;
	}

	public List<TierM> getTier() {
		return tier;
	}

	public void setTier(List<TierM> tier) {
		this.tier = tier;
	}

	public List<ItemsM> getItems() {
		return items;
	}

	public void setItems(List<ItemsM> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setDate(LocalDateTime d)
	{
		this.date = d;
	}
	
	public LocalDateTime getDate()
	{
		return this.date;
	}
	
	public void setImagePres(String path) {
	    try {
	        imagePres = Files.readAllBytes(Paths.get(path)); 
	    } catch (IOException e) {
	        System.err.println("Erreur lors de la lecture de l'image au chemin : " + path);
	        e.printStackTrace();
	    }
	}
	
	public byte[] getImagePres()
	{
		return imagePres;
	}
}
