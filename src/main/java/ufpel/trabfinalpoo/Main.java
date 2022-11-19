package ufpel.trabfinalpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) {
        if(mainStage == null)
            mainStage = stage;

        // Carrega a primeira tela do programa
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("selectMode.fxml")));
        } catch (Exception e) {
            System.exit(0);
        }

        Scene scene = new Scene(root);

        // Mostra a janela
        mainStage.setTitle("Controle de Horas Complementares");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainStage() { return mainStage; }
}