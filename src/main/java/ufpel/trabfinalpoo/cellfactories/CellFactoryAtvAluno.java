package ufpel.trabfinalpoo.cellfactories;

import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ufpel.trabfinalpoo.generalClasses.AtividadeCadastrada;
import ufpel.trabfinalpoo.helperClasses.Messenger;
import ufpel.trabfinalpoo.helperClasses.SceneManager;

public class CellFactoryAtvAluno extends ListCell<AtividadeCadastrada> {

    // Configura o evento de clique da ListView
    public CellFactoryAtvAluno() {
        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && !this.isEmpty()) {
                Stage st = new Stage();

                // Envia para o mensageiro o novo palco e a atividade clicada
                Messenger m = Messenger.getInstance();
                m.queue.add(st);
                m.queue.add(this.getItem());

                st.setScene(new Scene(SceneManager.loadFXML(SceneManager.SC_ALUNO_SHOW_ATV)));
                st.setTitle("Visualizar atividade \"" + this.getItem().toString() + "\"");
                st.setResizable(false);
                st.initModality(Modality.APPLICATION_MODAL);
                st.showAndWait();
            }
            e.consume();
        });
    }

    @Override
    protected void updateItem(AtividadeCadastrada atividadeCadastrada, boolean b) {
        super.updateItem(atividadeCadastrada, b);

        if (b || atividadeCadastrada == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(atividadeCadastrada.toString());
        }
    }
}
