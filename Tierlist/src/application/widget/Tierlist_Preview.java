package application.widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import model.TierList_previewM;
import services.data.Settings;
import services.screenManager.Manager;
import services.screenManager.PageType;

public class Tierlist_Preview extends VBox{
	
	private static final float DEFAULT_HEIGHT = 250;
	private static final float DEFAULT_WITDH = 225;

	
	public Tierlist_Preview(TierList_previewM model)
	{
		this(model, DEFAULT_WITDH, DEFAULT_HEIGHT);
	}
	
	public Tierlist_Preview(TierList_previewM model, float witdh, float height)
	{
		this.setPrefWidth(witdh);
		this.setPrefHeight(height);
		
		this.getStyleClass().add("tier-card");
		
		
		VBox container = new VBox();

		Image image = new Image(getClass().getResourceAsStream("/assets/tierlistIllustration.png"));

		
		ImageView view = new ImageView(image); // on l'affiche
		view.setFitWidth(witdh);
		view.setFitHeight(height * 0.75);
		
		Rectangle clip = new Rectangle(witdh, height * 0.75);
		clip.setArcWidth(12); clip.setArcHeight(12);
		view.setClip(clip);
		HBox ButtonController = new HBox();
		
		//Bouton pour jouer,
		Button playButton = new Button();
		playButton.setPrefWidth(40);
		playButton.setPrefHeight(40);
		playButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
		
		ImageView playContent = new ImageView(new Image(getClass().getResourceAsStream("/assets/iconPlay.png")));
		playContent.setFitWidth(playButton.getPrefWidth());
		playContent.setFitHeight(playButton.getPrefHeight());
		playContent.setPreserveRatio(true);
		
		playButton.setGraphic(playContent);
		
		playButton.setOnAction(e->{
			Settings.getInstance().setCurrentTierList_play(model.getTierlist());
			Manager.getInstance().setPage(PageType.PLAY);
		});
		
		
		//bouton pour editer la tierlist
		Button editButton = new Button();
		editButton.setPrefWidth(40);
		editButton.setPrefHeight(40);
		editButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
		
		ImageView editContent = new ImageView(new Image(getClass().getResourceAsStream("/assets/edit.png")));
		editContent.setFitWidth(editButton.getPrefWidth());
		editContent.setFitHeight(editButton.getPrefHeight());
		editContent.setPreserveRatio(true);
		
		editButton.setGraphic(editContent);
		
		editButton.setOnAction(e->{
			Settings.getInstance().setCurrentTierList_edit(model.getTierlist());
			Manager.getInstance().setPage(PageType.CREATETIERLIST);
		});
		//bouton pour dupliquer
		Button duplButton = new Button();
		duplButton.setPrefWidth(40);
		duplButton.setPrefHeight(40);
		duplButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
		
		
		ImageView duplContent = new ImageView(new Image(getClass().getResourceAsStream("/assets/dupliquer.png")));
		duplContent.setFitWidth(duplButton.getPrefWidth());
		duplContent.setFitHeight(duplButton.getPrefHeight());
		duplContent.setPreserveRatio(true);
		
		duplButton.setGraphic(duplContent);
		
		duplButton.setOnAction((e)->{
			//TODO
		});
		
		//bouton pour supprimer
		Button delButton = new Button();
		delButton.setPrefWidth(40);
		delButton.setPrefHeight(40);
		delButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
		
		
		ImageView delContent = new ImageView(new Image(getClass().getResourceAsStream("/assets/delete.png")));
		delContent.setFitWidth(delButton.getPrefWidth());
		delContent.setFitHeight(delButton.getPrefHeight());
		delContent.setPreserveRatio(true);
		
		delButton.setGraphic(delContent);
		
		delButton.setOnAction((e)->{
			//TODO
		});
		ButtonController.setSpacing(witdh / 16);
		ButtonController.setAlignment(Pos.CENTER);
		container.setMargin(ButtonController, new Insets(10,0,0,0));
		
		ButtonController.getChildren().addAll(playButton,editButton, duplButton, delButton);
		container.getChildren().addAll(view, ButtonController);
		this.getChildren().add(container);
	}
}
