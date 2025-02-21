package com.visualiser.dsa_visualiser;

import com.visualiser.data_structures.LinkedList;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class LinkedListController {

    private Stage stage;

    private LinkedList list = new LinkedList();

    @FXML
    private Canvas list_canvas;

    @FXML
    private TextField position_field, add_field;

    @FXML
    private void onAddClick() throws NumberFormatException {
        int value = parseInt(add_field.getText());
        list.addNode(value);
    }

    @FXML
    private void showList() {

    }

    @FXML
    private void onDeleteClick() throws Exception {
        int value = parseInt(position_field.getText());
        list.delete(value);
    }

    @FXML
    private void onInsertClick() {

    }

    @FXML
    private void onResetClick() {

    }

    //CHECK IF BACK BUTTON IS WORKING THEN START WORK ON THE LOGIC OF THE LINKED LIST
    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/data_structure_choose_screen.fxml");
    }
}
