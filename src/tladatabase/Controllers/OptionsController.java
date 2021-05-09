/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.comm.CommPortIdentifier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tladatabase.Entity.Fees_Class;
import tladatabase.Entity.PRT;
import tladatabase.Entity.ProgramRecords;
import tladatabase.EntityManager.PRTManager;
import tladatabase.Utility.BugLog;
import tladatabase.Utility.Confirmations;
import tladatabase.Utility.FeesTable;
import tladatabase.Utility.GetClassList;
import tladatabase.Utility.OptionStage;
import tladatabase.Utility.ProgressLoadSMSInfo;
import tladatabase.Utility.SMS_MultiSend_CustomProgress;
import tladatabase.Utility.passwordPage;
import tladatabase.Utility.promotionEventPlaced;
import tladatabase.Entity.Class;
import tladatabase.Utility.FeesFill;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class OptionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TabPane optionsTabpane;

    @FXML
    private Tab smsTab;

    @FXML
    private Tab promDemotionTab;

    @FXML
    private TableView<FeesFill> tableFees;

    @FXML
    private TableColumn classesColumn;

    @FXML
    private TableColumn feesColumn;

    @FXML
    private TextArea commentArea;

    @FXML
    private TextField connStatusField;

    @FXML
    private TextField modemManufac;

    @FXML
    private TextField userId;

    @FXML
    private TextField passwordField1;

    @FXML
    private TextField passwordField2;

    @FXML
    private TextField passwordField3;

    @FXML
    private Text passText1;

    @FXML
    private Text passText2;

    @FXML
    private Text passText3;

    @FXML
    public ComboBox selectClassBox;

    @FXML
    public ComboBox availableports;

    @FXML
    private Button sendButton;

    @FXML
    public Button okPassword;

    @FXML
    private Button cancelButton;

    @FXML
    private Button cancelProDemo;

    @FXML
    private Button cancelPassword;
    
    @FXML
    private Button closeFees;

    @FXML
    public Button promoteClassButton;

    @FXML
    private Button undoClassPromotionButton;

    @FXML
    private Button removePassword;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label passLabel1;

    @FXML
    private Label passLabel2;

    @FXML
    private Label passLabel3;

    private boolean promote = false;

    public boolean isPromoted() {
        return promote;
    }

    private boolean undoPromotion = false;

    public boolean isUndoPomotion() {
        return undoPromotion;
    }

    ////////////////////////////////
    public TableView tableView;

    passwordPage pp = new passwordPage();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TLAdatabasePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        promDemotionTab.setId("0");
        smsTab.setId("1");
        modemManufac.setText("");

        pp.pass(em, removePassword, passwordField3, passText1, passText2, passText3);

        final ProgramRecords prg = em.find(ProgramRecords.class, 100L);

        if (prg.getComports() != null) {

            availableports.setValue(prg.getComports());
        }

        availableports.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                em.getTransaction().begin();
                ProgramRecords prg = em.find(ProgramRecords.class, 100L);
                if (availableports.getValue() != null) {
                    prg.setComports(availableports.getValue().toString());
                }
                em.getTransaction().commit();
            }
        });

        if (MainPageController.getNumberForParentStatus() == 0) {

        } else if (MainPageController.getNumberForParentStatus() == 1) {
            selectClassBox.setDisable(true);

            promDemotionTab.setDisable(true);
        }
////////////////////////////////fees table////////////////////////////////
        ObservableList<FeesFill> clsfees = FXCollections.observableArrayList();
        int size = GetClassList.fetchClasses(em).size();

        for (int i = 0; i < size; i++) {
            Fees_Class fc = em.find(Fees_Class.class, GetClassList.fetchClasses(em).get(i).getId());
            if (fc != null) {
                clsfees.addAll(new FeesFill(GetClassList.fetchClasses(em).get(i).getId(), GetClassList.fetchClasses(em).get(i).getClassName(), String.valueOf(fc.getClassFees())));

            } else {
                clsfees.addAll(new FeesFill(GetClassList.fetchClasses(em).get(i).getId(), GetClassList.fetchClasses(em).get(i).getClassName(), ""));

            }
        }

        tableFees.setItems(clsfees);
        FeesTable.classes(classesColumn);
        try {
            //tableFees.setItems(clsfees);
            FeesTable.fees(em, feesColumn, tableFees);
        } catch (Exception ex) {
            System.err.println("input a value");
        }

