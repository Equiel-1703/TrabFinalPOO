package ufpel.trabfinalpoo.cellfactories;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import ufpel.trabfinalpoo.helperClasses.Messenger;
import ufpel.trabfinalpoo.helperClasses.SceneManager;
import ufpel.trabfinalpoo.moduloProfessor.MpAtvsManagerController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CellFactoryAtvProf extends ListCell<AtividadeCadastrada> {

    // Configura o evento de clique da ListView
    public CellFactoryAtvProf() {
        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && !this.isEmpty()) {
                Stage st = new Stage();

                // Envia para o mensageiro o novo palco e a atividade clicada
                Messenger m = Messenger.getInstance();
                m.queue.add(st);
                m.queue.add(this.getItem());

                st.setScene(new Scene(SceneManager.loadFXML(SceneManager.SC_PROFESSOR_ACEITA_REJEITA)));
                st.setTitle("Visualizar atividade \"" + this.getItem().toString() + "\"");
                st.setResizable(false);
                st.initModality(Modality.APPLICATION_MODAL);
                st.showAndWait();

                // Após isso, o professor terá definido se irá aceitar ou recusar a atividade
            }
            e.consume();
        });
    }

    @Override
    protected void updateItem(AtividadeCadastrada atv, boolean b) {
        super.updateItem(atv, b);

        if (b || atv == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(atv.toString());

            switch (atv.getEstadoAprovacao())
            {
                case APPROVED:
                    setTextFill(Color.GREEN);
                    break;
                case REJECTED:
                    setTextFill(Color.RED);
                    break;
                default:
                    setTextFill(Color.BLACK);
                    break;
            }
        }
    }
}
