module ufpel.trabfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires org.apache.commons.lang3;

    opens ufpel.trabfinalpoo to javafx.fxml;
    opens ufpel.trabfinalpoo.moduloAluno to javafx.fxml;
    opens ufpel.trabfinalpoo.moduloProfessor to javafx.fxml;
    opens ufpel.trabfinalpoo.firstScene to javafx.fxml;
    opens ufpel.trabfinalpoo.generalClasses to javafx.fxml;
    opens ufpel.trabfinalpoo.helperClasses to javafx.fxml;

    exports ufpel.trabfinalpoo;
    exports ufpel.trabfinalpoo.helperClasses;
    exports ufpel.trabfinalpoo.firstScene;
    exports ufpel.trabfinalpoo.generalClasses;
    exports ufpel.trabfinalpoo.cellfactories;
    opens ufpel.trabfinalpoo.cellfactories to javafx.fxml;
}