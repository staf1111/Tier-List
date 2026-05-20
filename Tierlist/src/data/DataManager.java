package data;

import java.io.File;

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
	
	
	public void saveTierList()
	{
		
	}
	
	public void importTierList()
	{
		
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
