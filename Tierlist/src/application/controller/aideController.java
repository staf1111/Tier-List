package application.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import services.screenManager.Manager;
import services.screenManager.PageType;

public class aideController {

    @FXML
    private Button Themes;

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
        // TODO : import
    }

    @FXML
    private void ClickParametres(MouseEvent event) throws IOException {
        // TODO : paramètres
    }

    @FXML
    private void ClickAide(MouseEvent event) throws IOException {
       
    }
}