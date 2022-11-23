package ufpel.trabfinalpoo.moduloAluno;

import ufpel.trabfinalpoo.generalClasses.Atividade;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.helperClasses.Messenger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class MaAddAtvController implements Initializable {
    @FXML
    private ComboBox<Atividade> cmbTipoAtv;
    @FXML
    private Label lblMax;
    @FXML
    private Label lblUnits;
    @FXML
    private TextField txtDescAtv;
    @FXML
    private TextField txtUnits;
    @FXML
    private TextField txtLinkPDF;

    private Stage currentStage;
    private AtividadeCadastrada cadAtv;
    private List<String[]> listTiposAtividades;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Pega a instância do mensageiro
        Messenger m = Messenger.getInstance();

        // Salva o Stage atual e coloca e a lista de atividades
        try {
            currentStage = (Stage) m.queue.remove();
            listTiposAtividades = (List<String[]>) m.queue.remove();
        }
        catch (NoSuchElementException e) {
            System.err.println("A fila do mensageiro estava vazia. Estranho.");
            System.exit(1);
        }

        // Adiciona os campos da combobox
        for(String[] s : listTiposAtividades)
        {
            cmbTipoAtv.getItems()
                    .add(new Atividade(Integer.parseInt(s[0]),
                    s[1], s[2], Integer.parseInt(s[3]), Integer.parseInt(s[4])));
        }
    }

    public void comboChange() {
        Atividade atv = cmbTipoAtv.getValue();
        String un = atv.getUnidade();

        lblUnits.setText("" + un);

        if(!un.equals("Horas"))
            lblMax.setText("Máx. " + (atv.getQtdeMax() / atv.getQtdeMin()));
        else
            lblMax.setText("Máx. " + atv.getQtdeMax());
    }

    private boolean validateATV() {
        Atividade atv = null;

        // Valida a comboBox
        if(cmbTipoAtv.getSelectionModel().getSelectedIndex() == -1) {
            showErrorAlert("Erro de atividade", "Você precisa selecionar uma atividade!");
            return false;
        }
        atv = cmbTipoAtv.getValue();
        cadAtv = new AtividadeCadastrada(atv);

        // Valida a descrição da atividade
        if(txtDescAtv.getText().isEmpty()) {
            showErrorAlert("Descrição da atividade", "A descrição da atividade não pode ser vazia!\n" +
                    "Adicione uma descrição. Ex: \"Monitoria de POO\"");
            return false;
        }
        cadAtv.setDescAtv(txtDescAtv.getText());

        // Valida o link da atividade
        if(!txtLinkPDF.getText().matches("https://drive.google.com/file/.+")) {
            showErrorAlert("Link do PDF", "Você precisa fornecer um link do Google Drive para o comprovante " +
                    "da sua atividade!");
            return false;
        }
        try {
            cadAtv.setLinkPDF(new URL(txtLinkPDF.getText()));
        }
        catch (MalformedURLException e) {
            showErrorAlert("Link do PDF", "O seu link é inválido!");
            return false;
        }

        // Converte o número das unidades
        int unidadesDoUsuario;
        try {
            unidadesDoUsuario = Integer.parseInt(txtUnits.getText());
        }
        catch (NumberFormatException e)
        {
            showErrorAlert("Número mal escrito", "Você precisa inserir um NÚMERO de unidades!");
            return false;
        }

        // Valida as unidades de horas (caso seja desse tipo)
        if(atv.getUnidade().equals("Horas"))
        {
            if(unidadesDoUsuario < atv.getQtdeMin())
            {
                showErrorAlert("Horas mínimas não atingidas",
                        "Infelizmente você não atingiu o número de horas/semestres mínimos para essa atividade :(\nO mínimo é " +
                        cadAtv.getQtdeMin());
                return false;
            }

            cadAtv.setQtdeHoras(Math.min(unidadesDoUsuario, atv.getQtdeMax()));
        }
        else
            cadAtv.setQtdeHoras(Math.min((atv.getQtdeMin() * unidadesDoUsuario), atv.getQtdeMax()));

        return true;
    }

    private void showErrorAlert(String titulo, String contentTxt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(titulo);

        alert.setContentText(contentTxt);
        alert.showAndWait();
    }

    public void createAtv() {
        // Valida os campos da atividade
        if(!validateATV())
            return;

        // Coloca no mensageiro a atividade cadastrada e validada
        Messenger m = Messenger.getInstance();
        m.queue.add(cadAtv);

        currentStage.close();
    }
}