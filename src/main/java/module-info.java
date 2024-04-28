module com.example.paccern {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.paccern to javafx.fxml;
    exports com.example.paccern;
}