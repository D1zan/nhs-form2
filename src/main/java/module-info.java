module com.example.nhs_form2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.nhs_form2 to javafx.fxml;
    exports com.example.nhs_form2;
}