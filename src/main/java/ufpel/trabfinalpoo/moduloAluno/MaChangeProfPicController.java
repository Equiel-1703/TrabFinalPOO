package ufpel.trabfinalpoo.moduloAluno;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;
import ufpel.trabfinalpoo.Main;
import ufpel.trabfinalpoo.helperClasses.Messenger;

import java.net.URL;
import java.util.ResourceBundle;

public class MaChangeProfPicController implements Initializable {
    private static final String picPath1 = Main.class.getResource("moduloAluno/profPics/agostinho.jpg").toString();
    private static final String picPath2 = Main.class.getResource("moduloAluno/profPics/gosling.jpg").toString();
    private static final String picPath3 = Main.class.getResource("moduloAluno/profPics/mark.jpg").toString();
    private static final String picPath4 = Main.class.getResource("moduloAluno/profPics/mia.jpg").toString();

    @FXML
    private Rectangle photo1;
    @FXML
    private Rectangle photo2;
    @FXML
    private Rectangle photo3;
    @FXML
    private Rectangle photo4;

    private Stage currentStage;

    @FXML
    void setPhoto(MouseEvent event) {
        Messenger m = Messenger.getInstance();

        if(event.getSource().equals(photo1)) {
            m.queue.add("agostinho.jpg");
        }
        else if(event.getSource().equals(photo2)) {
            m.queue.add("gosling.jpg");
        }
        else if(event.getSource().equals(photo3)) {
            m.queue.add("mark.jpg");
        }
        else {
            m.queue.add("mia.jpg");
        }

        currentStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentStage = (Stage) Messenger.getInstance().queue.remove();

        Image img = new Image(picPath1);
        photo1.setFill(new ImagePattern(img));

        img = new Image(picPath2);
        photo2.setFill(new ImagePattern(img));

        img = new Image(picPath3);
        photo3.setFill(new ImagePattern(img));

        img = new Image(picPath4);
        photo4.setFill(new ImagePattern(img));
    }
}
