module craft.tierup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens craft.tierup to javafx.fxml;
    exports craft.tierup;
}