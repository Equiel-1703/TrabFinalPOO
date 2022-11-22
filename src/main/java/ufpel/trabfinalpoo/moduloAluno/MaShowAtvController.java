package ufpel.trabfinalpoo.moduloAluno;

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
    private Label lblDesc;
    @FXML
    private Label lblQtdeHoras;

    private AtividadeCadastrada cadAtv;

    private Stage currentStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Messenger m = Messenger.getInstance();

        currentStage = (Stage) m.queue.remove();
        cadAtv = (AtividadeCadastrada) m.queue.remove();

        lblCodAndTipo.setText(cadAtv.codigo + " - " + cadAtv.tipoAtv);
        lblQtdeHoras.setText("Sua quantidade de horas: " + cadAtv.qtdeHoras);
        lblDesc.setText(cadAtv.descAtv);
    }

    public void linkClick(ActionEvent event) {
        Main mI = Main.getMainInst();
        HostServices hs = mI.getHostServices();
        hs.showDocument(cadAtv.linkPDF.toString());
    }

    public void fecharViewer(ActionEvent event) {
        currentStage.close();
    }

}