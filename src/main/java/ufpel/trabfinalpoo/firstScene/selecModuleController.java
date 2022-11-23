package ufpel.trabfinalpoo.firstScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.io.IOException;

public class selecModuleController {

    public void souAlunoEntrar(ActionEvent event) throws IOException {
        SceneManager.sceneSet(SceneManager.SC_ALUNO_LOGIN);
    }

    @FXML
    void souAlunoCadastrar(ActionEvent event) {
        SceneManager.sceneSet(SceneManager.SC_ALUNO_CADASTRO);
    }

    public void souProfessorEntrar(ActionEvent event) {
        SceneManager.sceneSet(SceneManager.SC_PROFESSOR_LOGIN);
    }

    @FXML
    void souProfessorCadastrar(ActionEvent event) {
        SceneManager.sceneSet(SceneManager.SC_PROFESSOR_CADASTRO);
    }
}