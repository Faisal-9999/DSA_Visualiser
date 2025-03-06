package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class StackController {

    @FXML
    private Pane stack_panel;

    private int stackHeight = 0;
    private final int maxHeight = 99;
    private Stage stage;


    private final double NODE_WIDTH = 135;
    private final double NODE_HEIGHT = 20;

    private final ArrayList <StackPane> stack = new ArrayList<>();

    //FRAMEWORK FOR STACK HAS BEEN CREATED
    //QUEUE WILL BE JUST LIKE THIS JUST WITH SOME MODIFICATIONS

    //TODO: COMPLETE PUSH AND POP METHOD
    //TODO: MAKE THE NODES STACK ON TOP OF EACH OTHER
    //TODO: DISPLAY THE NODES


    @FXML
    TextField push_value;

    @FXML
    private AnchorPane stack_screen;

    private StackPane createNode(int value) {
        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));
        return new StackPane(rectangle, label);
    }

    @FXML
    private void pop() {
        if (stackHeight <= 0) {
            showErrorMessage("STACK IS EMPTY", "Can't pop from an empty stack");
            return;
        }

        stack.removeLast();
        stackHeight--;
    }

    @FXML
    private void push() throws NumberFormatException {
        if (stackHeight >= maxHeight) {
            showErrorMessage("MAX STACK HEIGHT REACHED", "Can't push more values, reached maximum stack height");
            return;
        }

        int pushed_val = Integer.parseInt(push_value.getText());

        stack_panel.getChildren().add(createNode(pushed_val));
        stackHeight++;
    }

    private void showErrorMessage(String headerText, String bodyText) {
        stage = (Stage) stack_screen.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.ERROR, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setHeaderText(headerText);
        alert.getDialogPane().setContentText(bodyText);
        alert.showAndWait();
    }

    @FXML
    private void reset() {
        stack.clear();
        stack_panel.getChildren().clear();
        stackHeight = 0;
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/data_structure_choose_screen.fxml");
    }
}
