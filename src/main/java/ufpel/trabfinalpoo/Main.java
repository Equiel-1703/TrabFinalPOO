package ufpel.trabfinalpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.io.IOException;

public class Main extends Application {

    // Palco principal do APP
    private static Stage mainStage;

    // Método para retornar o palco do APP
    public static Stage getMainStage() {
        return mainStage;
    }

    // Função principal do programa
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        if(mainStage == null)
            mainStage = stage;

        // Carrega a primeira cena do programa
        Parent root = null;

        try
        {
            root = FXMLLoader.load(this.getClass().getResource(SceneManager.SC_INICIO));
        }
        catch (IOException e)
        {
            System.err.println("Não foi encontrado o FXML da primeira cena :(");
            System.exit(1);
        }

        mainStage.setScene(new Scene(root));

        // Mostra e centraliza a janela
        mainStage.setResizable(false);
        mainStage.setTitle("Controle de Horas Complementares");
        mainStage.show();
        mainStage.centerOnScreen();
    }
}