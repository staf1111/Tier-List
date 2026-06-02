package application.widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.TierM;

public class Tier extends HBox {

	private HBox itemsContainer = null;
	private HBox editContainer = null;

	private TextField nameController;
	private ColorPicker nameFill;
	private ColorPicker background;

	private Button up;
	private Button down;

	private Runnable onUp;
	private Runnable onDown;
	private Runnable onDelete;

	public Tier(TierM model, TierType type) {

		this.setPrefHeight(model.getHeight());
		this.setPrefWidth(model.getWidth());
		this.setMaxWidth(Double.MAX_VALUE);
		this.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

		this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

		if (model.getBackColor().toString().equalsIgnoreCase("0xffffffff")) {
			this.setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		} else {
			this.setBorder(new Border(new BorderStroke(model.getBackColor(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					BorderWidths.DEFAULT)));
		}

		StackPane labelPane = new StackPane();
		double squareSize = model.getHeight();

		labelPane.setPrefWidth(squareSize);
		labelPane.setPrefHeight(squareSize);
		labelPane.setMinWidth(squareSize);
		labelPane.setMinHeight(squareSize);
		labelPane.setMaxWidth(squareSize);
		labelPane.setMaxHeight(squareSize);

		labelPane.setBackground(
				new Background(new BackgroundFill(model.getBackColor(), CornerRadii.EMPTY, Insets.EMPTY)));

		labelPane.setAlignment(javafx.geometry.Pos.CENTER);

		Label text = new Label(model.getName());
		text.setTextFill(model.getNameColor());

		labelPane.getChildren().add(text);

		if (type == TierType.PLAY) {

			this.itemsContainer = new HBox();
			this.itemsContainer.setSpacing(5);

			HBox.setHgrow(itemsContainer, Priority.ALWAYS);

			this.borderProperty().set(new Border(new BorderStroke(model.getBackColor(), BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			// drop
			this.itemsContainer.setOnDragOver(e -> {
				if (e.getGestureSource() != itemsContainer && e.getDragboard().hasString()
						&& "ITEM_DRAG".equals(e.getDragboard().getString())) {
					e.acceptTransferModes(TransferMode.MOVE);
				}
				e.consume();
			});

			this.itemsContainer.setOnDragDropped(e -> {

				Dragboard db = e.getDragboard();

				if (db.hasString() && "ITEM_DRAG".equals(db.getString())) {

					javafx.scene.Node sourceNode = (javafx.scene.Node) e.getGestureSource();

					// 🔥 on remonte jusqu'au vrai Items
					while (sourceNode != null && !(sourceNode instanceof Items)) {
						sourceNode = sourceNode.getParent();
					}

					if (sourceNode instanceof Items draggedItem) {

						if (draggedItem.getParent() instanceof HBox oldParent) {
							oldParent.getChildren().remove(draggedItem);
						}

						itemsContainer.getChildren().add(draggedItem);

						e.setDropCompleted(true);
					} else {
						e.setDropCompleted(false);
					}

				} else {
					e.setDropCompleted(false);
				}

				e.consume();
			});

			this.getChildren().addAll(labelPane, itemsContainer);
		}

		else if (type == TierType.EDIT) {

			this.editContainer = new HBox();
			HBox.setHgrow(editContainer, Priority.ALWAYS);
			editContainer.setSpacing(10);
			editContainer.setAlignment(Pos.CENTER_LEFT);

			Label nameL = new Label("Nom :");
			nameL.getStyleClass().add("configureTierlist-label-tier");
			Label nomColorL = new Label("couleur texte :");
			nomColorL.getStyleClass().add("configureTierlist-label-tier");
			Label backgroundL = new Label("Arrière plan :");
			backgroundL.getStyleClass().add("configureTierlist-label-tier");

			nameController = new TextField();
			nameController.setText(model.getName());

			text.textProperty().bind(nameController.textProperty());

			nameController.textProperty().addListener((observable, oldName, newName) -> {
				model.setName(newName);
			});

			nameFill = new ColorPicker();
			nameFill.setValue(model.getNameColor());
			nameFill.setMaxWidth(50);

			background = new ColorPicker();
			background.setValue(model.getBackColor());
			background.setMaxWidth(50);

			nameFill.valueProperty().addListener((obs, o, n) -> {
				if (n != null) {
					text.setTextFill(n);
					model.setNameColor(n);
				}
			});

			background.valueProperty().addListener((obs, o, n) -> {
				if (n != null) {
					labelPane.setBackground(new Background(new BackgroundFill(n, null, null)));
					model.setBackColor(n);
				}
			});

			VBox buttonLayout = new VBox();
			buttonLayout.setAlignment(Pos.CENTER);
			buttonLayout.setSpacing(2);
			
			up = new Button("▲");
			down = new Button("▼");

			up.setOnAction(e -> {
				if (this.onUp != null)
					this.onUp.run();
			});

			down.setOnAction(e -> {
				if (this.onDown != null)
					this.onDown.run();
			});

			buttonLayout.getChildren().addAll(up, down);

			Button delete = new Button("Delete");

			delete.setOnAction(e -> {
				if (this.onDelete != null)
					this.onDelete.run();
			});

			up.getStyleClass().add("configureTierlist-btn-nav");
			down.getStyleClass().add("configureTierlist-btn-nav");
			delete.getStyleClass().add("configureTierlist-btn-delete");
			
			this.editContainer.getChildren().addAll(nameL, nameController, nomColorL, nameFill, backgroundL, background, buttonLayout, delete);

			this.getChildren().addAll(labelPane, editContainer);
		}
	}

	public void setWitdh(double w) {
		this.setWidth(w);
	}

	public void setOnUp(Runnable onUp) {
		this.onUp = onUp;
	}

	public void setOnDown(Runnable onDown) {
		this.onDown = onDown;
	}

	public void setOnDelete(Runnable onDelete) {
		this.onDelete = onDelete;
	}
}