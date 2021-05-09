/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import tladatabase.Entity.Student;
import tladatabase.Utility.CreateTabs;
import tladatabase.Utility.Paths;
import tladatabase.Utility.RestrictTextField;
import tladatabase.Entity.Class;
import tladatabase.EntityManager.ClassManager;
import tladatabase.Utility.IDs_generator;
import javax.net.ssl.HttpsURLConnection;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class CreateClassController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button cancel_Button;
    
    @FXML
    public Button finish_Button;
    
    @FXML
    public TextField newClass_Textfield;
    
    @FXML
    private Label label;
    
    private Class classes;
    
    private boolean createtable = false;
    
    public boolean isCreated() {
        return createtable;
    }
    
    private Stage primaryStage;
    
    public void setDialogStage(Stage dialogStage) {
        this.primaryStage = dialogStage;
    }

    ////////////////////////////////////////////
    private TableView<Student> tableViews;
    
    @FXML
    private TableColumn<Student, String> lastName;
    
    @FXML
    private TableColumn<Student, String> firstName;
    
    @FXML
    private TableColumn<Student, String> otherName;
    
    @FXML
    private TableColumn<Student, String> ppN;
    
    public Button newStudent;
    
    public Button smsButton;
    
    public MenuItem newStud;
    
    public TabPane tabPane;
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    
    EntityManager em = emf.createEntityManager();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    void cancelButton_Initialized(ActionEvent event) {
        
        primaryStage.close();
        em.close();
        emf.close();
    }
    
    private String newClass_getTextFromField = "";
    
    public String getNewClass_getTextFromField() {
        return newClass_getTextFromField;
    }
    
    public void setNewClass_getTextFromField(String newClass_getTextFromField) {
        this.newClass_getTextFromField = newClass_getTextFromField;
    }
    
    @FXML
    void finish_ButtonInitialized(ActionEvent event) throws IOException {
        
        if (v() == false) {
            
            createtable = true;
            
            primaryStage.close();
            setNewClass_getTextFromField(newClass_Textfield.getText());
            classes.setClassName(newClass_Textfield.getText());
            
            em.getTransaction().begin();
            em.persist(classes);
            em.getTransaction().commit();
            
            em.close();
            emf.close();
            
            newStudent.setDisable(false);
            smsButton.setDisable(false);
            newStud.setDisable(false);
            
        } else {
            
            label.setOpacity(1.0);
        }
    }
    
    public void setClasses(Class classes) {
        this.classes = classes;
    }
    
    boolean v() {
        
        return newClass_Textfield.getText().isEmpty() || checkClassExist() == 0;
    }
    
    int checkClassExist() {
        
        if (!MainPageController.getGetTabsAsClass().isEmpty()) {
            for (int i = 0; i < MainPageController.getGetTabsAsClass().size(); ++i) {
                
                if (newClass_Textfield.getText().equals(MainPageController.getGetTabsAsClass().get(i))) {
                    JOptionPane.showMessageDialog(null, "Class already Exist", "Notify", JOptionPane.WARNING_MESSAGE);
                    
                    return 0;
                }
            }
        }
        
        return 1;
    }
    
}
