package services.data;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeUTF(tierlist.getName());
			
			//on écrit les Tier dans le fichier
			for(TierM t : tierlist.getTier())
			{
				dos.writeUTF(t.getName());
				dos.writeFloat(t.getWidth());
				dos.writeFloat(t.getHeight());
				
				dos.writeDouble(t.getBackColor().getRed());
                dos.writeDouble(t.getBackColor().getGreen());
                dos.writeDouble(t.getBackColor().getBlue());
                dos.writeDouble(t.getBackColor().getOpacity());
                
                dos.writeDouble(t.getNameColor().getRed());
                dos.writeDouble(t.getNameColor().getGreen());
                dos.writeDouble(t.getNameColor().getBlue());
                dos.writeDouble(t.getNameColor().getOpacity());
			}
			
			
			for(ItemsM i : tierlist.getItems())
			{
				dos.writeUTF(i.getName());
				dos.writeUTF(i.getContent());
				
				if(i.getName() == "Image local")
				{
					Path path = Paths.get(i.getContent());
					if(Files.exists(path))
					{
						byte[] octet = Files.readAllBytes(path);
						dos.writeInt(octet.length);
						dos.write(octet);
					}
					else
					{
						dos.write(0);
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TierList importTierList()
	{
		return null;
	}
	
	
	public void saveSettings()
	{
		
	}
	
	public void readSettings()
	{
		
	}
	
	public void updateSettings()
	{
		
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
