package application.controller.modal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ImageItems;
import model.ItemsM;
import model.TextItems;

public class AddItemController {

	@FXML Label MessageSystem;
	@FXML Button ajouter, quitter, localImage;
	@FXML TextField text;
	
	@FXML TabPane nav;
	@FXML ImageView localImageIcon;
	
	List<ItemsM> items = new ArrayList<ItemsM>();
	
	
	private String imagePath;
	
	
	
	@FXML 
	public void ajouter()
	{
		Tab ongletActif = nav.getSelectionModel().getSelectedItem();
		
		if (ongletActif == null) return;

	    String titreOnglet = ongletActif.getText();
	    System.out.println(titreOnglet);
	    
		switch (titreOnglet) {
		case "Texte": 
		    if (text == null) {
		        MessageSystem.setText("Erreur: champ texte non trouvé (fx:id manquant?)");
		        return;
		    }
			TextItems Ti = new TextItems(titreOnglet, text.getText());
			items.add(Ti);
			break;
		case "Image local":
			ImageItems II = new ImageItems(titreOnglet, imagePath);
			items.add(II);
			break;
		case "Image film":
			break;
		case "Image Jeu":
			break;
		}
		
		MessageSystem.setText("Items ajouté");
	}
	@FXML
	public void getLocalImage()
	{
		File fileSelected = null;
		
		JFileChooser fileChooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers Image (*.png, *.jpg, *.jpeg)", "png", "jpg", "jpeg");
		
		fileChooser.setFileFilter(filter);

		int response = fileChooser.showOpenDialog(null);
		
		if(response == fileChooser.APPROVE_OPTION)
		{
			fileSelected = fileChooser.getSelectedFile();
			
			imagePath = fileSelected.toURI().toString();
			
			Image image = new Image(imagePath);
			
			localImageIcon.setImage(image);
		}
	}
	
	@FXML
	public void leave()
	{
		 Stage stage = (Stage) quitter.getScene().getWindow();
		 stage.close();
	}
	
	
	public List<ItemsM> getItems()
	{
		return items;
	}
	
}
