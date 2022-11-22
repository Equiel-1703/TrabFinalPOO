module ufpel.trabfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires org.apache.commons.lang3;

    opens ufpel.trabfinalpoo to javafx.fxml;
    opens ufpel.trabfinalpoo.moduloAluno to javafx.fxml;
    exports ufpel.trabfinalpoo;
    exports ufpel.trabfinalpoo.helperClasses;
    opens ufpel.trabfinalpoo.helperClasses to javafx.fxml;
    exports ufpel.trabfinalpoo.firstScene;
    opens ufpel.trabfinalpoo.firstScene to javafx.fxml;
}