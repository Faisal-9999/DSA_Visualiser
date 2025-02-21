package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.io.IOException;

import static java.lang.Integer.parseInt;

public class LinkedListController {

    private Stage stage;

    private int currX = 100;
    private int currY = 100;
    private Boolean right = true;

    private int list_length = 0;

    @FXML
    private Pane link_panel;

    //THE PANEL CLASS IS FUCKED YOU USED THE awt one find a way to redo it using the JavaFX panel
    //TODO: ADD WORKING FOR GRAPHICAL REPRESENTATION OF NODES IN LINKED LIST

    @FXML
    private TextField position_field, add_field;

    @FXML
    private void onAddClick() throws NumberFormatException {
        int value = parseInt(add_field.getText());

        Rectangle rectangle = new Rectangle(75, 75);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));

        StackPane node = new StackPane(rectangle, label);

        node.setLayoutX(currX);
        node.setLayoutY(currY);

        list_length++;

        if (list_length % 9 == 0) {
            currY += 125;
            right = !right;
        }
        else {
            if (right) {
                currX += 125;
            }
            else {
                currX -= 125;
            }
        }

        link_panel.getChildren().add(node);
    }

    @FXML
    private void onDeleteClick() throws Exception {
        int value = parseInt(position_field.getText());
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
