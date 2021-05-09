/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Entity.Student;
import tladatabase.Entity.StudentImage;
import tladatabase.Entity.Class;
import tladatabase.EntityManager.ClassManager;
import tladatabase.MainPage;
import tladatabase.Utility.BugLog;
import tladatabase.Utility.Confirmations;
import tladatabase.Utility.Tables;
import tladatabase.Utility.CustomeImage;
import tladatabase.Utility.DeleteStudent;
import tladatabase.Utility.FeesClear;
import tladatabase.Utility.GetClassIDS;
import tladatabase.Utility.New_Edit_Student;
import tladatabase.Utility.OptionStage;
import tladatabase.Utility.Paths;
import tladatabase.Utility.PromotionAlg;
import tladatabase.Utility.SaveEditedStudent;
import tladatabase.Utility.StudentsInfo;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class TablesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane pane;

    public TableView<Student> tableViews;

    public TableColumn<Student, String> lastName;

    public TableColumn<Student, String> firstName;

    public TableColumn<Student, String> otherName;

    public TableColumn<Student, String> ppN;

 //   public TableColumn feesIdentifyButton;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem editStudent;

    @FXML
    private MenuItem deleteStudent;

    @FXML
    private MenuItem studentsInfo;

    @FXML
    public MenuItem sendSMS;

    @FXML
    public MenuItem promoteStudent;

    @FXML
    public MenuItem demoteStudent;

    @FXML
    private MenuItem feesCheck;

    @FXML
    private TableColumn number;

    ///////////////////////////////////////////////////////////////////
    @FXML
    private Text nameText;

    @FXML
    private Text sexText;

    @FXML
    private Text classText;

    @FXML
    private Text dobText;

    @FXML
    private Text citizenshipText;

    @FXML
    private Text hometownText;

    @FXML
    private Text houseaddressText;

    @FXML
    private Text fatherNameText;

    @FXML
    private Text motherNameText;

    @FXML
    private Text phonenumbersText;

    @FXML
    private ImageView studentImage;

    /////////////////////////////////////////////////////////
    private ComboBox selectClassBOXSMS;

    private TabPane optionsTabpane;

    private TabPane mainPageTabPane;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Tables.lastName(lastName);
        Tables.firstName(firstName);
        Tables.otherName(otherName);
        Tables.phoneNo(ppN);
   //     Tables.feesIden(feesIdentifyButton);
        Tables.numberColumn(number);
        //  lastName.setCellFactory(Callback<Student,String>);

        tableViews.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (tableViews.getSelectionModel().getSelectedItem() != null) {
                    if (event.getClickCount() == 2) {

                        try {
                            studentsInfo();
                        } catch (IOException ex) {
                            //  Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });

    }

    New_Edit_Student nes = new New_Edit_Student();

    //this is to store parents number for use in fetchnumberForSMS class
    static String getNumberFromParent;

    public static String getGetNumberFromParent() {
        return getNumberFromParent;
    }

    public static void setGetNumberFromParent(String getNumberFromParent) {
        TablesController.getNumberFromParent = getNumberFromParent;
    }

    @FXML
    void sendSMSInitialized(ActionEvent event) throws IOException {

        if (tableViews.getSelectionModel().getSelectedItem() != null) {

            MainPageController.setNumberForParentStatus(1);

            setGetNumberFromParent(tableViews.getSelectionModel().getSelectedItem().getPhoneNo());

            OptionStage opt = new OptionStage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            opt.optionstage(fxmlLoader);
            OptionsController optc = (OptionsController) fxmlLoader.getController();
            optc.selectClassBox = selectClassBOXSMS;
            selectClassBOXSMS = optc.selectClassBox;
            optc.optionsTabpane = optionsTabpane;
            optionsTabpane = optc.optionsTabpane;

        } else {
            BugLog.alertCall("Please Select a student from the table", "Select Student", "Select Student");
        }
    }

    @FXML
    void editStudentInitialized(ActionEvent event) {

        if (tableViews.getSelectionModel().getSelectedItem() != null) {

            Student student = tableViews.getSelectionModel().getSelectedItem();
            NewStudentController.setMode(1);
            int index = tableViews.getSelectionModel().getFocusedIndex();

            boolean savedClicked = nes.EditStudents(student);

            if (SaveEditedStudent.getStudent().getId() != null) {

                tableViews.getItems().set(index, SaveEditedStudent.getStudent());
                tableViews.getSelectionModel().select(index);

                SaveEditedStudent.setStudent(new Student(null));
            }

        } else {
            BugLog.alertCall("Please Select a student from the table", "Select Student", "Select Student");
        }

    }

    @FXML
    void deleteStudentInitialized(ActionEvent event) {

        if (tableViews.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableViews.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                Optional<ButtonType> result = Confirmations.deleteConfirmation("Are you sure you want to delete student.", "Please confirm student removal").showAndWait();

                if (result.get() == ButtonType.OK) {
                    //System.out.println(tableViews.getSelectionModel().getSelectedItem().getId());
                    DeleteStudent deleteSt = new DeleteStudent(em, tableViews.getSelectionModel().getSelectedItem());

                    tableViews.getItems().remove(selectedIndex);

                } else {

                }
            }

        } else {
            BugLog.alertCall("Please Select a student from the table", "Select Student", "Select Student");

        }

    }

    @FXML
    void studentsInfoInitialized(ActionEvent event) throws IOException {
        studentsInfo();

    }

    private static String studID;

    public static String getStudID() {
        return studID;
    }

    public static void setStudID(String studID) {
        TablesController.studID = studID;
    }

    private void studentsInfo() throws IOException {

        if (tableViews.getSelectionModel().getSelectedItem() != null) {

            String id = tableViews.getSelectionModel().getSelectedItem().getId();
            setStudID(id);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(Paths.studentInfo));
            Parent root = fxmlLoader.load();

            StudentsInfoController sic = (StudentsInfoController) fxmlLoader.getController();
            nameText = sic.nameText;
            sexText = sic.sexText;
            classText = sic.classText;
            dobText = sic.dobText;
            citizenshipText = sic.citizenshipText;
            hometownText = sic.hometownText;
            houseaddressText = sic.houseaddressText;
            fatherNameText = sic.fatherNameText;
            motherNameText = sic.motherNameText;
            phonenumbersText = sic.phonenumbersText;
            studentImage = sic.studentImage;

            sic.nameText = nameText;
            sic.sexText = sexText;
            sic.classText = classText;
            sic.dobText = dobText;
            sic.citizenshipText = citizenshipText;
            sic.hometownText = hometownText;
            sic.houseaddressText = houseaddressText;
            sic.fatherNameText = fatherNameText;
            sic.motherNameText = motherNameText;
            sic.phonenumbersText = phonenumbersText;
            sic.studentImage = studentImage;

            root.setStyle("-fx-background-color:transparent;");
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setTitle("Student's Info");
            primaryStage.setResizable(false);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.initOwner(MainPage.stage);
            primaryStage.initStyle(StageStyle.UNIFIED);
            primaryStage.setScene(scene);
            primaryStage.show();

        } else {
            BugLog.alertCall("Please Select a student from the table", "Select Student", "Select Student");

        }
    }

    ObservableList<Long> l = FXCollections.observableArrayList();

    @FXML
    void promoteStudent_MenuItemInit(ActionEvent event) {

        GetClassIDS gi = new GetClassIDS();
        l.clear();
        l.setAll(gi.getClassId(em));

        if (tableViews.getSelectionModel().getSelectedItem() != null) {

            Student student = tableViews.getSelectionModel().getSelectedItem();

            int size = l.size();
            for (int i = 0; i < size; i++) {

                if (student.getClassId().equals(l.get(i))) {
                    if ((i + 1) != size) {
                      //  System.out.println("exist");
                        Student stud = em.find(Student.class, student.getId());
                        em.getTransaction().begin();
                        stud.setClassId(l.get(i + 1));
                        FeesClear.clearOnPromotionAndDemotion(em, student.getId());
                        em.getTransaction().commit();
                        BugLog.alert("Promotion Successful, Please Restart Program to See Changes", "", MainPage.stage);
                    } else {
                        BugLog.Error_alert("No class available ahead", "", MainPage.stage);
                    }

                }
            }
        } else {
            BugLog.alertCall("Please Select a student from the table", "Select Student", "Select Student");

        }

    }

    @FXML
    void demoteStudent_MenuItemInit(ActionEvent event) {

        GetClassIDS gi = new GetClassIDS();
        l.clear();
        l.setAll(gi.getClassId(em));

        if (tableViews.getSelectionModel().getSelectedItem() != null) {

            Student student = tableViews.getSelectionModel().getSelectedItem();

            int size = l.size();
            for (int i = 0; i < size; i++) {

                if (student.getClassId().equals(l.get(i))) {
                    if ((i) != 0) {
                       // System.out.println("exist");
                        Student stud = em.find(Student.class, student.getId());
                        em.getTransaction().begin();
                        stud.setClassId(l.get(i - 1));
                        FeesClear.clearOnPromotionAndDemotion(em, student.getId());
                        em.getTransaction().commit();
                        BugLog.alert("Demotion Successful, Please Restart Program to See Changes", "", MainPage.stage);
                    } else {
                        BugLog.Error_alert("No class available below", "", MainPage.stage);
                    }

                }
            }
        } else {
            BugLog.alertCall("Please Select a student from the table", "Select Student", "Select Student");

        }
    }
    
    private static String id_forFees;

    public static String getId_forFees() {
        return id_forFees;
    }

    public static void setId_forFees(String id_forFees) {
        TablesController.id_forFees = id_forFees;
    }

    @FXML
    void feesCheckInitialize(ActionEvent event) {
        if (tableViews.getSelectionModel().getSelectedItem() != null) {

            try {
                setId_forFees(tableViews.getSelectionModel().getSelectedItem().getId());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(Paths.feesCheck));
                Parent root = loader.load();
                root.setStyle("-fx-background-color:transparent;");
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                primaryStage.setTitle("Fees");
                primaryStage.setResizable(false);
                primaryStage.initModality(Modality.WINDOW_MODAL);
                primaryStage.initOwner(MainPage.stage);
                primaryStage.initStyle(StageStyle.UNIFIED);
                primaryStage.setScene(scene);
                primaryStage.show();

            } catch (IOException ex) {
                // Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            BugLog.alertCall("Please Select a student from the table", "Select Student", "Select Student");

        }
    }

}
