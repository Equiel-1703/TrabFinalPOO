package ufpel.trabfinalpoo.helperClasses;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.Main;

public final class SceneManager {

    // FXMLs
    public static final String SC_INICIO = "selecModule.fxml";
    public static final String SC_ALUNO_LOGIN = "moduloAluno/maLogin.fxml";
    public static final String SC_ALUNO_CADASTRO = "moduloAluno/maCadastro.fxml";
    public static final String SC_ALUNO_ATIVIDADES = "moduloAluno/maAtvsManager.fxml";
    public static final String SC_ALUNO_ADD_ATV = "moduloAluno/maAddAtv.fxml";
    public static final String SC_ALUNO_CHNG_PIC = "moduloAluno/maChangeProfPic.fxml";
    public static final String SC_ALUNO_SHOW_ATV = "moduloAluno/maShowAtv.fxml";
    public static final String SC_PROFESSOR_CADASTRO = "moduloProfessor/mpCadastro.fxml";
    public static final String SC_PROFESSOR_LOGIN = "moduloProfessor/mpLogin.fxml";
    public static final String SC_PROFESSOR_ATIVIDADES = "moduloProfessor/mpAtvsManager.fxml";
    public static final String SC_PROFESSOR_ACEITA_REJEITA = "moduloProfessor/mpApproveAtv.fxml";

    // Garantir que a classe não será instanciada
    private SceneManager() {}

    // Método para trocar de cena
    public static void sceneSet(String sceneFXMLPath) {
        Parent root = null;

        try {
            root = FXMLLoader.load(Main.class.getResource(sceneFXMLPath));
        }
        catch (Exception e) {
            System.err.println("O FXML \"" + sceneFXMLPath + "\" não pôde ser carregado.");
            System.err.println(e.toString());
            System.exit(0);
        }

        Stage mainSt = Main.getMainStage();

        mainSt.getScene().setRoot(root);
        mainSt.sizeToScene();
        mainSt.centerOnScreen();
    }

    // Carrega um FXML para abrir um novo stage (retorna como Parent)
    public static Parent loadFXML(String sceneFXMLPath) {
        Parent root = null;

        try {
            root = FXMLLoader.load(Main.class.getResource(sceneFXMLPath));
        }
        catch (Exception e) {
            System.err.println("O FXML \"" + sceneFXMLPath + "\" não pôde ser carregado.");
            System.exit(0);
        }

        return root;
    }

}
