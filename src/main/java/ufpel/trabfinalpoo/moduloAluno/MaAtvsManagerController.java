package ufpel.trabfinalpoo.moduloAluno;

import com.opencsv.CSVWriter;
import ufpel.trabfinalpoo.generalClasses.Aluno;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.lang3.SerializationUtils;
import ufpel.trabfinalpoo.Main;
import ufpel.trabfinalpoo.helperClasses.CSVManager;
import ufpel.trabfinalpoo.cellfactories.CellFactoryAtvAluno;
import ufpel.trabfinalpoo.helperClasses.FilesManager;
import ufpel.trabfinalpoo.helperClasses.Messenger;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class MaAtvsManagerController implements Initializable {

    @FXML
    private Circle profPic;
    @FXML
    private Label lblCurso;
    @FXML
    private Label lblDtCad;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblFormatura;
    @FXML
    private Label lblIngresso;
    @FXML
    private Label lblMatricula;
    @FXML
    private Label lblNome;
    @FXML
    private ListView<AtividadeCadastrada> listviewAtvs;

    private Aluno aluno;
    private List<String[]> listTiposAtividades;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Pega instância do mensageiro
        Messenger m = Messenger.getInstance();

        // Pega o objeto do aluno que estará no mensageiro
        try {
            aluno = (Aluno)m.queue.remove();

            // Verifica se recebeu a lista de atividades já cadastradas do aluno
            if(!m.queue.isEmpty())
            {
                List<AtividadeCadastrada> cadLs = (List<AtividadeCadastrada>) m.queue.remove();

                ObservableList<AtividadeCadastrada> ls = FXCollections.observableList(cadLs);
                listviewAtvs.setItems(ls);
            }
        }
        catch (NoSuchElementException e) {
            System.err.println("O singleton que troca informações entre as cenas por " +
                    "algum motivo chegou vazio na tela de atividades.");

            System.exit(1);
        }

        // Carrega a imagem do aluno
        loadImg();

        // Preenche os labels
        lblNome.setText(aluno.getNome());
        lblEmail.setText(aluno.getEmail());
        lblMatricula.setText(aluno.getMatricula());
        lblCurso.setText(aluno.getCurso().toString());
        lblIngresso.setText("Ingresso: " + aluno.getSemIngresso());
        lblFormatura.setText("Prev. formatura: " + aluno.getPrevFormat());
        lblDtCad.setText("Dt. Cadastro: " + aluno.getDataCad());

        // Inicializa a lista que vai guardar os dados do CSV das possíveis atividades a serem
        // cadastradas. Esse parte do código ficava em outro controlador, porém esse objeto seria
        // recriado e recarregado muitas vezes, valendo mais a pena colocá-lo aqui e carregar uma vez só.
        listTiposAtividades = new ArrayList<>();

        String csvPath = null;
        switch (aluno.getCurso())
        {
            case CCOMP:
                csvPath = CSVManager.CSV_ATV_CIENCIA;
                break;

            case ENGCOMP:
                csvPath = CSVManager.CSV_ATV_ENG;
                break;
        }

        try {
            listTiposAtividades.addAll(CSVManager.readCSV(csvPath, ';'));
        }
        catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        }

        // Configura como as células da lista vão se comportar
        listviewAtvs.setCellFactory(new Callback<ListView<AtividadeCadastrada>, ListCell<AtividadeCadastrada>>() {
            @Override
            public ListCell<AtividadeCadastrada> call(ListView<AtividadeCadastrada> atividadeCadastradaListView) {
                return new CellFactoryAtvAluno();
            }
        });
    }

    public void newAtv() {
        Stage stage = new Stage();

        Messenger m = Messenger.getInstance();
        // Envia pelo mensageiro o novo Stage
        m.queue.add(stage);
        // Envia a lista de possíveis atividades
        m.queue.add(listTiposAtividades);

        stage.setScene(new Scene(SceneManager.loadFXML(SceneManager.SC_ALUNO_ADD_ATV)));
        stage.setTitle("Adicionar Atividade");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Adiciona a atividade na lista (se o usuário não clicou em fechar)
        if(!m.queue.isEmpty())
        {
            AtividadeCadastrada cadAtv = (AtividadeCadastrada) m.queue.remove();
            listviewAtvs.getItems().add(cadAtv);
        }
    }

    public void removeAtv() {
        AtividadeCadastrada selectedAtv = listviewAtvs.getSelectionModel().getSelectedItem();

        if(selectedAtv != null)
            listviewAtvs.getItems().remove(selectedAtv);
    }

    public void done() throws IOException {
        /* Salva os dados do aluno em um .data */

        // Nome do arquivo de saída
        String alunoDataFile = FilesManager.PATH_TO_SAVE_DATA_SEP + aluno.getNome().toUpperCase() + "_" + aluno.getMatricula() + ".data";

        // Cria stream de saída de dados
        FileOutputStream fOut = new FileOutputStream(alunoDataFile);
        DataOutputStream datOut = new DataOutputStream(fOut);

        // Escreve o objeto do novo aluno em um arquivo binário
        datOut.flush();
        datOut.write(SerializationUtils.serialize(aluno));

        // Fecha as streams
        fOut.close();
        datOut.close();

        /* Salva as atividades do aluno em um .csv */

        CSVWriter csvWriter = null;

        try {
            csvWriter = CSVManager.writeCSVSetup(aluno);
        }
        catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        }

        for (AtividadeCadastrada cadAtv : listviewAtvs.getItems())
        {
            csvWriter.writeNext(cadAtv.toStringArray());
        }

        try {
            csvWriter.close();
        }
        catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        }

        SceneManager.sceneSet(SceneManager.SC_INICIO);
    }

    public void changePic() {
        Stage stage = new Stage();

        Messenger m = Messenger.getInstance();
        // Envia pelo mensageiro o novo Stage
        m.queue.add(stage);

        stage.setScene(new Scene(SceneManager.loadFXML(SceneManager.SC_ALUNO_CHNG_PIC)));
        stage.setTitle("Mudar foto");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Carrega a imagem do aluno (se ele alterou)
        if(!m.queue.isEmpty()){
            aluno.setIconID((String) m.queue.remove());
            loadImg();
        }
    }

    private void loadImg() {
        String s = Main.class.getResource("moduloAluno/profPics/" + aluno.getIconID()).toString();
        Image pic = new Image(s);
        profPic.setFill(new ImagePattern(pic));
    }
}
