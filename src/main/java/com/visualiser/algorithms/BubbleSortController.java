package com.visualiser.algorithms;

import com.visualiser.miscellaneous.ErrorMessage;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.fxml.FXML;
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
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class BubbleSortController {

    private ArrayList<Integer> elements = null;

    private BubbleSorter sorter = null;

    @FXML
    private Stage stage;

    //TODO MAKE SHIT APPEAR ON SCREEN

    private boolean running = true;

    private final double NODE_WIDTH = 75;
    private final double NODE_HEIGHT = 75;

    @FXML
    private AnchorPane bubble_screen;

    @FXML
    private Pane bubble_panel;

    @FXML
    TextField elementsNumberField;

    @FXML
    void onNumberOfElementsClick() throws InterruptedException {

        Integer numOfElements = null;

        try {
            numOfElements = Integer.parseInt(elementsNumberField.getText());
        }
        catch (Exception e) {
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

                if (result.isPresent()) {
                    try {
                        int element = Integer.parseInt(result.get().trim());
                        elements.add(element);
                        validInput = true;
                    } catch (NumberFormatException ex) {
                        ErrorMessage.showErrorMessage(bubble_screen, stage, "Invalid Element", "Please enter a valid integer.");
                    }
                } else
                    return;
            }
        }

        start();
    }

    private void start() throws InterruptedException {
        sorter = BubbleSorter.bubbleSorterBuilder(elements);
        sorter.start();

        while (running) {

            sorter.join();
            Thread.sleep(550);

            elements = new ArrayList<>();
            elements.addAll(sorter.getElements());
            printArray();
        }
    }

    private void printArray() {
        double startX = 350;
        double startY = 500;

        for (int i = 0; i < elements.size(); i++) {
            StackPane node = createNode(elements.indexOf(i));
            node.setLayoutY(startY);
            node.setLayoutX(startX + (NODE_WIDTH * i));
            bubble_panel.getChildren().add(node);
        }
    }

    private StackPane createNode(int value) {
        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));
        return new StackPane(rectangle, label);
    }

    @FXML
    void onBackClick(ActionEvent e) {
        try {
            SceneSwitcher.backAlgorithms(e, stage);
        }
        catch (IOException ex) {
            ErrorMessage.showErrorMessage(bubble_screen, stage, "Error Backing", String.valueOf(ex));
        }
    }

}
