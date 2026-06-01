package application.controller.modal;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private HBox accueil, mestierlist, importer, parametres, aide;
    @FXML
    private Button newtierlist, imptierlist;

    private void changeScene(String fxml, Node source) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));

        Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void ClickAccueil(MouseEvent event) throws IOException {
        changeScene("/fxml/home.fxml", (Node) event.getSource());
    }

    @FXML
    private void ClickMesTierList(MouseEvent event) throws IOException {
        changeScene("/fxml/mesTiersLists.fxml", (Node) event.getSource());
    }

    @FXML
    private void ClickImporter(MouseEvent event) throws IOException {
        changeScene("/fxml/importer.fxml", (Node) event.getSource());
    }

    @FXML
    private void ClickParametres(MouseEvent event) throws IOException {
        changeScene("/fxml/parametres.fxml", (Node) event.getSource());
    }

    @FXML
    private void ClickAide(MouseEvent event) throws IOException {
        changeScene("/fxml/aide.fxml", (Node) event.getSource());
    }

    @FXML
    private void ClickNouvelleTierList(ActionEvent event) throws IOException {
        changeScene("/fxml/createTierList.fxml", (Node) event.getSource());
    }

    @FXML
    private void ClickImporterTierList(ActionEvent event) throws IOException {
        changeScene("/fxml/importer.fxml", (Node) event.getSource());
    }
}
