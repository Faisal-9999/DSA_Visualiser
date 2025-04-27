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

public class SelectionSortController {

    private ArrayList<Integer> elements = null;
    private final double NODE_WIDTH = 75;
    private final double NODE_HEIGHT = 75;

    @FXML
    private Stage stage;

    @FXML
    private AnchorPane selection_screen;

    @FXML
    private Pane selection_panel;

    @FXML
    private TextField elementsNumberField;

    @FXML
    private Label icon;

    @FXML
    private Label spaceText;

    @FXML
    private Label timeText;


    @FXML
    private Label writeableArea;

    public void initialize() {
        spaceText.setVisible(false);
        timeText.setVisible(false);

        icon.setOnMouseEntered(e -> {
            timeText.setVisible(true);
            spaceText.setVisible(true);
        });

        icon.setOnMouseExited(event -> {
            timeText.setVisible(false);
            spaceText.setVisible(false);
        });
    }


    @FXML
    private void onNumberOfElementsClick() {

        Integer numOfElements = null;

        try {
            numOfElements = Integer.parseInt(elementsNumberField.getText());
        } catch (Exception e) {
            ErrorMessage.showErrorMessage(selection_screen, stage, "Invalid Data Type", "Argument can only be an integer");
            return;
        }

        if (numOfElements > 10 || numOfElements <= 0) {
            ErrorMessage.showErrorMessage(selection_screen, stage, "Invalid Number Of Elements", "Number of elements can only be from 1 - 10");
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
                    ErrorMessage.showErrorMessage(selection_screen, stage, "Invalid Element", "Please enter a valid integer.");
                }
            }
        }
        start();
    }

    private void start() {
        new Thread(() -> {
            Platform.runLater(() -> {
                writeableArea.setText("Starting Selection Sort");
                drawElements();
            });
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Platform.runLater(() -> ErrorMessage.showErrorMessage(selection_screen, stage, "Error Starting", String.valueOf(e)));
                return;
            }

            //TODO: FIX THIS SHIT NIGGA
            //TODO: THIS ALGORITHM DUN FUCKED

            for (int i = 0; i < elements.size() - 1; i++) {

                int smallest = i;

                for (int j = i + 1; j < elements.size(); j++) {
                    if (elements.get(j) < elements.get(smallest)) {
                        smallest = j;
                    }
                }

                int temp = elements.get(i);
                elements.set(i, elements.get(smallest));
                elements.set(smallest, temp);

                int finalSmallest = smallest;
                int finalI = i;
                Platform.runLater(() ->writeableArea.setText("Swapped " + elements.get(finalI) + " with " + elements.get(finalSmallest)));

                Platform.runLater(this::drawElements);

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Platform.runLater(() -> ErrorMessage.showErrorMessage(selection_screen, stage, "Thread Error", String.valueOf(e)));
                    return;
                }

                boolean sorted = true;

                for (int k = 0; k < elements.size() - 1; k++) {
                    if (elements.get(k + 1) < elements.get(k)) {
                        sorted = false;
                        break;
                    }
                }

                if (sorted) {
                    Platform.runLater(() -> writeableArea.setText("Sorting Completed"));
                    return;
                }
            }
            Platform.runLater(() -> writeableArea.setText("Sorting Completed"));
        }).start();
    }

    private void drawElements() {

        selection_panel.getChildren().clear();

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
            selection_panel.getChildren().add(node);
        }
    }


    @FXML
    private void onResetClick() {
        selection_panel.getChildren().clear();
        elements = new ArrayList<>();
        writeableArea.setText("");
    }


    @FXML
    private void onBackClick(ActionEvent e) {
        try {
            SceneSwitcher.backAlgorithms(e, stage);
        } catch (IOException ex) {
            ErrorMessage.showErrorMessage(selection_screen, stage, "Error Backing", String.valueOf(ex));
        }
    }

}
