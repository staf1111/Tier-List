package services.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import model.ImageItems;
import model.ItemsM;
import model.TierM;

public class DataManager {
	
	private static DataManager instance;
	
	private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TierList_TD11A";
	private static String path;
	
	public static void init()
	{
		instance = new DataManager();
	}
		
	private DataManager()
	{
		this.path = DEFAULT_PATH;
		pathVerify();
	}
	
	
	public void saveTierList(TierList tierlist)
	{
		try {
			FileOutputStream fos = new FileOutputStream(DEFAULT_PATH + File.separator +tierlist.getName() + ".mmlc");
			ObjectOutputStream  oos = new ObjectOutputStream (fos);
			
			oos.writeObject(tierlist);
			
			oos.close();
			fos.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public TierList importTierList(String path)
	{
		TierList tierList = null;
		List<ItemsM> Items = new ArrayList<ItemsM>();
		List<TierM> Tiers = new ArrayList<TierM>();
		
		try {
			FileInputStream fos = new FileInputStream(path);
			ObjectInputStream  oos = new ObjectInputStream(fos);
			
			TierList obj = (TierList) oos.readObject();
			
			for(TierM m : obj.getTier())
			{
				// on recrée les couleur
				Color background = new Color(m.getBackColor_red(), m.getBackColor_green(), m.getBackColor_bleu(), 1);
				Color texteFill = new Color(m.getNameColor_red(), m.getNameColor_green(), m.getNameColor_bleu(), 1);

				TierM model = new TierM(m.getName(), background, texteFill);
				Tiers.add(model);
			}
			
			for(ItemsM m : obj.getItems()) // on crée des items spé pour les image local étant donnée qu'elle contient des images au format byte
			{
				if ("Image local".equals(m.getName()))
				{
					ImageItems it = (ImageItems) m;
					Items.add(it);
				}
				Items.add(m);
			}
			
			tierList = new TierList(obj.getName(),Tiers, Items); 
			
			oos.close();
			fos.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return tierList;
	}
	
	
	public List<String> getAllTierList()
	{
		List<String> res = new ArrayList<String>();
		
		File folder = new File(DEFAULT_PATH);
		if(!folder.exists())
		{
			folder.mkdir();
		}
		
		for(File file : folder.listFiles())
		{
			
			if(!file.isDirectory() && file.getName().contains(".mmlc"))
			{
				res.add(file.getAbsolutePath());
			}
		}
		
		return res;
	}
	
	
	private void pathVerify() //on verifie que le dossier existe
	{
		File folder = new File(path);
		if(!folder.exists())
		{
			folder.mkdir();
		}
	}
	
	public static DataManager getInstance()
	{
		if(instance == null)
		{
			instance = new DataManager();
		}
		
		return instance;
	}
}
