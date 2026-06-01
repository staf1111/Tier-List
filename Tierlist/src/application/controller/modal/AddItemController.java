package application.controller.modal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.widget.Items;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ImageItems;
import model.ItemsM;
import model.TextItems;
import services.api.Api;

public class AddItemController {

	@FXML Button ajouter, quitter, localImage, rechercherFilm, rechercherJeu;
	@FXML TextField text, rechercheFilm, rechercheJeu;
	
	@FXML TabPane nav;
	@FXML ImageView localImageIcon;
	
	@FXML StackPane imgFilm, imgJeu;
	
	@FXML Label SysMsg, SysMsg1;
	
	List<ItemsM> items = new ArrayList<ItemsM>();
	
	ImageItems resRequest = null;

	private String imagePath;
	
	@FXML
	public void initialize()
	{
		imgFilm.setAlignment(Pos.CENTER);
		imgJeu.setAlignment(Pos.CENTER);
	}
	
	@FXML 
	public void ajouter()
	{
		SysMsg.setText("");
		Tab ongletActif = nav.getSelectionModel().getSelectedItem();
		
		if (ongletActif == null) return;

	    String titreOnglet = ongletActif.getText();
	    
		switch (titreOnglet) {
		case "Texte": 
		    if (text == null) {
		    	SysMsg.setText("Erreur: champ texte non trouvé (fx:id manquant?)");
		    	SysMsg.setStyle("-fx-text-fill: Red;");
		        return;
		    }
			TextItems Ti = new TextItems(titreOnglet, text.getText());
			items.add(Ti);
	    	SysMsg.setText("Item ajouté");
	    	SysMsg.setStyle("-fx-text-fill: Green;");
			break;
		case "Image locale":
			ImageItems II = new ImageItems(titreOnglet, imagePath);
			items.add(II);
	    	SysMsg1.setText("Item ajouté");
	    	SysMsg1.setStyle("-fx-text-fill: Green;");
	    	break;
		case "Image film":
			items.add(resRequest);
			break;
		case "Image Jeu":
			items.add(resRequest);
			break;
		}
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
	        
	        imagePath = fileSelected.getAbsolutePath();
	        
	        String uriPath = fileSelected.toURI().toString();
	        Image image = new Image(uriPath);
	        
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

	@FXML
	private void afficherFilm() {
	    String query = rechercheFilm.getText();
	    ImageItems img = Api.getInstance().TMDB_request(query);
	    if(img!=null) {
	    	Items it = new Items(img);
	    	imgFilm.getChildren().add(it);
	    	resRequest = img;
	    }
	}

	@FXML
	private void afficherJeu() {
	    String query = rechercheJeu.getText();
	    ImageItems img = Api.getInstance().RAWG_request(query);
	    if(img!=null) {
	    	Items it = new Items(img);
	    	imgJeu.getChildren().add(it);
	    	resRequest = img;
	    }
	}
}
