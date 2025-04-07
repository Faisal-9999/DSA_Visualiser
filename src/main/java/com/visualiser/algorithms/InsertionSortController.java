package com.visualiser.algorithms;

import com.visualiser.miscellaneous.ErrorMessage;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
import java.util.Optional;

public class InsertionSortController {

    private ArrayList<Integer> elements = null;
    private final double NODE_WIDTH = 75;
    private final double NODE_HEIGHT = 75;

    @FXML
    private Stage stage;

    @FXML
    private AnchorPane bubble_screen;

    @FXML
    private Pane bubble_panel;

    @FXML
    private TextField elementsNumberField;

    @FXML
    private Label writeableArea;

    //TODO: MAKE CHANGES TO THE SORTING ALGORITHM TO MATCH INSERTION SORT

    @FXML
    private void onNumberOfElementsClick() {
        Integer numOfElements;
        try {
            numOfElements = Integer.parseInt(elementsNumberField.getText());
        } catch (Exception e) {
            ErrorMessage.showErrorMessage(bubble_screen, stage, "Invalid Data Type", "Argument can only be an integer");
            return;
        }
        elements = new ArrayList<>();
        for (int i = 0; i < numOfElements; i++) {
            boolean validInput = false;
            while (!validInput) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Element Input");
                dialog.setHeaderText("Enter element #" + (i + 1));
                dialog.setContentText("Please enter an integer:");
                Optional<String> result = dialog.showAndWait();
                if (result.isEmpty()) {
                    return;
                }
                try {
                    int element = Integer.parseInt(result.get().trim());
                    elements.add(element);
                    validInput = true;
                } catch (NumberFormatException ex) {
                    ErrorMessage.showErrorMessage(bubble_screen, stage, "Invalid Element", "Please enter a valid integer.");
                }
            }
        }
        start();
    }

    private void start() {
        new Thread(() -> {
            Platform.runLater(() -> {
                writeableArea.setText("Starting Bubble Sort");
                drawElements();
            });
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Platform.runLater(() -> ErrorMessage.showErrorMessage(bubble_screen, stage, "Error Starting", String.valueOf(e)));
                return;
            }
            for (int i = 0; i < elements.size() - 1; i++) {
                for (int j = 0; j < elements.size() - 1 - i; j++) {
                    if (elements.get(j) > elements.get(j + 1)) {
                        int firstValue = elements.get(j);
                        int secondValue = elements.get(j + 1);
                        int temp = firstValue;
                        elements.set(j, secondValue);
                        elements.set(j + 1, temp);
                        Platform.runLater(() -> writeableArea.setText("Swapped " + firstValue + " with " + secondValue));
                    } else {
                        int finalJ = j;
                        int finalJ1 = j;
                        Platform.runLater(() -> writeableArea.setText("No swap needed for indices " + finalJ + " and " + (finalJ1 + 1)));
                    }
                    Platform.runLater(this::drawElements);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        Platform.runLater(() -> ErrorMessage.showErrorMessage(bubble_screen, stage, "Thread Error", String.valueOf(e)));
                        return;
                    }
                }
            }
            Platform.runLater(() -> writeableArea.setText("Sorting Completed"));
        }).start();
    }

    private void drawElements() {
        bubble_panel.getChildren().clear();
        for (int i = 0; i < elements.size(); i++) {
            int value = elements.get(i);
            StackPane node = new StackPane();
            Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
            rectangle.setFill(Color.CORNFLOWERBLUE);
            rectangle.setArcHeight(10);
            rectangle.setArcWidth(10);
            Text number = new Text(String.valueOf(value));
            number.setFont(Font.font(20));
            number.setFill(Color.WHITE);
            node.getChildren().addAll(rectangle, number);
            node.setLayoutX(450 + (i * (NODE_WIDTH + 10)));
            node.setLayoutY(300);
            bubble_panel.getChildren().add(node);
        }
    }

    @FXML
    private void onResetClick() {
        bubble_panel.getChildren().clear();
        elements = new ArrayList<>();
        writeableArea.setText("");
    }

    @FXML
    private void onBackClick(ActionEvent e) {
        try {
            SceneSwitcher.backAlgorithms(e, stage);
        } catch (IOException ex) {
            ErrorMessage.showErrorMessage(bubble_screen, stage, "Error Backing", String.valueOf(ex));
        }
    }
}
