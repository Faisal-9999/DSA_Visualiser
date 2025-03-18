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

import static com.visualiser.miscellaneous.ErrorMessage.showErrorMessage;

public class StackController {

    @FXML
    private Pane stack_panel;

    private int stackHeight = 0;
    private final int maxHeight = 30;
    private Stage stage;

    private final double startX = 600;
    private final double startY = 600;

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

    private void displayNodes() {

        stack_panel.getChildren().clear();

        for (int i = 0; i < stack.size(); i++) {
            StackPane element = stack.get(i);
            element.setLayoutX(startX);
            element.setLayoutY(startY - ((i + 1) * NODE_HEIGHT));
            stack_panel.getChildren().add(element);
        }

    }

    @FXML
    private void pop() {
        if (stackHeight <= 0) {
            showErrorMessage(stack_screen, stage, "STACK IS EMPTY", "Can't pop from an empty stack");
            return;
        }

        stack.removeLast();
        displayNodes();
        stackHeight--;
    }

    @FXML
    private void push() throws NumberFormatException {
        if (stackHeight >= maxHeight) {
            showErrorMessage(stack_screen, stage,"MAX STACK HEIGHT REACHED", "Can't push more values, reached maximum stack height");
            return;
        }

        int pushed_val = Integer.parseInt(push_value.getText());

        stack.add(createNode(pushed_val));
        displayNodes();
        stackHeight++;
    }

    @FXML
    private void reset() {
        stack.clear();
        stack_panel.getChildren().clear();
        stackHeight = 0;
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.back(e, stage);
    }
}
