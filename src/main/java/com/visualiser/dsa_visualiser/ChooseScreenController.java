package com.visualiser.dsa_visualiser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseScreenController {

    Stage stage;

    @FXML
    private void onLinkedListClick(ActionEvent e) throws IOException  {
        SceneSwitcher.switch_scene(e, stage, "LinkedList.fxml");
    }

    @FXML
    private void onStackClick(ActionEvent e) {

    }

    @FXML
    private void onQueueClick(ActionEvent e) {

    }

    @FXML
    private void onBinaryTreeClick(ActionEvent e) {

    }

    @FXML
    private void onBSTClick(ActionEvent e) {

    }
}
