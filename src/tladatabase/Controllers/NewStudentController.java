/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Entity.Student;
import tladatabase.EntityManager.ClassManager;
import tladatabase.Utility.ChooseImage;
import tladatabase.Utility.DateOfBirth;
import tladatabase.Utility.FieldCheck;
import tladatabase.Utility.IDs_generator;
import tladatabase.Utility.RestrictTextField;
import tladatabase.Entity.Class;
import tladatabase.Entity.StudentImage;
import tladatabase.EntityManager.StudentManager;
import tladatabase.Utility.BugLog;
import tladatabase.Utility.New_Edit_Student;
import tladatabase.Utility.ImageBytesConversion;
import tladatabase.Utility.Null_and_Empty_Check;
import tladatabase.Utility.Paths;
import tladatabase.Utility.SaveEditedStudent;
import tladatabase.Utility.SelectionId;
import tladatabase.Utility.StringSplit;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class NewStudentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList monthList = FXCollections.observableArrayList("JANUARY", "FEBRUARY", "MARCH", "APRIL",
            "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView loadImage_ImageView;

    @FXML
    private ComboBox dayBox;

    @FXML
    private ComboBox monthBox;

    @FXML
    private ComboBox yearBox;

    @FXML
    private ComboBox studentClass;

    // I used sex in place of gender here as is different from the fxml file
    @FXML
    private CheckBox maleSex;

    @FXML
    private CheckBox femaleSex;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField otherName;

    @FXML
    private TextField citizenship;

    @FXML
    private TextField homeTown;

    @FXML
    private TextField houseAddress;

    @FXML
    private TextField fatherName;

    @FXML
    private TextField motherName;

    @FXML
    private TextField fatherNo;

    @FXML
    private TextField motherNo;

    public Student student;

    private boolean saveClicked = false;

    private boolean editSaveClicked = false;

    public boolean isEditSavedClicked() {
        return editSaveClicked;
    }

    public boolean isSavedClicked() {
        return saveClicked;
    }

//////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Label checkAll;

    @FXML
    private Label nameCheck;

    @FXML
    private Label dobCheck;

    @FXML
    private Label studentClassCheck;

    @FXML
    private Label citizenCheck;

    @FXML
    private Label hometownCheck;

    @FXML
    private Label fatherNoCheck;

    @FXML
    private Label motherNoCheck;

    @FXML
    private Label AllNoCheck;

    @FXML
    private Label sexCheck;

    private Stage dialogStage;

    //  public static ObservableList<Student> addstudentList = FXCollections.observableArrayList();
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    ////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TextField searchF;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lastName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (lastName.getText().isEmpty() || lastName.getText() == null) {
                nameCheck.setOpacity(1.0);

            } else {
                nameCheck.setOpacity(0);
            }
        });

        citizenship.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (!citizenship.getText().isEmpty() || citizenship != null) {
                citizenCheck.setOpacity(0);
            } else {
                citizenCheck.setOpacity(1);
            }
        });

        homeTown.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (!homeTown.getText().isEmpty() || homeTown != null) {
                hometownCheck.setOpacity(0);
            } else {
                hometownCheck.setOpacity(1);
            }
        });

        yearBox.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (!yearBox.getItems().isEmpty() || yearBox.getItems() != null) {
                dobCheck.setOpacity(0);
            } else {
                dobCheck.setOpacity(1);
            }
        });

        studentClass.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if (!studentClass.getItems().isEmpty() || studentClass.getItems() != null) {
                    studentClassCheck.setOpacity(0);
                } else {
                    studentClassCheck.setOpacity(1);
                }
            }
        });

        maleSex.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                sexCheck.setOpacity(0.0);
            }
        });

        femaleSex.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                sexCheck.setOpacity(0.0);
            }
        });

    }

    private static String getClassId;

    private static int mode = 0;

    public String sexSelction = "";

    public static int getMode() {
        return mode;
    }

    public static void setMode(int mode) {
        NewStudentController.mode = mode;
    }

    public static String getGetClassId() {
        return getClassId;
    }

    public static void setGetClassId(String getClassId) {
        NewStudentController.getClassId = getClassId;
    }

    @FXML
    void maleCheckboxInitialized(ActionEvent event) {

        femaleSex.setSelected(false);
    }

    @FXML
    void femaleCheckboxInitialized(ActionEvent event) {

        maleSex.setSelected(false);

    }

    @FXML
    void saveButton_initialized(ActionEvent event) {

        StudentImage stdimage = new StudentImage();

        if (FieldCheck.nameCheck(lastName, firstName, otherName, nameCheck) == true || FieldCheck.dobCheck(dayBox, monthBox, yearBox, dobCheck) == true
                || FieldCheck.citizenCheck(citizenship, citizenCheck) == true || FieldCheck.homeTown(homeTown, hometownCheck) == true
                || FieldCheck.yearBoxCheck(yearBox, dobCheck) == 0 || FieldCheck.fatherNumber(fatherNo, fatherNoCheck, AllNoCheck) == 0
                || FieldCheck.motherNumber(motherNo, motherNoCheck, AllNoCheck) == 0 || FieldCheck.studentsClass(studentClass) == true
                || FieldCheck.sexCheckBox(maleSex, femaleSex, sexCheck) == 0) {

            checkAll.setOpacity(1);

        } else {

            try {
                SelectionId si = new SelectionId();

                student.setLastName(lastName.getText());
                student.setFirstName(firstName.getText());
                student.setOtherName(otherName.getText());
                if (maleSex.isSelected() == true) {
                    sexSelction = "Male";
                } else if (femaleSex.isSelected() == true) {
                    sexSelction = "Female";
                }
                student.setSex(sexSelction);
                student.setDob(DateOfBirth.date(dayBox.getValue().toString(), monthBox, yearBox.getValue().toString()));
        
                student.setClassId(si.getlassSelectionId(em, studentClass));
                student.setCitizenship(citizenship.getText());
                student.setHomeTown(homeTown.getText());
                student.setHouseAdd(houseAddress.getText());
                student.setFatherName(fatherName.getText());
                student.setMotherName(motherName.getText());
                student.setPhoneNo(fatherNo.getText() + "/" + motherNo.getText());
                setGetClassId(studentClass.getValue().toString());

                switch (getMode()) {
                    case 0:
                        String studentid = IDs_generator.student_Id();
                        student.setId(studentid);
                        stdimage.setId(studentid);
                        ImageBytesConversion.getImagebitsForStore(stdimage);
                        em.getTransaction().begin();
                        em.persist(student);
                        em.persist(stdimage);
                        em.getTransaction().commit();
                        saveClicked = true;
                        break;
                    case 1:
                        StudentImage stm = em.find(StudentImage.class, student.getId());
                        ImageBytesConversion.getImagebitsForStoreEdit(stm, loadImage_ImageView.getImage());
                        SaveEditedStudent ses = new SaveEditedStudent(em, student, stm);
                        editSaveClicked = true;
                        break;
                    default:
                        BugLog.Error_static("Error 102  (Mode unchecked)", "Error");
                        break;
                }

                em.close();
                emf.close();
                dialogStage.close();

            } catch (Exception error) {
                System.err.println(error);
            }
        }

    }

    public void setStudents(Student student) throws IOException {

        this.student = student;

        if (student.getId() != null) {
            firstName.setText(student.getFirstName());
            lastName.setText(student.getLastName());
            otherName.setText(student.getOtherName());
            sex(student);
            dayBox.setValue(student.getDob().getDate());
            monthBox.setValue(DateOfBirth.getMonth(student.getDob().getMonth()));
            yearBox.setValue(student.getDob().getYear() + 1900);
            studentClass.setDisable(true);
            studentClass.setValue(SelectionId.getClassFromId(em, student.getClassId()));
            citizenship.setText(student.getCitizenship());
            homeTown.setText(student.getHomeTown());
            houseAddress.setText(student.getHouseAdd());
            fatherName.setText(student.getFatherName());
            motherName.setText(student.getMotherName());

            if (!student.getPhoneNo().equals("/")) {

                if (StringSplit.splitDashString(student.getPhoneNo()).length != 2) {

                    if (StringSplit.splitDashString(student.getPhoneNo())[0] != null) {

                        String fatherno = StringSplit.splitDashString(student.getPhoneNo())[0];
                        fatherNo.setText(fatherno);
                    } else if (StringSplit.splitDashString(student.getPhoneNo())[1] != null) {
                        String motherno = StringSplit.splitDashString(student.getPhoneNo())[1];
                        motherNo.setText(motherno);
                    }

                } else if (StringSplit.splitDashString(student.getPhoneNo()).length == 2) {
                    fatherNo.setText(StringSplit.splitDashString(student.getPhoneNo())[0]);
                    motherNo.setText(StringSplit.splitDashString(student.getPhoneNo())[1]);
                }

            }
            ImageBytesConversion.isFromBytestoImage(em, loadImage_ImageView, student.getId());
        }

    }

   
    private void sex(Student student) {
        if (student.getSex().equals("Male")) {
            maleSex.setSelected(true);
        } else if (student.getSex().equals("Female")) {
            femaleSex.setSelected(true);

        }
    }

    @FXML
    void classSelection(MouseEvent event) {
        getClasses();

    }

    void getClasses() {

        if (!MainPageController.getGetTabsAsClass().isEmpty()) {
            int size = MainPageController.getGetTabsAsClass().size();
            studentClass.getItems().clear();
            for (int i = 0; i < size; ++i) {

                studentClass.getItems().add(MainPageController.getGetTabsAsClass().get(i));

            }
        } else {

            studentClass.getItems().setAll("");
        }
    }

    @FXML
    void loadStudentImage_Clicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            ChooseImage.getImageFile(loadImage_ImageView);
        }
    }

    private ObservableList dayList() {
        ObservableList daylist = FXCollections.observableArrayList();

        for (int i = 1; i < 32; i++) {
            daylist.add(i);
        }
        return daylist;
    }

    private ObservableList yearList() {
        ObservableList yearList = FXCollections.observableArrayList();

        for (int i = 1; i < 37; i++) {
            int yr = 1989 + i;
            if (yr <= 2025) {
                yearList.add(yr);
            }
        }
        return yearList;

    }

    @FXML
    void dayBoxInitialized(ActionEvent event) {

    }

    @FXML
    void dayBoxClicked(MouseEvent event) {

        dayBox.setItems(dayList());

    }

    @FXML
    void monthBoxInitialized(ActionEvent event) {

        monthBox.setItems(monthList);
    }

    @FXML
    void monthBoxClicked(MouseEvent event) {
        monthBox.setItems(monthList);
    }

    @FXML
    void yearBoxClicked(MouseEvent event) {
        yearBox.setItems(yearList());
    }

    @FXML
    void save_ButtonEntered(MouseEvent event) {

        saveButton.setEffect(new Glow(0.4));

    }

    @FXML
    void save_ButtonExited(MouseEvent event) {

        saveButton.setEffect(new Glow(0.0));

    }

    @FXML
    void cancel_ButtonEntered(MouseEvent event) {

        cancelButton.setEffect(new Glow(0.4));

    }

    @FXML
    void cancel_ButtonExited(MouseEvent event) {

        cancelButton.setEffect(new Glow(0.0));

    }

    @FXML
    void cancel_ButtonInitialized(ActionEvent event) {

        em.close();
        emf.close();

        dialogStage.close();
    }

}
