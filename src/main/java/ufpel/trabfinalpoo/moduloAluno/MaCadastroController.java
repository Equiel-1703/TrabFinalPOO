package ufpel.trabfinalpoo.moduloAluno;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MaCadastroController implements Initializable {

    @FXML
    private ComboBox<String> cmbCurso;
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
        cmbCurso.getItems().addAll("Ciência da Computação", "Engenharia da Computação");
    }

    public void clicProximo() {
        Aluno novAluno = new Aluno();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erro de input");

        if(!novAluno.setNome(txtNome.getText())) {
            alert.setContentText("Insira um nome!");
            alert.showAndWait();
            return;
        }
        if(!novAluno.setEmail(txtEmail.getText())) {
            alert.setContentText("Email em formato incorreto!");
            alert.showAndWait();
            return;
        }
        if(!novAluno.setMatricula(txtMatricula.getText())) {
            alert.setContentText("Matrícula inválida!");
            alert.showAndWait();
            return;
        }
        if(cmbCurso.getSelectionModel().getSelectedIndex() == -1) {
            alert.setContentText("Selecione um curso!");
            alert.showAndWait();
            return;
        }
        else
            novAluno.setCurso(cmbCurso.getValue());

        if(!novAluno.setSemIngresso(txtSemIngresso.getText())) {
            alert.setContentText("Semestre de ingresso inválido!");
            alert.showAndWait();
            return;
        }

        try {
            int anoFormat = Integer.parseInt(txtFormatura.getText());

            if(!novAluno.setPrevFormat(anoFormat)) {
                alert.setContentText("O ano digitado é inválido!");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Digite um ano!");
            alert.showAndWait();
            return;
        }

        alert.setContentText(novAluno.toString());
        alert.showAndWait();

    }
}