///////////////////////////////////////////////////////////////////////////
        modemManufac.setText("");
        availableports.getItems().clear();
        ProgressLoadSMSInfo progsmsInfo = new ProgressLoadSMSInfo();
        sendButton.setDisable(false);
        progsmsInfo.progress(modemManufac, availableports, prg, em, sendButton);

    }

    public static String selectedClass = "";

    @FXML
    void sendButtonInitialized(ActionEvent event) throws InterruptedException {
        OptionStage ops = new OptionStage();
        if (MainPageController.getNumberForParentStatus() == 0) {

            if (availableports.getValue() != null && selectClassBox.getValue() != null) {

                Stage primaryStage = new Stage();
                em.getTransaction().begin();
                ProgramRecords prg = em.find(ProgramRecords.class, 100L);
                prg.setComports(availableports.getValue().toString());
                em.getTransaction().commit();

                selectedClass = selectClassBox.getValue().toString();

                SMS_MultiSend_CustomProgress smsMC = new SMS_MultiSend_CustomProgress();
                smsMC.loadCustomizeLoadProgress(em, primaryStage, commentArea.getText());

            } else {
                BugLog.Error_alert("Please Select a Class or the modems Port", "Class/Port Selection", ops.primaryStage);
            }

        } else if (MainPageController.getNumberForParentStatus() == 1) {

            Stage primaryStage = new Stage();
            em.getTransaction().begin();
            ProgramRecords prg = em.find(ProgramRecords.class, 100L);
            prg.setComports(availableports.getValue().toString());
            em.getTransaction().commit();

            // selectedClass = selectClassBox.getValue().toString();
            SMS_MultiSend_CustomProgress smsMC = new SMS_MultiSend_CustomProgress();
            smsMC.loadCustomizeLoadProgress(em, primaryStage, commentArea.getText());

        }
    }

    @FXML
    void changeNumInitialized(ActionEvent event) {

    }

    @FXML
    void classSelectionForSms(MouseEvent event) {

        getClasses();

    }

    @FXML
    void availablePortsIntance(MouseEvent event) {

        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        availableports.getItems().clear();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();

            switch (port.getPortType()) {
                case CommPortIdentifier.PORT_SERIAL:
                    availableports.getItems().add(port.getName());
                    break;
                default: /// Shouldn't happen

                    break;
            }

        }

    }

    ObservableList<String> allclass = FXCollections.observableArrayList("All Classes");

    public void getClasses() {

        if (!MainPageController.getGetTabsAsClass().isEmpty()) {
            int size = MainPageController.getGetTabsAsClass().size();
            selectClassBox.getItems().clear();
            selectClassBox.getItems().addAll(allclass);
            for (int i = 0; i < size; ++i) {

                selectClassBox.getItems().add(MainPageController.getGetTabsAsClass().get(i));

            }

        } else {

            selectClassBox.getItems().setAll("");
        }
    }

    @FXML
    void promotion_ButtonInitialized(ActionEvent event) {

        Optional<ButtonType> result = Confirmations.studentPro_Demo_Confirmation("Class Promotion", "Are you sure you would like to Promote all available classes to the next level").showAndWait();

        if (!MainPageController.getGetTabsAsClass().isEmpty()) {
            if (result.get() == ButtonType.OK) {

                promote = true;

                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();

            }

        } else {
            BugLog.Error_alert("No Class Found", "", null);
        }
    }

    @FXML
    void undoClassPromotion_ButtonInitialized(ActionEvent event) {

        if (promotionEventPlaced.isPromoEventPlaced() == true) {
            Optional<ButtonType> result = Confirmations.studentPro_Demo_Confirmation("Undo Promotion", "Are you sure you would like to Undo Class Promotion.").showAndWait();

            if (result.get() == ButtonType.OK) {

                undoPromotion = true;

                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();

            }

        } else {
            BugLog.Error_alert("No Recent Class Promotions Found,\n Please restart Program for Recheck", "", null);
        }

    }

    @FXML
    void okButtonInitialize(ActionEvent event) {

        //  System.out.println(passwordPage.isIsEmpty());
        if (passwordPage.isIsEmpty() == true) {

            if (!userId.getText().isEmpty()) {
                if (!passwordField1.getText().isEmpty()) {

                    if (passwordField1.getText().equals(passwordField2.getText())) {
                        PRT prt = new PRT();
                        em.getTransaction().begin();
                        prt.setId("123");
                        prt.setUserName(userId.getText());
                        prt.setPassword(passwordField1.getText());
                        em.persist(prt);
                        em.getTransaction().commit();

                        Stage stage = (Stage) cancelButton.getScene().getWindow();
                        stage.close();
                    } else {
                        passLabel2.setText("Password Mismatched");
                        passLabel2.setOpacity(1.0);
                    }
                    //   System.err.println(passwordField1.getText());
                } else {
                    passLabel1.setText("No Password");
                    passLabel1.setOpacity(1.0);
                }

            } else {

                userIdLabel.setOpacity(1.0);
            }
        } else {

            PRT prtt = em.find(PRT.class, "123");
            if (passwordField1.getText().equals(prtt.getPassword())) {

                if (!passwordField2.getText().isEmpty()) {

                    if (passwordField3.getText().equals(passwordField2.getText())) {

                        em.getTransaction().begin();
                        prtt.setUserName(userId.getText());
                        prtt.setPassword(passwordField2.getText());
                        em.getTransaction().commit();
                        Stage stage = (Stage) cancelButton.getScene().getWindow();
                        stage.close();

                    } else {
                        passLabel3.setText("Password Mismatched");
                        passLabel3.setOpacity(1.0);
                    }
                } else {
                    passLabel2.setOpacity(1.0);

                }

            } else {
                passLabel1.setOpacity(1.0);
            }

        }

    }

    @FXML
    void removePasswordInit(ActionEvent event) {

        PRT prt = em.find(PRT.class, "123");
        String id = prt.getId();
        PRTManager pt = new PRTManager();
        Optional<String> result = Confirmations.passWordRemovalConfirmation().showAndWait();
        if (result.isPresent()) {

            if (result.get().equals(prt.getPassword())) {

                em.getTransaction().begin();
                pt.removePRT(em, id);
                em.getTransaction().commit();
                em.close();
                BugLog.Error_static("Removal successful", "");
                removePassword.setDisable(true);
            } else {
                BugLog.Error_static("Wrong Password", "");
            }
        }

    }

    @FXML
    void promotion_ButtonEntered(MouseEvent event) {

        promoteClassButton.setEffect(new Glow(0.8));

    }

    @FXML
    void promotion_ButtonExited(MouseEvent event) {

        promoteClassButton.setEffect(new Glow(0.0));

    }

    @FXML
    void undoClassPromotion_ButtonEntered(MouseEvent event) {

        undoClassPromotionButton.setEffect(new Glow(0.8));

    }

    @FXML
    void undoClassPromotion_ButtonExited(MouseEvent event) {

        undoClassPromotionButton.setEffect(new Glow(0.0));

    }

    @FXML
    void cancelInitialized(ActionEvent event) {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void closeFeesButtonInitialized(ActionEvent event) {

        Stage stage = (Stage) closeFees.getScene().getWindow();
        stage.close();
    }
}
