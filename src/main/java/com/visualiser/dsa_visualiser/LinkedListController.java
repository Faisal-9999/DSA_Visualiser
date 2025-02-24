package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class LinkedListController {

    private Stage stage;

    private int currX = 100;
    private int currY = 100;
    private Boolean right = true;
    private ArrayList<StackPane> nodesList = new ArrayList<>();

    private int list_length = 0;

    @FXML
    private Pane link_panel;

    //TODO: ADD ARROWS BETWEEN NODES
    //TODO: ADD WORKING FOR OTHER LIST FUNCTIONALITY, DELETE, INSERT, and RESET
    //TODO: ADD WARNING MESSAGE TOO IF THE USER INPUTS MORE THAN THE PANE CAN HOLD

    @FXML
    private TextField position_field, add_field, insert_field;

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

        nodesList.add(node);

        if (list_length % 9 == 0) {
            currY += 125;
            right = !right;
        }
        else {
            if (right) {
                currX += 135;
            }
            else {
                currX -= 135;
            }
        }

        link_panel.getChildren().add(node);
    }

    @FXML
    private void onDeleteClick() throws NumberFormatException {
        int value = parseInt(position_field.getText());

        nodesList.remove(value - 1);

        link_panel.getChildren().clear();

        for (StackPane node : nodesList) {
            link_panel.getChildren().add(node);
        }
    }

    @FXML
    private void onInsertClick() throws NumberFormatException {
        int value = parseInt(insert_field.getText());
    }

    @FXML
    private void onResetClick() throws NumberFormatException {
        link_panel.getChildren().clear();
    }

    //CHECK IF BACK BUTTON IS WORKING THEN START WORK ON THE LOGIC OF THE LINKED LIST
    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/data_structure_choose_screen.fxml");
    }
}
