package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.Arrow;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class LinkedListController {

    //LEFT TO RIGHT NODE ADDITION IS WORKING

    //TODO: POLISH GUI
    /*TODO: FIX ARROW PLACEMENT FOR OTHER DIRECTIONS OTHER THAN LEFT TO RIGHT
        SUCH AS TOP TO BOTTOM AND RIGHT TO LEFT
    */


    private final double NODE_HEIGHT = 75;
    private final double NODE_WIDTH = 75;
    private final double HORIZONTAL_GAP = 135;
    private final double VERTICAL_GAP = 125;

    private Stage stage;

    private double currX = 100;
    private double currY = 100;
    private Boolean right = true;
    private final ArrayList<StackPane> nodesList = new ArrayList<>();

    private int list_length = 0;

    @FXML
    private Pane link_panel;

    @FXML
    private TextField position_field, add_field, insert_field, insert_value_field;

    @FXML
    private AnchorPane linked_list_screen;

    @FXML
    private void onAddClick() throws NumberFormatException {

        int MAX_NODES = 36;

        if (list_length >= MAX_NODES) {
            showErrorMessage("Node Limit Reached", "Can't add more than " + MAX_NODES + " nodes");
            return;
        }

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
            currY += VERTICAL_GAP;
            right = !right;

            addArrowBetweenNodes(nodesList.size() - 2, nodesList.size() - 1, "down");
        }
        else {
            if (right) {
                currX += HORIZONTAL_GAP;

                link_panel.getChildren().add(node);

                addArrowBetweenNodes(nodesList.size() - 2, nodesList.size() - 1, "right");
            }
            else {
                currX -= HORIZONTAL_GAP;

                link_panel.getChildren().add(node);

                addArrowBetweenNodes(nodesList.size() - 2, nodesList.size() - 1, "left");
            }
        }
    }

    @FXML
    private void onDeleteClick() throws NumberFormatException {

        if (list_length <= 0) {
            showErrorMessage("List Already Empty", "Can't delete modes in an empty list");
            return;
        }

        int value = parseInt(position_field.getText());

        nodesList.remove(value - 1);

        list_length--;

        link_panel.getChildren().clear();

        for (StackPane node : nodesList) {
            link_panel.getChildren().add(node);
        }
    }

    @FXML
    private void onInsertClick() throws NumberFormatException {
        int value = parseInt(insert_value_field.getText());
        int position = parseInt(insert_field.getText());

        list_length++;

        Rectangle rectangle = new Rectangle(75, 75);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));

        StackPane node = new StackPane(rectangle, label);

        nodesList.add(position - 1, node);


        currX = 100;
        currY = 100;
        list_length = 0;
        right = true;

        link_panel.getChildren().clear();

        for (StackPane linknode : nodesList) {
            linknode.setLayoutX(currX);
            linknode.setLayoutY(currY);

            list_length++;

            if (list_length % 9 == 0) {
                currY += VERTICAL_GAP;
                right = !right;
            }
            else {
                if (right) {
                    currX += HORIZONTAL_GAP;
                }
                else {
                    currX -= HORIZONTAL_GAP;
                }
            }

            link_panel.getChildren().add(linknode);
        }
    }

    @FXML
    private void onResetClick() throws NumberFormatException {
        link_panel.getChildren().clear();
        list_length = 0;
        currX = 100;
        currY = 100;
    }

    //CHECK IF BACK BUTTON IS WORKING THEN START WORK ON THE LOGIC OF THE LINKED LIST
    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/data_structure_choose_screen.fxml");
    }

    private void addArrowBetweenNodes(int indexA, int indexB, String direction) {
        if (indexA < 0 || indexA >= nodesList.size() || indexB < 0 || indexB >= nodesList.size()) {
            return;
        }

        StackPane nodeA = nodesList.get(indexA);
        StackPane nodeB = nodesList.get(indexB);

        double xA, yA, xB, yB;

        //TODO: FIX THE LOGIC FOR THE LEFT CASE
        //TODO: SAME FOR RIGHT CASE

        switch (direction) {
            case "right" -> {
                xA = nodeA.getLayoutX() + NODE_WIDTH;
                yA = nodeA.getLayoutY() + NODE_HEIGHT / 2;

                xB = nodeB.getLayoutX();
                yB = nodeB.getLayoutY() + NODE_HEIGHT / 2;
            }
            case "left" -> {
                xA = nodeA.getLayoutX() - NODE_WIDTH;
                yA = nodeA.getLayoutY() + NODE_HEIGHT / 2;

                xB = nodeB.getLayoutX() + NODE_WIDTH;
                yB = nodeB.getLayoutY() + NODE_HEIGHT / 2;
            }
            case "down" -> {
                xA = nodeA.getLayoutX() + NODE_WIDTH / 2;
                yA = nodeA.getLayoutY() - NODE_HEIGHT;

                xB = nodeB.getLayoutX() + NODE_WIDTH / 2;
                yB = nodeB.getLayoutY();
            }
            default -> {
                return;
            }
        }

        Arrow arrow = new Arrow(xA, yA, xB, yB);

        link_panel.getChildren().add(arrow);
    }

    private void showErrorMessage(String headerText, String bodyText) {
        stage = (Stage) linked_list_screen.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.ERROR;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setHeaderText(headerText);
        alert.getDialogPane().setContentText(bodyText);

        alert.showAndWait();
    }
}
