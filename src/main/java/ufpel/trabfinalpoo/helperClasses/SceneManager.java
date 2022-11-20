package ufpel.trabfinalpoo.helperClasses;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ufpel.trabfinalpoo.Main;

import java.util.Objects;

public class SceneManager {

    // FXMLs
    public static final String SC_INICIO = "selecModule.fxml";
    public static final String SC_ALUNO_LOGIN = "moduloAluno/maLogin.fxml";
    public static final String SC_ALUNO_CADASTRO = "moduloAluno/maCadastro.fxml";


    // Método para trocar de cena
    public static void sceneSet(String sceneFXMLPath) {
        Parent root = null;

        try
        {
            root = FXMLLoader.load(Main.class.getResource(sceneFXMLPath));
        }
        catch (Exception e)
        {
            System.err.println("FXML \"" + sceneFXMLPath + "\" não encontrado.");
            System.exit(0);
        }

        Main.getMainStage().getScene().setRoot(root);
    }

}
