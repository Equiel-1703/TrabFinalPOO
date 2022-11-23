package ufpel.trabfinalpoo.moduloProfessor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.SerializationUtils;
import ufpel.trabfinalpoo.generalClasses.BackToHome;
import ufpel.trabfinalpoo.generalClasses.Professor;
import ufpel.trabfinalpoo.helperClasses.FilesManager;
import ufpel.trabfinalpoo.helperClasses.Messenger;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MpCadastroController extends BackToHome {

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
            showErrorAlert(alertTitle, "SIAPE inválida!\nO SIAPE é um número de 7 dígitos.");
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

    public void clicProximo() throws IOException {
        // Cria objeto do professor
        Professor novProf = new Professor();

        // Valida os campos de entrada
        if(!entradasValidas(novProf))
            return;

        // Cria um formatador para a data
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Salva a data de cadastro do professor
        novProf.setDataCad(dtf.format(LocalDateTime.now()));

        /* Salva os dados do professor em um .pdata */

        // Nome do arquivo de saída
        String professorDataFile = FilesManager.PATH_TO_SAVE_DATA_SEP + novProf.getNome().toUpperCase() + "_" + novProf.getSIAPE() + ".pdata";

        // Cria stream de saída de dados
        FileOutputStream fOut = new FileOutputStream(professorDataFile);
        DataOutputStream datOut = new DataOutputStream(fOut);

        // Escreve o objeto do novo professor no arquivo binário
        datOut.flush();
        datOut.write(SerializationUtils.serialize(novProf));

        // Fecha as streams
        fOut.close();
        datOut.close();

        // Envia o objeto do professor para o mensageiro
        Messenger m = Messenger.getInstance();
        m.queue.add(novProf);

        // Troca de tela
        SceneManager.sceneSet(SceneManager.SC_PROFESSOR_ATIVIDADES);
    }
}
