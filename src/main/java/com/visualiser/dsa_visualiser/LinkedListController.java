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

    private Stage stage;

    private int currX = 100;
    private int currY = 100;
    private Boolean right = true;
    private final ArrayList<StackPane> nodesList = new ArrayList<>();

    private int list_length = 0;

    @FXML
    private Pane link_panel;

    //LEFT TO RIGHT NODE ADDITION IS WORKING

    //TODO: POLISH GUI
    /*TODO: FIX ARROW PLACEMENT FOR OTHER DIRECTIONS OTHER THAN LEFT TO RIGHT
        SUCH AS TOP TO BOTTOM AND RIGHT TO LEFT
    */


    @FXML
    private TextField position_field, add_field, insert_field, insert_value_field;

    @FXML
    private AnchorPane linked_list_screen;

    @FXML
    private void onAddClick() throws NumberFormatException {

        if (list_length >= 36) {
            showErrorMessage("Node Limit Reached", new String("Can't add more than " + 36 + " nodes"));
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
            currY += 125;
            right = !right;

            addArrowBetweenNodes(nodesList.size() - 2, nodesList.size() - 1);
        }
        else {
            if (right) {
                currX += 135;

                link_panel.getChildren().add(node);

                addArrowBetweenNodes(nodesList.size() - 2, nodesList.size() - 1);
            }
            else {
                currX -= 135;

                link_panel.getChildren().add(node);

                addArrowBetweenNodes(nodesList.size() - 2, nodesList.size() - 1);
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

    private void addArrow(String direction) {

        direction = direction.toLowerCase().replaceAll("\\s", "");

        StackPane lastNode = nodesList.getLast();

        double nodeWidth = lastNode.getWidth();
        double nodeHeight = lastNode.getHeight();
        double x = lastNode.getLayoutX();
        double y = lastNode.getLayoutY();

        double horizontalGap = 135;
        double verticalGap = 125;

        Arrow arrow = null;

        switch (direction) {
            case "left"  -> {
                arrow = new Arrow(x, y + nodeHeight / 2, x - horizontalGap, y);
            }
            case "right" -> {
                arrow = new Arrow(x + nodeWidth, y + nodeHeight / 2, x + horizontalGap, y);
            }
            case "down"  -> {
                arrow = new Arrow(x + nodeWidth / 2, y + nodeHeight, x, y + verticalGap);
            }
            default -> {
                showErrorMessage("Error While Adding Arrow", "Couldn't Generate Arrow Due To Incorrect Parameter");
            }
        }

        if (arrow != null) {
            link_panel.getChildren().add(arrow);
        }
    }

    private void addArrowBetweenNodes(int indexA, int indexB) {
        if (indexA < 0 || indexA >= nodesList.size() || indexB < 0 || indexB >= nodesList.size()) {
            return;
        }

        StackPane nodeA = nodesList.get(indexA);
        StackPane nodeB = nodesList.get(indexB);

        double nodeWidth = 75;
        double nodeHeight = 75;

        double xA = nodeA.getLayoutX() + nodeWidth;
        double yA = nodeA.getLayoutY() + nodeHeight / 2;

        double xB = nodeB.getLayoutX();
        double yB = nodeB.getLayoutY() + nodeHeight / 2;

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
