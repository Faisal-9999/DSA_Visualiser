package com.visualiser.miscellaneous;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorMessage {
    public static void showErrorMessage(AnchorPane panel, Stage stage, String headerText, String bodyText) {
        stage = (Stage) panel.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.ERROR, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setHeaderText(headerText);
        alert.getDialogPane().setContentText(bodyText);
        alert.showAndWait();
    }
}
