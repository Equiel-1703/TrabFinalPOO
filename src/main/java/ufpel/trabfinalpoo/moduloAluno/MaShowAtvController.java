package ufpel.trabfinalpoo.moduloAluno;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.Main;
import ufpel.trabfinalpoo.helperClasses.Messenger;

import java.net.URL;
import java.util.ResourceBundle;

public class MaShowAtvController implements Initializable {

    @FXML
    private Label lblCodAndTipo;
    @FXML
    private TextArea txtareaDesc;
    @FXML
    private Label lblQtdeHoras;

    private AtividadeCadastrada cadAtv;

    private Stage currentStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Messenger m = Messenger.getInstance();

        currentStage = (Stage) m.queue.remove();
        cadAtv = (AtividadeCadastrada) m.queue.remove();

        lblCodAndTipo.setText(cadAtv.getCodigo() + " - " + cadAtv.getTipoAtv());
        lblQtdeHoras.setText("Sua quantidade de horas: " + cadAtv.getQtdeHoras());
        txtareaDesc.setText(cadAtv.getDescAtv());

        // Precisamos colocar dentro de um runLater pois não é possível pegar o
        // ScrollPane no initialize. Isso é preciso para arrumar o bug do javaFX
        // que deixa o texto de um TextArea borrado :P
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtareaDesc.setCache(false);
                ScrollPane sp = (ScrollPane) txtareaDesc.getChildrenUnmodifiable().get(0);
                sp.setCache(false);
                for (Node n : sp.getChildrenUnmodifiable()) {
                    n.setCache(false);
                }
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

}