package ufpel.trabfinalpoo.moduloAluno;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.apache.commons.lang3.SerializationUtils;
import ufpel.trabfinalpoo.helperClasses.CSVManager;
import ufpel.trabfinalpoo.helperClasses.GetFilesWithExtension;
import ufpel.trabfinalpoo.helperClasses.Messenger;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MaLoginController implements Initializable {

    @FXML
    private ComboBox<Aluno> cmbAlunoLogin;

    private final String PATH_TO_FILES = "./dados";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] files = GetFilesWithExtension.getAllFilesWithCertainExtension(new File(PATH_TO_FILES), "data");
        List<Aluno> alunos = new LinkedList<>();

        try {
            for(String s : files)
            {
                FileInputStream alunoData = null;

                alunoData = new FileInputStream(new File(PATH_TO_FILES + "/" + s));

                alunos.add(SerializationUtils.deserialize(alunoData));
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Um dos arquivos .data não pôde ser lido.");
            System.err.println(e.toString());
            System.exit(1);
        }

        // Adiciona todos os alunos na combobox
        cmbAlunoLogin.getItems().addAll(alunos);
    }

    public void entrar() throws IOException {
        Aluno selectedAluno = cmbAlunoLogin.getValue();

        // Envia o aluno selecionado para o mensageiro
        Messenger m = Messenger.getInstance();
        m.queue.add(selectedAluno);

        // Cria o nome
        String nomeCSVdoAluno = selectedAluno.getNome().toUpperCase()+"_"+selectedAluno.getMatricula()+".csv";
        String pathToCSVdoAluno = PATH_TO_FILES + System.getProperty("file.separator") + nomeCSVdoAluno;

        File csvAluno = new File(pathToCSVdoAluno);

        // Verifica se o arquivo CSV desse aluno existe
        if(csvAluno.exists())
        {
            /* Agora vai carregar o CSV das atividades desse aluno */
            List<String[]> atividades = CSVManager.readCSV(pathToCSVdoAluno, ';');

            // Converte a lista de arrays de strings para uma lista de atividades cadastradas
            List<AtividadeCadastrada> cadList = AtividadeCadastrada.convertToAtvCad(atividades);

            // Envia essa lista para o mensageiro
            m.queue.add(cadList);
        }

        // Se não existir, o csv será criado no controlador da página de atividades

        SceneManager.sceneSet(SceneManager.SC_ALUNO_ATIVIDADES);
    }
}
