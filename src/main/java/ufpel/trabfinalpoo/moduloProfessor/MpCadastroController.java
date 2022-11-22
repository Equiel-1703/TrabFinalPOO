package ufpel.trabfinalpoo.moduloProfessor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ufpel.trabfinalpoo.helperClasses.Messenger;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MpCadastroController {

    @FXML
    private TextField txtSIAPE;
    @FXML
    private TextField txtNome;

    // Validação dos inputs do usuário
    private boolean entradasValidas(Professor novProf) {
        String alertTitle = "Erro de input";

        if(!novProf.setNome(txtNome.getText())) {
            showErrorAlert(alertTitle, "Insira um nome!");
            return false;
        }
        if(!novProf.setSIAPE(txtSIAPE.getText())) {
            showErrorAlert(alertTitle, "Matrícula inválida!");
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
        // Cria objeto do professor
        Professor novProf = new Professor();

        // Valida os campos de entrada
        if(!entradasValidas(novProf))
            return;

        // Cria um formatador para a data
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Salva a data de cadastro do professor
        novProf.setDataCad(dtf.format(LocalDateTime.now()));

        // Envia o objeto do professor para o mensageiro
        Messenger m = Messenger.getInstance();
        m.queue.add(novProf);

        // Troca de tela
        SceneManager.sceneSet(SceneManager.SC_ALUNO_ATIVIDADES);
    }
}
