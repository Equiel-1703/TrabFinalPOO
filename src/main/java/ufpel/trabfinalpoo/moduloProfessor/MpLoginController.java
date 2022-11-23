package ufpel.trabfinalpoo.moduloProfessor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.apache.commons.lang3.SerializationUtils;
import ufpel.trabfinalpoo.generalClasses.BackToHome;
import ufpel.trabfinalpoo.generalClasses.Professor;
import ufpel.trabfinalpoo.helperClasses.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MpLoginController extends BackToHome implements Initializable {

    @FXML
    private ComboBox<Professor> cmbProfessorLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] files = GetFilesWithExtension.getAllFilesWithCertainExtension(new File(FilesManager.PATH_TO_SAVE_DATA), ".pdata");
        List<Professor> profes = new LinkedList<>();

        if(files != null) {
            try {
                for (String s : files) {
                    FileInputStream profData = new FileInputStream(FilesManager.PATH_TO_SAVE_DATA_SEP + s);

                    profes.add(SerializationUtils.deserialize(profData));
                }
            } catch (FileNotFoundException e) {
                System.err.println("Um dos arquivos .pdata não pôde ser lido.");
                System.err.println(e.toString());
                System.exit(1);
            }
        }

        // Adiciona todos os professores na combobox
        cmbProfessorLogin.getItems().addAll(profes);
    }

    public void entrar() {
        Professor selectedProfessor = cmbProfessorLogin.getValue();

        if(selectedProfessor == null)
            return;

        // Envia o professor selecionado para o mensageiro
        Messenger m = Messenger.getInstance();
        m.queue.add(selectedProfessor);

        SceneManager.sceneSet(SceneManager.SC_PROFESSOR_ATIVIDADES);
    }
}
