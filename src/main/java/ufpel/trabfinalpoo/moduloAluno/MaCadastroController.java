package ufpel.trabfinalpoo.moduloAluno;

import ufpel.trabfinalpoo.generalClasses.Aluno;
import ufpel.trabfinalpoo.generalClasses.BackToHome;
import ufpel.trabfinalpoo.generalClasses.Curso;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ufpel.trabfinalpoo.helperClasses.Messenger;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MaCadastroController extends BackToHome implements Initializable {

    @FXML
    private ComboBox<Curso> cmbCurso;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtFormatura;
    @FXML
    private TextField txtMatricula;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSemIngresso;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Curso c : Curso.values())
        {
            cmbCurso.getItems().add(c);
        }
    }

    // Validação dos inputs do usuário
    private boolean entradasValidas(Aluno novAluno) {
        String alertTitle = "Erro de input";

        if(!novAluno.setNome(txtNome.getText())) {
            showErrorAlert(alertTitle, "Insira um nome!");
            return false;
        }
        if(!novAluno.setEmail(txtEmail.getText())) {
            showErrorAlert(alertTitle, "Email em formato incorreto!");
            return false;
        }
        if(!novAluno.setMatricula(txtMatricula.getText())) {
            showErrorAlert(alertTitle, "Matrícula inválida!");
            return false;
        }
        if(cmbCurso.getSelectionModel().getSelectedIndex() == -1) {
            showErrorAlert(alertTitle, "Selecione um curso!");
            return false;
        }
        else
            novAluno.setCurso(cmbCurso.getValue());

        if(!novAluno.setSemIngresso(txtSemIngresso.getText())) {
            showErrorAlert(alertTitle, "Semestre de ingresso inválido!");
            return false;
        }

        try {
            int anoFormat = Integer.parseInt(txtFormatura.getText());

            if(!novAluno.setPrevFormat(anoFormat)) {
                showErrorAlert(alertTitle, "O ano digitado é inválido!");
                return false;
            }
        }
        catch (NumberFormatException e) {
            showErrorAlert(alertTitle, "Só são permitidos números!");
            return false;
        }

        return true;
    }

    // Mostra alerta de erro com titulo e conteudo personalizado
    private void showErrorAlert(String titulo, String contentTxt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(titulo);

        alert.setContentText(contentTxt);
        alert.showAndWait();
    }

    public void clicProximo() {
        // Cria objeto do aluno
        Aluno novAluno = new Aluno();

        // Valida os campos de entrada
        if(!entradasValidas(novAluno))
            return;

        // Cria um formatador para a data
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Salva a data de cadastro do aluno
        novAluno.setDataCad(dtf.format(LocalDateTime.now()));

        // Envia o objeto do aluno para a classe mensageira (é assim que eu chamo um singleton)
        Messenger m = Messenger.getInstance();
        m.queue.add(novAluno);

        // Troca de tela
        SceneManager.sceneSet(SceneManager.SC_ALUNO_ATIVIDADES);
    }
}
