package ufpel.trabfinalpoo.firstScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.Main;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.io.IOException;

public class selecModuleController {

    public void souAlunoEntrar(ActionEvent event) throws IOException {
        Stage st = Main.getMainStage();

        // Carrega a cena
        SceneManager.sceneSet(SceneManager.SC_ALUNO_LOGIN);

        // Centraliza a janela
        st.sizeToScene();
        st.centerOnScreen();
    }

    @FXML
    void souAlunoCadastrar(ActionEvent event) {
        Stage st = Main.getMainStage();

        SceneManager.sceneSet(SceneManager.SC_ALUNO_CADASTRO);

        st.sizeToScene();
        st.centerOnScreen();
    }

    public void souProfessorEntrar(ActionEvent event) {

    }

    @FXML
    void souProfessorCadastrar(ActionEvent event) {

    }
}