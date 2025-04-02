package com.visualiser.algorithms;

import com.visualiser.miscellaneous.ErrorMessage;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class BubbleSortController {

    private ArrayList<Integer> elements = null;

    private BubbleSorter sorter = null;

    private Stage stage;
    @FXML
    private AnchorPane bubble_screen;

    @FXML
    private Pane bubble_panel;

    @FXML
    TextField elementsNumberField;

    @FXML
    void onNumberOfElementsClick() {

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
                dialog.setHeaderText("Enter element " + (i + 1) + " of " + numOfElements);
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
                } else {
                    ErrorMessage.showErrorMessage(bubble_screen, stage, "Input Interrupted", "");
                    return;
                }
            }

        }

    }

    void start() {
        sorter = BubbleSorter.bubbleSorterBuilder(elements);
        sorter.start();
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
