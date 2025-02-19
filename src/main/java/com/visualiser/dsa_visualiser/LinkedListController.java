package com.visualiser.dsa_visualiser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class LinkedListController {

    private Stage stage;

    private List list = new List();

    @FXML
    private void onAddClick() {

    }

    @FXML
    private void onDeleteClick() {

    }

    @FXML
    private void onResetClick() {

    }

    //CHECK IF BACK BUTTON IS WORKING THEN START WORK ON THE LOGIC OF THE LINKED LIST
    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "data_structure_choose_screen.fxml");
    }
}
