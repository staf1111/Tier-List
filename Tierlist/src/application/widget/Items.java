package application.widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.ItemsM;

public class Items extends StackPane {
	public static final int DEFAULT_WIDTH = 69;
	public static final int DEFAULT_HEIGHT = 69;
	
	
	public Items(ItemsM model)
	{
		this(model, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public Items(model.ItemsM model, int width, int height) {
	    this.setPrefSize(width, height);
	    this.setMinSize(width, height);
	    this.setMaxSize(width, height);
	    this.setPadding(new Insets(5));
	    
	    if (model.getName() == null || model.getContent() == null) {
	        this.getChildren().add(new Label("[Vide]"));
	        return;
	    }

	    if (model.getName().equalsIgnoreCase("texte")) {
	        Label l = new Label(model.getContent());
	        l.setPrefSize(width, height);
	        l.setAlignment(Pos.CENTER);
	        l.setStyle("-fx-text-fill: white; -fx-background-color: black;");
	        l.setWrapText(true);
	        
	        this.getChildren().add(l);
	    } else {
	    	try {
	            Image image = new Image(model.getContent());
	            if (image.isError()) {
	                System.err.println("Erreur chargement image : " + model.getContent());
	                Label errorLabel = new Label("[Image Erreur]");
	                errorLabel.setStyle("-fx-text-fill: red;");
	                this.getChildren().add(errorLabel);
	            } else {
	                ImageView imageView = new ImageView(image);
	                imageView.setFitWidth(width);
	                imageView.setFitHeight(height);
	                imageView.setPreserveRatio(true); 
	                this.getChildren().add(imageView);
	            }
	        } catch (Exception e) {
	            System.err.println("Exception lors du chargement de l'image : " + e.getMessage());
	            this.getChildren().add(new Label("[Erreur]"));
	        }
	    }
	}
}
