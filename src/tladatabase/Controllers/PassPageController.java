/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Entity.PRT;
import tladatabase.EntityManager.PRTManager;
import tladatabase.MainPage;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class PassPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane passPane;

    @FXML
    public Button ok_Button;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label label_WrongInputs;

    @FXML
    private Label no_Id;

    private final PRT prt = new PRT();

    private boolean isNotEmpty = false;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        PRTManager pm = new PRTManager();

        List<PRT> pmlist = pm.findAllPRT(em);

        if (!pmlist.isEmpty()) {

            isNotEmpty = true;

        } else {

            no_Id.setOpacity(1.0);
            nameField.setDisable(true);
            passwordField.setDisable(true);
        }

        nameField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                label_WrongInputs.setOpacity(0.0);
            }
        });

        passwordField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                label_WrongInputs.setOpacity(0.0);
            }
        });

    }

    @FXML
    void Ok_ButtonInitalized(ActionEvent event) throws Exception {

        Stage stage = (Stage) passPane.getScene().getWindow();

        ok_Button.setEffect(new Glow(0));

        if (isNotEmpty == true) {
            PRT prtt = em.find(PRT.class, "123");
            if ((prtt.getUserName().equals(nameField.getText()) && prtt.getPassword().equals(passwordField.getText())) == true) {
                MainPage mp = new MainPage();
                mp.start(new Stage());

                stage.close();

            } else {
                label_WrongInputs.setOpacity(1.0);
            }
        } else {

           
            MainPage mp = new MainPage();
            mp.start(new Stage());

            stage.close();
        }

    }

    @FXML
    void ok_ButtonEntered(MouseEvent event) {

        ok_Button.setEffect(new Glow(0.8));

    }

    @FXML
    void ok_ButtonExited(MouseEvent event) {

        ok_Button.setEffect(new Glow(0.0));

    }
}
