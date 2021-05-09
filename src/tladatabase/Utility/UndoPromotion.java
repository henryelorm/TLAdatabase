/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import tladatabase.Entity.ProgramRecords;
import tladatabase.Entity.Student;
import tladatabase.Entity.StudentImage;
import tladatabase.EntityManager.ClassManager;
import tladatabase.EntityManager.StudentManager;

/**
 *
 * @author Elorm
 */
public class UndoPromotion {

    public void promote(EntityManager em, TableView<Student>[] tableView, TabPane tabPane) throws IOException {

        ObservableList<Long> getClassSort = FXCollections.observableArrayList();
        ProgramRecords pr = em.find(ProgramRecords.class, 100L);
        StudentManager stm = new StudentManager();
        PromotionAlg pro = new PromotionAlg();

        List<Student> student = stm.findAllStudents(em);
        int classSize = tabPane.getTabs().size();

        if (!"".equals(pr.getPromotedClassName())) {
            String file_name = pr.getPromotedClassName();

            for (int x = 0; x < classSize; x++) {

                int classAhead = x + 1;
                if (classAhead == classSize) {

                    try {
                        ReadFile file = new ReadFile(file_name);
                        String[] aryLines = file.openFile();

                        // System.out.println(aryLines.length);
                        em.getTransaction().begin();

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
                            em.persist(stud);
                            em.persist(sti);

                        }
                        pr.setComports(pr.getComports());
                        pr.setPromotedClassName("");

                        em.getTransaction().commit();

                    } catch (IOException e) {

                        System.out.println(e);
                    }

                } else {

                    for (int y = 0; y < tableView[classAhead].getItems().size(); y++) {

                        em.getTransaction().begin();
                        tableView[classAhead].getItems().get(y).setClassId(pro.fetchClasses(em, getClassSort).get(x));
                        FeesClear.clearOnPromotionAndDemotion(em, tableView[classAhead].getItems().get(y).getId());
                        em.getTransaction().commit();
                        //  System.out.println(pro.fetchClasses(em, getClassSort).get(x));
                    }
                }
            }
             BugLog.Error_alert("Class Promotion Undone \n Please restart program", "", null);

        }else{
            BugLog.Error_alert("No recent Class Promotions Found", "", null);

        }
       
    }

}
