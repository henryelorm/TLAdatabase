/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Entity.Teacher;
import tladatabase.EntityManager.TeacherManager;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class ClassInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public TextField teacherName;

    @FXML
    public TextField phoneNumber;

    @FXML
    private Button saveButton;

    @FXML
    public Text numberOfStudents;

    @FXML
    public Text numberOfMaleStudents;

    @FXML
    public Text numberOfFemaleStudents;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        teacherName.setText("");
        phoneNumber.setText("");

        teacherName.setEditable(false);
        phoneNumber.setEditable(false);
        TeacherManager tm = new TeacherManager();
        List<Teacher> teacher = tm.findAllTeachers(em);

        if (!teacher.isEmpty()) {

            for (int i = 0; i < teacher.size(); i++) {

                if (teacher.get(i).getId().equals(MainPageController.getClssId())) {
                    teacherName.setText(teacher.get(i).getFullName());
                    phoneNumber.setText(teacher.get(i).getPhoneNumber());

                }
            }
        }

    }

    @FXML
    void teacherNameFieldinitialize(MouseEvent event) {

        if (event.getClickCount() == 2) {
            teacherName.setEditable(true);
            teacherName.setOpacity(1.0);
        }

    }

    @FXML
    void teacherPhoneFieldinitialize(MouseEvent event) {
        if (event.getClickCount() == 2) {
            phoneNumber.setEditable(true);
            phoneNumber.setOpacity(1.0);
        }
    }

    @FXML
    void saveButtonInitialize(ActionEvent event) {

      //  System.err.println(MainPageController.getClssId());
        TeacherManager tm = new TeacherManager();
        boolean teacherExist = false;
        boolean teacherNotExist = false;

        List<Teacher> teacherList = tm.findAllTeachers(em);
        if (!teacherList.isEmpty()) {

            for (Teacher teacherList1 : teacherList) {
                if (teacherList1.getId().equals(MainPageController.getClssId())) {
                    teacherExist = true;
                } else {
                    teacherNotExist = true;
                }
            }

        } else {
            em.getTransaction().begin();
            Teacher teacher = new Teacher();
            teacher.setId(MainPageController.getClssId());
            teacher.setFullName(teacherName.getText());
            teacher.setPhoneNumber(phoneNumber.getText());
            em.persist(teacher);
            em.getTransaction().commit();
        }

        if (teacherExist == true) {
            Teacher teach = em.find(Teacher.class, MainPageController.getClssId());
            em.getTransaction().begin();
            teach.setFullName(teacherName.getText());
            teach.setPhoneNumber(phoneNumber.getText());
            em.getTransaction().commit();
        } else if (teacherNotExist == true) {
            em.getTransaction().begin();
            Teacher teacher = new Teacher();
            teacher.setId(MainPageController.getClssId());
            teacher.setFullName(teacherName.getText());
            teacher.setPhoneNumber(phoneNumber.getText());
            em.persist(teacher);
            em.getTransaction().commit();
        }
        saveButton.setDisable(true);

    }

}
