package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

//TODO: ADD GUI ELEMENTS TO tree.fxml and connect with this backend class also add the BFS search below
//TODO: FIND A WAY TO DISPLAY THE NODES PROPERLY WITH NO COLLISIONS

public class TreeController {

    private final int NODE_RADIUS = 35;
    private Stage stage;
    private Boolean isFirst = true;

    private final double startX = 625;
    private final double startY = 50;


    Tree tree = new Tree();

    @FXML
    Pane Tree_panel;

    @FXML
    AnchorPane Tree_screen;

    @FXML
    TextField delete_field, add_field;

    @FXML
    private void onAddClick() {
        int value = Integer.parseInt(add_field.getText());
        StackPane node = createNode(value);

        if(isFirst) {
            node.setLayoutX(startX);
            node.setLayoutY(startY);

            tree.addNode(node);
            Tree_panel.getChildren().add(node);

            isFirst = false;
            return;
        }

        tree.addNode(node);

        //TODO: FIND A WAY TO RETURN A REFERENCE TO THE PARENT NODE AND PLACE THE NEW NODE DIAGONALLY RELATIVE TO THAT
        //TODO: DEPENDING ON IF ITS PLACED ON THE LEFT OR RIGHT OF THE PARENT



    }

    private void displayNodes() {

    }

    @FXML
    private void onDeleteClick() {

    }

    @FXML
    private void onResetClick() {
        Tree_panel.getChildren().clear();
        isFirst = true;
        tree = new Tree();
    }

    private StackPane createNode(int value) {
        Circle circle = new Circle(NODE_RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));
        return new StackPane(circle, label);
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/data_structure_choose_screen.fxml");
    }
}
