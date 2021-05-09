/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Calculator;
import tladatabase.Utility.CreateTabs;
import tladatabase.Utility.Paths;
import tladatabase.Utility.mainPage_behaviour;
import tladatabase.Entity.Class;
import tladatabase.Entity.Student;
import tladatabase.Entity.Teacher;
import tladatabase.EntityManager.ClassManager;
import tladatabase.EntityManager.ImageManager;
import tladatabase.EntityManager.StudentManager;
import tladatabase.EntityManager.TeacherManager;
import tladatabase.MainPage;
import tladatabase.Utility.BugLog;
import tladatabase.Utility.Confirmations;
import tladatabase.Utility.DeleteClass;
import tladatabase.Utility.NewClass;
import tladatabase.Utility.New_Edit_Student;
import tladatabase.Utility.PaneAnimation;
import tladatabase.Utility.SelectionId;
import tladatabase.Utility.*;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class MainPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TextField searchField;

    @FXML
    public ListView<String> searchView;

    @FXML
    private Button newStudent;

    @FXML
    public Button smsButton;

    @FXML
    private Button newClass;
    
    @FXML
    private Button calculator;

    @FXML
    public MenuItem item_newClass;

    @FXML
    private MenuItem newstudent_Item;

    @FXML
    private MenuItem item_Close;

    @FXML
    private MenuItem options;

    @FXML
    public StackPane floorPane;

    @FXML
    public TabPane tabPane;

    @FXML
    private MenuItem editClass;

    @FXML
    private MenuItem deleteClass;

    @FXML
    private MenuItem archive;

    @FXML
    private MenuItem classInfo;

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
    ////////////////////////////optionstage/////////////

    @FXML
    private Button promoteButton;

    ///////////////////////////////classInfo///
    @FXML
    private Text numberOfStudents;

    @FXML
    private Text numberOfMaleStudents;

    @FXML
    private Text numberOfFemaleStudents;

    @FXML
    private TextField teacherName;

    @FXML
    private TextField phoneNumber;

    ///////////////////////////////////////////
    @FXML
    public static TableView<Student>[] tableViews = new TableView[150];

    private List<Class> findClass;

    private List<Student> findStudents;

    public static ObservableList<Student> getstudents = FXCollections.observableArrayList();

    ObservableList<Class> getClasses = FXCollections.observableArrayList();

    public ObservableList<Student> getStudentData() {
        return getstudents;
    }

    public static int tableSize = 0;

    public static int getTableSize() {
        return tableSize;
    }

    public static void setTableSize(int tableSize) {
        MainPageController.tableSize = tableSize;
    }

    private boolean promotionEvent;

    public boolean isPromotionEvent() {
        return promotionEvent;
    }

    public void setPromotionEvent(boolean promotionEvent) {
        this.promotionEvent = promotionEvent;
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ClassManager clm = new ClassManager();
        StudentManager stm = new StudentManager();
        ImageManager imm = new ImageManager();

        findClass = clm.findAllClasses(em);
        findStudents = stm.findAllStudents(em);

        //tableViews = new TableView[Integer.MAX_VALUE];
        getClasses.setAll(findClass);
        getstudents.setAll(findStudents);

        int countNumberofBoundClass = 0;
        for (int i = 0; i < findClass.size(); ++i) {

            try {
                String className = getClasses.get(i).getClassName();
                Long classId = getClasses.get(i).getId();
                CreateTabs ct = new CreateTabs();

                FXMLLoader fxmlloader = new FXMLLoader();

                fxmlloader.setLocation(getClass().getResource(Paths.tables));

                Parent view = fxmlloader.load();

                TablesController tbc = (TablesController) fxmlloader.getController();

                tableViews[i] = tbc.tableViews;
                tbc.tableViews = tableViews[i];

                tabPane.getTabs().addAll(ct.createTab_TablesElement(tableViews[i], classId, className, view, getstudents, i));
                tableSize = i;

            } catch (IOException ex) {
                BugLog.Error_static("could not load page content (Error 101)", "Error");
            } catch (ArrayIndexOutOfBoundsException e) {
                if (countNumberofBoundClass == 0) {
                    BugLog.Error_alert("Class out of bounds please this software accepts only 150 classes \n hence delete some classes", "Error", MainPage.stage);
                    countNumberofBoundClass++;
                }
            }

        }

        if (getTableSize() != 0) {
            setTableSize(getTableSize() + 1);
        }

        searchField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                SearchEngine.search(em, newValue, searchView);

            }
        });

        FieldFocus.searchFieldFocusInstane(searchField, searchView);
        RecordsNullified rnf = new RecordsNullified();
        rnf.programRecordsNullified(em);

        CreateArchiveFolder caf = new CreateArchiveFolder();

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if (tabPane.getTabs().isEmpty()) {

                    newStudent.setDisable(true);
                    smsButton.setDisable(true);
                    newstudent_Item.setDisable(true);
                }
            }
        });

        mainPage_behaviour.buttons_behaviour_to_TabPane(tabPane, newStudent, smsButton, newstudent_Item);

    }

    /**
     *
     * @param view
     */
    private static ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    private static Long clssId;

    public static Long getClssId() {
        return clssId;
    }

    public static ObservableList<Teacher> getTeacherList() {
        return teacherList;
    }

    public static void setTeacherList(ObservableList<Teacher> teacherList) {
        MainPageController.teacherList = teacherList;
    }

    @FXML
    void classInfoInitialize(ActionEvent event) {
        SelectionId si = new SelectionId();
        StudentManager sm = new StudentManager();

        //****getting the number of male and females in a specific class****//
        int males = 0;
        int females = 0;
        List<Student> studentList = sm.findAllStudents(em);
        long classID = si.getClassSelectionId(em, tabPane.getSelectionModel().getSelectedItem().getText());
        clssId = classID;
        String className = tabPane.getSelectionModel().getSelectedItem().getText();
        if (!tabPane.getTabs().isEmpty()) {
            if (tabPane.getSelectionModel().getSelectedItem().isSelected()) {
                getTabsAsClasses();

                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getClassId().equals(classID) && studentList.get(i).getSex().equals("Male")) {

                        // System.err.println(studentList.get(i).getLastName());
                        males++;
                    } else if (studentList.get(i).getClassId().equals(classID) && studentList.get(i).getSex().equals("Female")) {
                        // System.err.println(studentList.get(i).getLastName());
                        females++;
                    }

                }
            }
        }
        //****stage call***//
        FXMLLoader loader = new FXMLLoader();
        try {
            Stage primaryStage = new Stage();
            primaryStage.setTitle(className);
            ClassInfoStage ci = new ClassInfoStage(loader, primaryStage);

            ClassInfoController controller = (ClassInfoController) loader.getController();

            numberOfStudents = controller.numberOfStudents;
            numberOfMaleStudents = controller.numberOfMaleStudents;
            numberOfFemaleStudents = controller.numberOfFemaleStudents;
            teacherName = controller.teacherName;
            phoneNumber = controller.phoneNumber;
            controller.teacherName = teacherName;
            controller.phoneNumber = phoneNumber;
            controller.numberOfStudents = numberOfStudents;
            controller.numberOfMaleStudents = numberOfMaleStudents;
            controller.numberOfFemaleStudents = numberOfFemaleStudents;

            numberOfStudents.setText(String.valueOf(males + females));
            numberOfMaleStudents.setText(String.valueOf(males));
            numberOfFemaleStudents.setText(String.valueOf(females));

            //****load teachers name****//
        } catch (IOException ex) {
            // System.err.println("not loaded");
        }

    }

    @FXML
    void archiveInitialize(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ArchiveController.class.getResource(Paths.archive));
            Parent root = (Parent) loader.load();
            root.setStyle("-fx-background-color:transparent;");
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.setTitle("Archive");
            primaryStage.initOwner(MainPage.stage);
            primaryStage.setResizable(false);

            primaryStage.initStyle(StageStyle.UNIFIED);

            primaryStage.show();

        } catch (IOException ex) {
            BugLog.Error_alert("Cannot Load Page", null, MainPage.stage);
        }

    }

    @FXML
    void listViewStudentListener(MouseEvent event) throws IOException {
        if ((searchView.getSelectionModel().getSelectedItem() == null || searchView.getSelectionModel().getSelectedItem() == "") == false) {

            if (event.getClickCount() == 2) {
                StudentManager stm = new StudentManager();

                List<Student> list = stm.findAllStudents(em);
                int size = list.size();

                for (int i = 0; i < size; i++) {

                    if (searchView.getSelectionModel().getSelectedItem().equals(list.get(i).getLastName() + " " + list.get(i).getFirstName() + " " + list.get(i).getOtherName())) {
                        String id = list.get(i).getId();

                        TablesController.setStudID(id);

                        System.err.println(list.get(i).getLastName());
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

                        StudentsInfo sti = new StudentsInfo();
                        // sti.StudentsInfo(em, id, nameText, sexText, classText, dobText, citizenshipText, hometownText, houseaddressText, fatherNameText, motherNameText, phonenumbersText, studentImage);

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

                    }
                }
            }

        }

    }

    @FXML
    void close_ItemInitialized(ActionEvent event) {

        System.exit(1);
    }

    @FXML
    void newClass_ButtonEntered(MouseEvent event) {

        newClass.setEffect(new Glow(0.8));

    }

    @FXML
    void newClass_ButtonExited(MouseEvent event) {

        newClass.setEffect(new Glow(0.0));

    }

    NewClass nc = new NewClass();

    @FXML
    void newClass_ItemInitialized(ActionEvent event) throws IOException {

        getTabsAsClasses();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(Paths.createClass));
        Parent root = fxmlLoader.load();

        CreateClassController c = (CreateClassController) fxmlLoader.getController();

        c.tabPane = tabPane;
        c.newStudent = newStudent;
        c.smsButton = smsButton;
        c.newStud = newstudent_Item;
        Class newClasses = new Class();
        boolean createClicked = nc.newClasses(newClasses, root, fxmlLoader);
        if (createClicked) {

            CreateTabs ct = new CreateTabs();
            FXMLLoader fxmlloader = new FXMLLoader();

            fxmlloader.setLocation(getClass().getResource(Paths.tables));

            Parent view = fxmlloader.load();

            tabPane.getTabs().add(ct.createTab(c.getNewClass_getTextFromField(), view, getTableSize()));

            TablesController tbc = (TablesController) fxmlloader.getController();
            try {

                tableViews[getTableSize()] = tbc.tableViews;
            } catch (ArrayIndexOutOfBoundsException e) {
                BugLog.Error_alert("Class out of bounds please this software accepts only 150 classes \n hence delete some classes", "Error", MainPage.stage);

            }
            // System.out.println(getTableSize());

            setTableSize(getTableSize() + 1);

        }

    }

    @FXML
    void editClassNameInitialized(ActionEvent event) {
        ClassManager cm = new ClassManager();

        if (tabPane.getSelectionModel().isEmpty() != true) {

            Optional<String> result = Confirmations.editClassConfirmation().showAndWait();
            if (result.isPresent()) {
                if (!result.get().isEmpty()) {
                    if (v(result.get()) != true) {
                        em.getTransaction().begin();
                        Class findC = em.find(Class.class, cm.getClassIdFromClassName(em, tabPane.getSelectionModel().getSelectedItem().getText()));
                        findC.setClassName(result.get());
                        em.getTransaction()
                                .commit();
                        tabPane.getSelectionModel()
                                .getSelectedItem().setText(result.get());
                    } else {
                        BugLog.alertAll("Class already Exist", "Class already Exist", null, Alert.AlertType.ERROR);
                    }
                }

            }

        } else {
            BugLog.alertAll("Please Create a Class First", "No Existing Class", null, Alert.AlertType.ERROR);
        }

    }

    boolean v(String n) {
        return checkClassExist(n) == 0;
    }

    int checkClassExist(String name) {

        for (int i = 0; i < tabPane.getTabs().size(); ++i) {

            if (name.equals(tabPane.getTabs().get(i).getText())) {
                //JOptionPane.showMessageDialog(null, "Class already Exist", "Notify", JOptionPane.WARNING_MESSAGE);

                return 0;
            }

        }

        return 1;
    }

    @FXML
    void deleteClassInitialized(ActionEvent event) {

        ClassManager cm = new ClassManager();

        if (tabPane.getSelectionModel().isEmpty() != true) {

            Optional<ButtonType> result = Confirmations.deleteConfirmation("Are you sure you want to delete CLASS \n all Students under this Class will be Removed", "Please confirm class removal").showAndWait();

            if (result.get() == ButtonType.OK) {

                long classId = cm.getClassIdFromClassName(em, tabPane.getSelectionModel().getSelectedItem().getText());
                DeleteClass dc = new DeleteClass(em, classId);
                tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
            }

        } else {
            BugLog.alertAll("Please Create a Class First", "No Existing Class", null, Alert.AlertType.ERROR);
        }

    }

    @FXML
    void sms_ButtonEntered(MouseEvent event) {

        smsButton.setEffect(new Glow(0.8));

    }

    @FXML
    void sms_ButtonExited(MouseEvent event) {

        smsButton.setEffect(new Glow(0.0));

    }

    private TabPane optionsTabpane;

    static int numberForParentStatus;

    public static int getNumberForParentStatus() {
        return numberForParentStatus;
    }

    public static void setNumberForParentStatus(int numberForParentStatus) {
        MainPageController.numberForParentStatus = numberForParentStatus;
    }

    @FXML
    void sms_ButtonInitialized(ActionEvent event) throws Exception {

        if (!tabPane.getTabs().isEmpty()) {
            if (tableViews[0].getItems().isEmpty()) {
                promotionEventPlaced.setPromoEventPlaced(true);
            }

        }

        setNumberForParentStatus(0);

        getTabsAsClasses();
        OptionStage opt = new OptionStage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        opt.optionstage(fxmlLoader);
        OptionsController optc = (OptionsController) fxmlLoader.getController();
        optc.selectClassBox = selectClassBOXSMS;
        selectClassBOXSMS = optc.selectClassBox;
        optc.optionsTabpane = optionsTabpane;
        optionsTabpane = optc.optionsTabpane;
        optc.promoteClassButton = promoteButton;
        promoteButton = optc.promoteClassButton;

        if (optc.isPromoted() == true) {
            PromotionAlg promotion = new PromotionAlg();
            promotion.promote(em, tableViews, tabPane);
            //  promotionEventPlaced = true;
        }

        if (optc.isUndoPomotion() == true) {

            UndoPromotion undoPromotion = new UndoPromotion();
            undoPromotion.promote(em, tableViews, tabPane);
        }

    }

    @FXML
    void newStudent_ButtonEntered(MouseEvent event) {

        newStudent.setEffect(new Glow(0.8));

    }

    @FXML
    void newStudent_ButtonExited(MouseEvent event) {

        newStudent.setEffect(new Glow(0.0));

    }
    
    @FXML
    void calculator_ButtonInitialized(ActionEvent event){
        Calculator c = new Calculator();
        try {
            c.Calculate(new Stage());
        } catch (IOException ex) {
           // Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
       BugLog.Error_alert("Calculator Failed to Load", null, MainPage.stage);
        }
    }
   
    @FXML
    void calculator_ButtonEntered(MouseEvent event) {

        calculator.setEffect(new Glow(1.0));

    }

    @FXML
    void calculator_ButtonExited(MouseEvent event) {

        calculator.setEffect(new Glow(0.0));

    }

    private ComboBox selectClassBOXSMS;

    @FXML
    void optionsinitialized(ActionEvent event) throws IOException {

        setNumberForParentStatus(0);

        getTabsAsClasses();
        OptionStage opt = new OptionStage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        opt.optionstage(fxmlLoader);
        OptionsController optc = (OptionsController) fxmlLoader.getController();
        optc.selectClassBox = selectClassBOXSMS;
        selectClassBOXSMS = optc.selectClassBox;
    }

    New_Edit_Student nes = new New_Edit_Student();

    public static ObservableList getTabsAsClass = FXCollections.observableArrayList();

    public static ObservableList getGetTabsAsClass() {
        return getTabsAsClass;
    }

    @FXML
    void newStudent_ButtonInitialized(ActionEvent event) throws Exception {

        Student student = new Student();
        getTabsAsClasses();
        NewStudentController.setMode(0);

        boolean saveClicked = nes.newStudents(student);

        if (saveClicked) {

            int x = Integer.parseInt(SelectionId.getTabOfClassId(tabPane, NewStudentController.getGetClassId()));

            getstudents.setAll(student);
            tabPane.getSelectionModel().select(x);
            tableViews[x].getItems().addAll(getstudents);
            tableViews[x].getSelectionModel().select(student);

        }
    }

    public void getTabsAsClasses() {

        getTabsAsClass.clear();
        for (int x = 0; x < tabPane.getTabs().size(); ++x) {

            getTabsAsClass.add(x, tabPane.getTabs().get(x).getText());

        }

    }

}
