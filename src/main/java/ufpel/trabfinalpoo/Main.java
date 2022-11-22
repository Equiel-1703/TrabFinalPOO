package ufpel.trabfinalpoo;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

public class Main extends Application {

    // Palco principal do APP
    private static Stage mainStage;
    // Instância da Main
    private static Main mInstance;
    // Método para retornar o palco do APP
    public static Stage getMainStage() {
        return mainStage;
    }
    // Método para retornar instância da Main
    public static Main getMainInst() {
        return mInstance;
    }

    // Função principal do programa
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        if(mainStage == null)
            mainStage = stage;

        if(mInstance == null)
            mInstance = this;

        // Carrega a primeira cena do programa
        Parent root = SceneManager.loadFXML(SceneManager.SC_INICIO);

        mainStage.setScene(new Scene(root));

        // Mostra e centraliza a janela
        mainStage.setResizable(false);
        mainStage.setTitle("Controle de Horas Complementares");
        mainStage.show();
        mainStage.centerOnScreen();
    }
}