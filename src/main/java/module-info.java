module ufpel.trabfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ufpel.trabfinalpoo to javafx.fxml;
    opens ufpel.trabfinalpoo.moduloAluno to javafx.fxml;
    exports ufpel.trabfinalpoo;
}