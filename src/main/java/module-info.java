module com.visualiser.data_structures {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;


    opens com.visualiser.data_structures to javafx.fxml;
    exports com.visualiser.data_structures;
    exports com.visualiser.miscellaneous;
    opens com.visualiser.miscellaneous to javafx.fxml;
    exports com.visualiser.menus;
    opens com.visualiser.menus to javafx.fxml;
}