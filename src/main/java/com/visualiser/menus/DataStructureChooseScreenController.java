package com.visualiser.menus;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class DataStructureChooseScreenController {

    Stage stage;

    @FXML
    private void onLinkedListClick(ActionEvent e) throws IOException  {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/LinkedList.fxml");
    }

    @FXML
    private void onStackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/Stack.fxml");
    }

    @FXML
    private void onQueueClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/queue.fxml");
    }

    @FXML
    private void onBinaryTreeClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/tree.fxml");
    }

    @FXML
    private void onBSTClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/BinarySearchTree.fxml");
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/data_structureAndalgorithmChooseScreen.fxml");
    }

    @FXML
    private void onHashMapClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/hashmap.fxml");
    }
}
