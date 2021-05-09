/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Calculator;
import tladatabase.Entity.Student;
import tladatabase.Entity.Class;
import tladatabase.Entity.Fees_Class;
import tladatabase.Entity.Fees_Student;
import tladatabase.MainPage;
import tladatabase.Utility.BugLog;
import tladatabase.Utility.FieldCheck;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class FeesRecordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text studentName;

    @FXML
    private Text className;

    @FXML
    private Text classFees;

    @FXML
    private Text FeesDifference;

    @FXML
    private Button calculatorButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField paidField;

    @FXML
    private Label inputErrorMessage;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();
    Student student;
    Fees_Class fc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String id = TablesController.getId_forFees();
        student = em.find(Student.class, id);
        studentName.setText(student.getLastName() + " " + student.getFirstName() + " " + student.getOtherName());
        Class clss = em.find(Class.class, student.getClassId());
        className.setText(clss.getClassName());

        fc = em.find(Fees_Class.class, student.getClassId());
        Fees_Student fs = em.find(Fees_Student.class, id);
        if (fc != null) {
            classFees.setText(String.valueOf(fc.getClassFees()) + " Cedis");
        } else {
            classFees.setText("Please set Fees for this class");
        }

        if (fs != null) {
            paidField.setText(String.valueOf(fs.getFees()));
        } else {
            paidField.setText("0.0");
        }

        feesCalculate();
        paidField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                saveButton.setDisable(false);
                inputErrorMessage.setOpacity(0.0);
            }
        });
    }

    @FXML
    void saveButtonInitialized(ActionEvent event) {
        Fees_Student fs = em.find(Fees_Student.class, student.getId());
        if (fs != null) {
            em.getTransaction().begin();
            fs.setFees(FieldCheck.feesField(paidField, inputErrorMessage));
            em.getTransaction().commit();
            feesCalculate();
            saveButton.setDisable(true);
        } else {
            em.getTransaction().begin();
            Fees_Student fss = new Fees_Student(student.getId(), FieldCheck.feesField(paidField, inputErrorMessage));
            em.persist(fss);
            em.getTransaction().commit();
            feesCalculate();
            saveButton.setDisable(true);
        }
    }

    @FXML
    void amountFieldDoubleClicked(MouseEvent event) {

        if (event.getClickCount() == 2) {
            paidField.setEditable(true);
        }
    }

    @FXML
    void closeButtonInitialized(ActionEvent event) {
        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void calculatorInit(ActionEvent event) {
        Calculator c = new Calculator();
        try {
            c.Calculate(new Stage());
        } catch (IOException ex) {
            BugLog.Error_alert("Calculator Failed to Load", null, MainPage.stage);
        }
    }

    private void feesCalculate() {
        if (fc != null) {
            double difference = fc.getClassFees() - FieldCheck.feesField(paidField, inputErrorMessage);

            FeesDifference.setText(String.valueOf(round(difference)));
        } else {
            FeesDifference.setText("class value undefined");
        }
    }

    private double round(double a) {

        double roundOff = Math.round(a * 100) / 100.00;

        return roundOff;
    }
}
