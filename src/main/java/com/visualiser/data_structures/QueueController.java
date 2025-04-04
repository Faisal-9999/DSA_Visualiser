package com.visualiser.data_structures;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.visualiser.miscellaneous.ErrorMessage.showErrorMessage;


public class QueueController {

    @FXML
    private Pane queue_panel;

    private int queueHeight = 0;
    private final int maxHeight = 30;
    private Stage stage;

    private final double startX = 600;
    private final double startY = 600;

    private final double NODE_WIDTH = 135;
    private final double NODE_HEIGHT = 20;

    private final ArrayList <StackPane> queue = new ArrayList<>();

    @FXML
    TextField push_value;

    @FXML
    private AnchorPane queue_screen;

    private StackPane createNode(int value) {
        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));
        return new StackPane(rectangle, label);
    }

    private void displayNodes() {

        queue_panel.getChildren().clear();

        for (int i = 0; i < queue.size(); i++) {
            StackPane element = queue.get(i);
            element.setLayoutX(startX);
            element.setLayoutY(startY - ((i + 1) * NODE_HEIGHT));
            queue_panel.getChildren().add(element);
        }

    }

    @FXML
    private void pop() {
        if (queueHeight <= 0) {
            showErrorMessage(queue_screen, stage, "QUEUE IS EMPTY", "Can't pop from an empty queue");
            return;
        }

        queue.removeFirst();
        displayNodes();
        queueHeight--;
    }

    @FXML
    private void push() throws NumberFormatException {
        if (queueHeight >= maxHeight) {
            showErrorMessage(queue_screen, stage,"MAX QUEUE HEIGHT REACHED", "Can't push more values, reached maximum queue height");
            return;
        }

        int pushed_val = Integer.parseInt(push_value.getText());

        queue.add(createNode(pushed_val));
        displayNodes();
        queueHeight++;
    }

    @FXML
    private void reset() {
        queue.clear();
        queue_panel.getChildren().clear();
        queueHeight = 0;
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.backDataStructure(e, stage);
    }
}
