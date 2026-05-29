package services.data;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
	
	public void saveTierList(TierListManager tierlist)
	{
		
	}
	
	public File importTierList()
	{
		
		File fileSelected = null;
		
		JFileChooser fileChooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers JSON (*.json)", "json");

		fileChooser.setFileFilter(filter);

		int response = fileChooser.showOpenDialog(null);
		
		if(response == fileChooser.APPROVE_OPTION)
		{
			fileSelected = fileChooser.getSelectedFile();
		}
		
		
		return fileSelected;
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
