package ufpel.trabfinalpoo.moduloProfessor;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.Main;
import ufpel.trabfinalpoo.generalClasses.Aprovacao;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import ufpel.trabfinalpoo.helperClasses.Messenger;

import java.net.URL;
import java.util.ResourceBundle;

public class MpApproveAtvController implements Initializable {

    @FXML
    private Label lblCodAndTipo;
    @FXML
    private TextArea txtareaDesc;
    @FXML
    private Label lblQtdeHoras;
    @FXML
    private RadioButton radButAceitar;
    @FXML
    private RadioButton radButReprovar;
    @FXML
    private TextArea txtareaJustificativa;

    private AtividadeCadastrada cadAtv;
    private Stage currentStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Messenger m = Messenger.getInstance();

        // Salva o palco atual e a atividade que recebeu pelo messenger
        currentStage = (Stage) m.queue.remove();
        cadAtv = (AtividadeCadastrada) m.queue.remove();

        lblCodAndTipo.setText(cadAtv.getCodigo() + " - " + cadAtv.getTipoAtv());
        lblQtdeHoras.setText("Sua quantidade de horas: " + cadAtv.getQtdeHoras());
        txtareaDesc.setText(cadAtv.getDescAtv());
        txtareaJustificativa.setText(cadAtv.getJustificativa());

        switch (cadAtv.getEstadoAprovacao())
        {
            case REJECTED:
                radButAceitar.setSelected(false);
                radButReprovar.setSelected(true);
                break;

            default:
                radButAceitar.setSelected(true);
                radButReprovar.setSelected(false);
                break;
        }

        // Precisamos colocar dentro de um runLater pois n??o ?? poss??vel pegar o
        // ScrollPane no initialize. Isso ?? preciso para arrumar o bug do javaFX
        // que deixa o texto de um TextArea borrado :P
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fixBlurredTextArea(txtareaDesc);
                fixBlurredTextArea(txtareaJustificativa);
            }
        });

    }

    public void linkClick(ActionEvent event) {
        Main mI = Main.getMainInst();
        HostServices hs = mI.getHostServices();
        hs.showDocument(cadAtv.getLinkPDF().toString());
    }

    public void fecharViewer(ActionEvent event) {
        currentStage.close();
    }

    public void okPressed(ActionEvent event) {
        // Configura os dados na atividade cadastrada
        if(radButAceitar.isSelected())
            cadAtv.setEstadoAprovacao(Aprovacao.APPROVED);
        else
            cadAtv.setEstadoAprovacao(Aprovacao.REJECTED);

        cadAtv.setJustificativa(txtareaJustificativa.getText());

        currentStage.close();
    }

    // Arrumar bug do texto borrado do JavaFX
    private void fixBlurredTextArea(TextArea txtarea) {
        txtarea.setCache(false);
        ScrollPane sp = (ScrollPane) txtarea.getChildrenUnmodifiable().get(0);
        sp.setCache(false);
        for (Node n : sp.getChildrenUnmodifiable()) {
            n.setCache(false);
        }
    }

}