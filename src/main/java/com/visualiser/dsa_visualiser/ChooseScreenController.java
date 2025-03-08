package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseScreenController {

    Stage stage;

    @FXML
    private void onLinkedListClick(ActionEvent e) throws IOException  {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/LinkedList.fxml");
    }

    @FXML
    private void onStackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/Stack.fxml");
    }

    @FXML
    private void onQueueClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/queue.fxml");
    }

    @FXML
    private void onBinaryTreeClick(ActionEvent e) {

    }

    @FXML
    private void onBSTClick(ActionEvent e) {

    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/main_menu.fxml");
    }
}
