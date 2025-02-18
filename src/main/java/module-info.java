module com.visualiser.dsa_visualiser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.visualiser.dsa_visualiser to javafx.fxml;
    exports com.visualiser.dsa_visualiser;
}