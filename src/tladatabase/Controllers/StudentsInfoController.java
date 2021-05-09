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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Entity.Student;
import tladatabase.Utility.ImageBytesConversion;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class StudentsInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public Text nameText;

    @FXML
    public Text sexText;

    @FXML
    public Text classText;

    @FXML
    public Text dobText;

    @FXML
    public Text citizenshipText;

    @FXML
    public Text hometownText;

    @FXML
    public Text houseaddressText;

    @FXML
    public Text fatherNameText;

    @FXML
    public Text motherNameText;

    @FXML
    public Text phonenumbersText;

    @FXML
    public ImageView studentImage;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        Student student = em.find(Student.class, TablesController.getStudID());

        nameText.setText(student.getLastName() + " " + student.getFirstName() + " " + student.getOtherName());
        sexText.setText(student.getSex());

        tladatabase.Entity.Class cl = em.find(tladatabase.Entity.Class.class, student.getClassId());
        classText.setText(cl.getClassName());
        dobText.setText(student.getDob().toString());
        citizenshipText.setText(student.getCitizenship());
        hometownText.setText(student.getHomeTown());
        houseaddressText.setText(student.getHouseAdd());
        fatherNameText.setText(student.getFatherName());
        motherNameText.setText(student.getMotherName());
        phonenumbersText.setText(student.getPhoneNo());
        
        try {
            ImageBytesConversion.isFromBytestoImage(em, studentImage,TablesController.getStudID());
        } catch (IOException ex) {
           // Logger.getLogger(StudentsInfoController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ddds");
        }
    }

}
