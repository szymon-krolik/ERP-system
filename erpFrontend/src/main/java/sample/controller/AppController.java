package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AppController implements Initializable {


    @FXML
    private Pane appPane;


    public AppController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadView();
    }

    //TODO obsluga wyjatkow
    private void loadView() {

        //wstrzykniecie widoku pracownikow do glownego widoku
        try {
            BorderPane borderPane = FXMLLoader.load(getClass().getResource("/fxml/employee.fxml"));
            appPane.getChildren().add(borderPane);
        } catch (IOException e) {

        }

    }


}
