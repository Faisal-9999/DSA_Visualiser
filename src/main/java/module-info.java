module com.visualiser.dsa_visualiser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.visualiser.dsa_visualiser to javafx.fxml;
    exports com.visualiser.dsa_visualiser;
    exports com.visualiser.miscellaneous;
    opens com.visualiser.miscellaneous to javafx.fxml;
}