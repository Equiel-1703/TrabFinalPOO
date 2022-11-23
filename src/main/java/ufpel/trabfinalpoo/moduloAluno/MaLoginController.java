package ufpel.trabfinalpoo.moduloAluno;

import ufpel.trabfinalpoo.generalClasses.Aluno;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.apache.commons.lang3.SerializationUtils;
import ufpel.trabfinalpoo.generalClasses.BackToHome;
import ufpel.trabfinalpoo.helperClasses.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MaLoginController extends BackToHome implements Initializable {

    @FXML
    private ComboBox<Aluno> cmbAlunoLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] files = GetFilesWithExtension.getAllFilesWithCertainExtension(new File(FilesManager.PATH_TO_SAVE_DATA), ".data");
        List<Aluno> alunos = new LinkedList<>();

        if(files != null) {
            try {
                for (String s : files) {
                    FileInputStream alunoData = new FileInputStream(FilesManager.PATH_TO_SAVE_DATA_SEP + s);

                    alunos.add(SerializationUtils.deserialize(alunoData));
                }
            } catch (FileNotFoundException e) {
                System.err.println("Um dos arquivos .data não pôde ser lido.");
                System.err.println(e.toString());
                System.exit(1);
            }
        }

        // Adiciona todos os alunos na combobox
        cmbAlunoLogin.getItems().addAll(alunos);
    }

    public void entrar() throws IOException {
        Aluno selectedAluno = cmbAlunoLogin.getValue();

        if(selectedAluno == null)
            return;

        // Envia o aluno selecionado para o mensageiro
        Messenger m = Messenger.getInstance();
        m.queue.add(selectedAluno);

        // Cria o nome
        String nomeCSVdoAluno = selectedAluno.getNome().toUpperCase()+"_"+selectedAluno.getMatricula()+".csv";
        String pathToCSVdoAluno = FilesManager.PATH_TO_SAVE_DATA_SEP + nomeCSVdoAluno;

        File csvAluno = new File(pathToCSVdoAluno);

        // Verifica se o arquivo CSV desse aluno existe
        if(csvAluno.exists())
        {
            /* Agora vai carregar o CSV das atividades desse aluno */
            List<String[]> atividades = CSVManager.readCSV(pathToCSVdoAluno, ';');

            // Converte a lista de arrays de strings para uma lista de atividades cadastradas
            List<AtividadeCadastrada> cadList = AtividadeCadastrada.convertToAtvCadList(atividades);

            // Envia essa lista para o mensageiro
            m.queue.add(cadList);
        }

        // Se não existir, o csv será criado no controlador da página de atividades

        SceneManager.sceneSet(SceneManager.SC_ALUNO_ATIVIDADES);
    }
}
