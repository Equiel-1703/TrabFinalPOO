package ufpel.trabfinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class selecModeController {
    @FXML
    public void souAluno(ActionEvent event) throws IOException {
        Stage st = Main.getMainStage();

        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("moduloAluno/maLogin.fxml")));
        st.setScene(new Scene(root));
        st.centerOnScreen();
    }

    @FXML
    public void souProfessor(ActionEvent event) {

    }
}