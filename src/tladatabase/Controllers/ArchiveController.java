/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tladatabase.Entity.Student;
import tladatabase.Entity.StudentImage;
import tladatabase.Utility.Paths;
import tladatabase.Utility.ReadFile;
import tladatabase.Utility.StringSplit;
import tladatabase.Utility.Table_Archive;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class ArchiveController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn number;

    @FXML
    private TableColumn year;

    @FXML
    private TableColumn<Student, String> lastName;

    @FXML
    private TableColumn<Student, String> firstName;

    @FXML
    private TableColumn<Student, String> otherName;

    @FXML
    private TableColumn<Student, String> ppN;

    ObservableList<Student> studentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Table_Archive.lastName(lastName);
        Table_Archive.firstName(firstName);
        Table_Archive.otherName(otherName);
        Table_Archive.phoneNo(ppN);
        //  Table_Archive.yearColumn(year);
        Table_Archive.numberColumn(number);

        String[] directories = new File(Paths.programArchive).list();

        try {
            //  System.err.println(readFiles(Paths.programArchive + "Wed_Aug_05_16_39_34_BST_2015.txt"));

            if (directories.length != 0) {
                for (String directorie : directories) {
                    tableView.getItems().addAll(readFiles(Paths.programArchive + directorie));
                    studentList.clear();
                }
            }

        } catch (IOException ex) {
            System.err.println("arcive could not open");
        }

    }

    private ObservableList<Student> readFiles(String file_name) throws IOException {
        ReadFile file = new ReadFile(file_name);
        String[] aryLines = file.openFile();

        for (int i = 0; i < aryLines.length; i++) {

            Student stud = new Student();
            StudentImage sti = new StudentImage();

            sti.setId(StringSplit.splitUnderScoreString(aryLines[i])[0]);
            sti.setImage(null);
            stud.setId(StringSplit.splitUnderScoreString(aryLines[i])[0]);
            stud.setLastName(StringSplit.splitUnderScoreString(aryLines[i])[1]);
            stud.setFirstName(StringSplit.splitUnderScoreString(aryLines[i])[2]);
            stud.setOtherName(StringSplit.splitUnderScoreString(aryLines[i])[3]);
            stud.setSex(StringSplit.splitUnderScoreString(aryLines[i])[4]);
            stud.setClassId(Long.valueOf(StringSplit.splitUnderScoreString(aryLines[i])[5]));
            stud.setDob(Date.valueOf(StringSplit.splitUnderScoreString(aryLines[i])[6]));
            stud.setFatherName(StringSplit.splitUnderScoreString(aryLines[i])[7]);
            stud.setMotherName(StringSplit.splitUnderScoreString(aryLines[i])[8]);
            stud.setPhoneNo(StringSplit.splitUnderScoreString(aryLines[i])[9]);
            stud.setCitizenship(StringSplit.splitUnderScoreString(aryLines[i])[10]);
            stud.setHomeTown(StringSplit.splitUnderScoreString(aryLines[i])[11]);
            stud.setHouseAdd("");

            if (StringSplit.splitUnderScoreString(aryLines[i]).length == 13) {
                //  System.out.println(StringSplit.splitUnderScoreString(aryLines[i])[12] + " ");
                stud.setHouseAdd(StringSplit.splitUnderScoreString(aryLines[i])[12]);

            }

            studentList.addAll(stud);
        }

        return studentList;
    }

    @FXML
    void archiveInfoInit(MouseEvent event) throws IOException {

    }
}
