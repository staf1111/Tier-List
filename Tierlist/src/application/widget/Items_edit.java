package application.widget;

import java.io.ByteArrayInputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.ImageItems;
import model.ItemsM;

public class Items_edit extends HBox {
	public static final int DEFAULT_WIDTH = 350;
	public static final int DEFAULT_HEIGHT = 80;

	public Items_edit(ItemsM model) {
		this(model, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public Items_edit(model.ItemsM model, int width, int height) {
		this.setPrefSize(width, height);
		this.setMinSize(width, height);
		this.setMaxSize(width, height);
		this.setPadding(new Insets(5));
		this.setSpacing(10);
		this.borderProperty().set(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setAlignment(Pos.CENTER_LEFT);
		if (model.getName() == null || model.getContent() == null) {
			this.getChildren().add(new Label("[Vide]"));
			return;
		}

		if (model.getName().equalsIgnoreCase("texte")) {
			Label l = new Label(model.getContent());
			l.setPrefSize(height, height);
			l.setAlignment(Pos.CENTER);
			l.setStyle("-fx-text-fill: white; -fx-background-color: gray;");
			l.setWrapText(true);
			
			this.getChildren().add(l);
		} else {
			try {
				Image image = null;

				if ("Image locale".equalsIgnoreCase(model.getName())) // si c'est une image local on affiche l'image a partir des byte de l'image et non du path
				{
					ImageItems II = (ImageItems) model;
					image = new Image(new ByteArrayInputStream(II.getImageData()));
				} else {
					image = new Image(model.getContent());
				}

				if (image.isError()) {
					System.err.println("Erreur chargement image : " + model.getContent());
					Label errorLabel = new Label("[Image Erreur]");

					errorLabel.setStyle("-fx-text-fill: red;");

					this.getChildren().add(errorLabel);
				} else {
					ImageView imageView = new ImageView(image);
					imageView.setFitWidth(height);
					imageView.setFitHeight(height);
					imageView.setPreserveRatio(true);
					this.getChildren().add(imageView);
				}
			} catch (Exception e) {
				System.err.println("Exception lors du chargement de l'image : " + e.getMessage());
				this.getChildren().add(new Label("[Erreur]"));
			}
			
		}
		
		if (model.getName().equalsIgnoreCase("texte")) 
		{
			TextField name = new TextField();
			name.setPromptText("my new text");
			name.getStyleClass().add("Zone-blanches-add-tier");
			name.setStyle("-fx-text-fill: White");
			
			this.getChildren().add(name);
		}
		Button delete = new Button("Delete");
		delete.getStyleClass().add("configureTierlist-btn-delete");
		this.getChildren().add(delete);
	}
}
