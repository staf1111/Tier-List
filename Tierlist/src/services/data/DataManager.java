package services.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
				if ("Image locale".equals(m.getName()))
				{
					ImageItems it = (ImageItems) m;
					Items.add(it);
				}
				else
				{
					Items.add(m);
				}
			}
			
			tierList = new TierList(obj.getName(),Tiers, Items); 
			
			oos.close();
			fos.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return tierList;
	}
	
	public void importFileTierlist() {
	    try {
	        File fileSelected = null;
	        JFileChooser fileChooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers Image (*.mmlc)", "mmlc");
	        fileChooser.setFileFilter(filter);

	        int response = fileChooser.showOpenDialog(null);
	        
	        if (response == JFileChooser.APPROVE_OPTION) {
	            fileSelected = fileChooser.getSelectedFile();
	            
	            String fileName = fileSelected.getName();
	            
	            Path destination = Paths.get(DEFAULT_PATH).resolve(fileName);
	            
	            Files.copy(fileSelected.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void exportFileTierlist(TierList t)
	{
	    try {
	    	JFileChooser folderChooser = new JFileChooser();
	        
	        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        
	        folderChooser.setDialogTitle("Sélectionnez le dossier de destination");

	        int response = folderChooser.showOpenDialog(null);
	        
	        if (response == JFileChooser.APPROVE_OPTION) {
	            File folderSelected = folderChooser.getSelectedFile();
	            
	            String folderPath = folderSelected.getAbsolutePath();
	            	            
	    		try {
	    			FileOutputStream fos = new FileOutputStream(folderPath + File.separator +t.getName() + ".mmlc");
	    			ObjectOutputStream  oos = new ObjectOutputStream (fos);
	    			
	    			oos.writeObject(t);
	    			
	    			oos.close();
	    			fos.close();
	    			
	    		}catch (Exception e) {
	    			e.printStackTrace();
	    		}	
	        }	 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void duplicateTierList(TierList tierlist) {
		int count = 1;
		
		
		String newName = tierlist.getName() + "_copy" + count;
		
		while(isFileExist(newName))
		{
			newName = tierlist.getName() + "_copy" + count;
			count++;
		}

		TierList newTL = new TierList(newName, tierlist.getTier(), tierlist.getItems());
		newTL.setDate(tierlist.getDate());
		
		saveTierList(newTL);
	}
	
	public void deleteTierlist(String path)
	{
		if (path == null || path.trim().isEmpty()) {
	        return;
	    }
		
		try {
			Path filePath = Paths.get(path);
			
			if(!Files.exists(filePath)) 
			{
				String filename = path;
				if(!filename.endsWith(".mmlc")) //permettre de supprimer un fichier juste en aillant sont nom
				{
					filename += ".mmlc";
				}
				filePath = Paths.get(DEFAULT_PATH, filename);
			}
			
			
			if (Files.deleteIfExists(filePath)) {
	            System.out.println("Fichier supprimé avec succès : " + filePath.toAbsolutePath());
	        } else {
	            System.out.println("Aucun fichier trouvé à supprimer pour : " + path);
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public List<String> getRecentTierList()
	{
		List<String> res = new ArrayList<String>();
		int limit = Math.min(6, getAllTierList().size());
		
		for(int i = 0; i < limit; i++)
		{
			res.add(getAllTierList().get(i));
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
	
	private boolean isFileExist(String name)
	{
		File folder = new File(DEFAULT_PATH);
		if(!folder.exists())
		{
			folder.mkdir();
		}
		
		for(File file : folder.listFiles())
		{
			
			if(!file.isDirectory() && file.getName().contains(".mmlc"))
			{
				String filename = file.getName().replace(".mmlc", "");
				if(filename.equals(name)) return true;
			}
		}
		
		return false;
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
