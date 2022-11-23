package ufpel.trabfinalpoo.moduloProfessor;

import com.opencsv.CSVWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import org.apache.commons.lang3.SerializationUtils;
import ufpel.trabfinalpoo.Main;
import ufpel.trabfinalpoo.cellfactories.CellFactoryAtvProf;
import ufpel.trabfinalpoo.generalClasses.Aluno;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import ufpel.trabfinalpoo.generalClasses.Professor;
import ufpel.trabfinalpoo.helperClasses.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MpAtvsManagerController implements Initializable {

    @FXML
    private Label lblDtCad;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblSIAPE;
    @FXML
    private Circle profPic;
    @FXML
    private ListView<AtividadeCadastrada> listViewAtvsAlunoSelecionado;
    @FXML
    private ListView<Aluno> listviewAlunosCadastrados;

    private Professor professor;
    private List<Aluno> alunosCadastrados;
    private Map<String, List<AtividadeCadastrada>> atvsAlunoMap;
    private Aluno currentSelectedAluno;
    private Aluno lastSelectedAluno;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Pega instância do mensageiro
        Messenger m = Messenger.getInstance();

        // Pega o objeto do professor que estará no mensageiro
        try {
            professor = (Professor) m.queue.remove();

        }
        catch (NoSuchElementException e) {
            System.err.println("O singleton que troca informações entre as cenas por " +
                    "algum motivo chegou vazio na tela de atividades do professor.");

            System.exit(1);
        }

        // Carrega fotinha do professor
        String fotoPath = Main.class.getResource("moduloProfessor/prof.jpg").toString();
        Image pic = new Image(fotoPath);
        profPic.setFill(new ImagePattern(pic));

        // Preenche os labels
        lblNome.setText(professor.getNome());
        lblSIAPE.setText(professor.getSIAPE());
        lblDtCad.setText("Dt. Cadastro: " + professor.getDataCad());

        /* Cria a lista de alunos cadastrados e suas respectivas atividades */
        alunosCadastrados = new LinkedList<>();
        atvsAlunoMap = new HashMap<>();

        String[] dataFiles = GetFilesWithExtension.getAllFilesWithCertainExtension(new File(FilesManager.PATH_TO_SAVE_DATA), ".data");

        if(dataFiles.length > 0)
        {
            try {
                for (String dataAluno : dataFiles) {
                    // Determina o nome do csv que deve existir para esse arquivo .data
                    String nomeCSVCorrespontente = dataAluno.replace(".data", ".csv");
                    // Cria um objeto de arquivo associado para o csv
                    File CSVCorrespondente = new File(FilesManager.PATH_TO_SAVE_DATA_SEP + nomeCSVCorrespontente);

                    // Verifica se o csv existe
                    if (CSVCorrespondente.exists())
                    {
                        // Se sim, lê o .data do aluno
                        FileInputStream alunoData = new FileInputStream(new File(FilesManager.PATH_TO_SAVE_DATA_SEP + dataAluno));
                        Aluno alunoDeserialized = SerializationUtils.deserialize(alunoData);

                        // Adiciona o aluno na lista
                        alunosCadastrados.add(alunoDeserialized);

                        // Carrega as atividades desse aluno
                        List<String[]> csvAtvsAlunoSTR = CSVManager.readCSV(FilesManager.PATH_TO_SAVE_DATA_SEP + nomeCSVCorrespontente, ';');

                        // Associa uma chave num hashmap para as atividades desse aluno
                        atvsAlunoMap.put(alunoDeserialized.getNome(), AtividadeCadastrada.convertToAtvCadList(csvAtvsAlunoSTR));
                    }
                }
            }
            catch (Exception e)
            {
                System.err.println("Um dos arquivos .data não pôde ser carregado.");
                System.err.println(e.toString());
                System.exit(1);
            }
        }

        // Adiciona os alunos validados na listView
        listviewAlunosCadastrados.getItems().addAll(alunosCadastrados);

        /* Configura o callback das células da aba de confirmação/rejeição das atividades */
        listViewAtvsAlunoSelecionado.setCellFactory(new Callback<ListView<AtividadeCadastrada>, ListCell<AtividadeCadastrada>>() {
            @Override
            public ListCell<AtividadeCadastrada> call(ListView<AtividadeCadastrada> atividadeCadastradaListView) {
                return new CellFactoryAtvProf();
            }
        });

    }

    public void done(ActionEvent event) throws IOException {
        /* Salva as alterações no csv */
        if(currentSelectedAluno != null) {
            CSVWriter csvWriter = null;

            try {
                csvWriter = CSVManager.writeCSVSetup(currentSelectedAluno);
            } catch (IOException e) {
                System.err.println(e.toString());
                System.exit(1);
            }

            for (AtividadeCadastrada cadAtv : atvsAlunoMap.get(currentSelectedAluno.getNome())) {
                csvWriter.writeNext(cadAtv.toStringArray());
            }

            try {
                csvWriter.close();
            } catch (IOException e) {
                System.err.println(e.toString());
                System.exit(1);
            }
        }

        SceneManager.sceneSet(SceneManager.SC_INICIO);
    }

    public void clicaAluno() {
        currentSelectedAluno = listviewAlunosCadastrados.getSelectionModel().getSelectedItem();

        if (lastSelectedAluno != currentSelectedAluno){
            listViewAtvsAlunoSelecionado.getItems().setAll(atvsAlunoMap.get(currentSelectedAluno.getNome()));
        }

        lastSelectedAluno = currentSelectedAluno;
    }

    public void deselect(KeyEvent event) {
        if(event.getCode() == KeyCode.ESCAPE)
        {
            listViewAtvsAlunoSelecionado.getSelectionModel().clearSelection();
        }
    }
}
