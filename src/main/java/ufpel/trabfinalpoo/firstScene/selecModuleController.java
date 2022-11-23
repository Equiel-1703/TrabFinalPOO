package ufpel.trabfinalpoo.firstScene;

import ufpel.trabfinalpoo.helperClasses.SceneManager;

public class selecModuleController {

    public void souAlunoEntrar() {
        SceneManager.sceneSet(SceneManager.SC_ALUNO_LOGIN);
    }

    public void souAlunoCadastrar() {
        SceneManager.sceneSet(SceneManager.SC_ALUNO_CADASTRO);
    }

    public void souProfessorEntrar() {
        SceneManager.sceneSet(SceneManager.SC_PROFESSOR_LOGIN);
    }

    public void souProfessorCadastrar() {
        SceneManager.sceneSet(SceneManager.SC_PROFESSOR_CADASTRO);
    }

    public void sair() {
        System.exit(0);
    }
}