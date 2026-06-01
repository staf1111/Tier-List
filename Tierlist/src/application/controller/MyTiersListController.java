package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.screenManager.Manager;
import services.screenManager.PageType;

public class MyTiersListController {

    @FXML
    private HBox accueil, mestierlist, importer, parametres, aide;
    @FXML
    private Button newtierlist, imptierlist;

    @FXML
    private void ClickAccueil(MouseEvent event) throws IOException {
        Manager.getInstance().setPage(PageType.HOME);
    }

    @FXML
    private void ClickMesTierList(MouseEvent event) throws IOException {
        Manager.getInstance().setPage(PageType.MYTIERLIST);
    }

    @FXML
    private void ClickImporter(MouseEvent event) throws IOException {
        //TODO
    }

    @FXML
    private void ClickParametres(MouseEvent event) throws IOException {
        //TODO
    }

    @FXML
    private void ClickAide(MouseEvent event) throws IOException {
        //TODO
    }

    @FXML
    private void ClickNouvelleTierList(ActionEvent event) throws IOException {
        Manager.getInstance().setPage(PageType.CREATETIERLIST);
    }

    @FXML
    private void ClickImporterTierList(ActionEvent event) throws IOException {
        //TODO
    }
}