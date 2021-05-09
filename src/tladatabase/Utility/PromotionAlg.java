/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import tladatabase.Entity.Class;
import tladatabase.Entity.Fees_Student;
import tladatabase.Entity.ProgramRecords;
import tladatabase.Entity.Student;
import tladatabase.Entity.StudentImage;
import tladatabase.EntityManager.ClassManager;
import tladatabase.EntityManager.Fees_StudentManager;
import tladatabase.EntityManager.StudentManager;

/**
 *
 * @author Elorm
 */
public class PromotionAlg {

    BufferedWriter output = null;

    public void promote(EntityManager em, TableView<Student>[] tableView, TabPane tabPane) {

        ObservableList<Long> getClassSort = FXCollections.observableArrayList();

        StudentManager stm = new StudentManager();

        List<Student> student = stm.findAllStudents(em);
        int classSize = tabPane.getTabs().size();

        for (int x = 0; x < classSize; x++) {

            if (!tableView[x].getItems().isEmpty()) {

                if ((x + 1) != classSize) {

                    for (int y = 0; y < tableView[x].getItems().size(); y++) {

                        em.getTransaction().begin();
                        tableView[x].getItems().get(y).setClassId(fetchClasses(em, getClassSort).get(x + 1));
                        FeesClear.clearOnPromotionAndDemotion(em, tableView[x].getItems().get(y).getId());
                        em.getTransaction().commit();

                        System.out.println(fetchClasses(em, getClassSort).get(x + 1));

                    }
                } else {
                    ProgramRecords pr = em.find(ProgramRecords.class, 100L);

                    Date d = new Date(System.currentTimeMillis());
                    String f = d.toString().replaceAll(" ", "_");
                    String finad = f.replaceAll(":", "_");
                    String path = Paths.programArchive + finad + ".txt";
                    Fees_StudentManager fsm = new Fees_StudentManager();

                    //creates a text file and writes to the file
                    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)))) {
                        em.getTransaction().begin();
                        for (int i = 0; i < tableView[x].getItems().size(); i++) {

                            out.println(tableView[x].getItems().get(i).getId() + "_" + tableView[x].getItems().get(i).getLastName() + "_" + tableView[x].getItems().get(i).getFirstName() + "_"
                                    + tableView[x].getItems().get(i).getOtherName() + "_" + tableView[x].getItems().get(i).getSex() + "_" + tableView[x].getItems().get(i).getClassId() + "_"
                                    + tableView[x].getItems().get(i).getDob() + "_" + tableView[x].getItems().get(i).getFatherName() + "_" + tableView[x].getItems().get(i).getMotherName() + "_"
                                    + tableView[x].getItems().get(i).getPhoneNo() + "_" + tableView[x].getItems().get(i).getCitizenship() + "_" + tableView[x].getItems().get(i).getHomeTown() + "_"
                                    + tableView[x].getItems().get(i).getHouseAdd());

                            Student stud = em.find(Student.class, tableView[x].getItems().get(i).getId());
                            StudentImage sti = em.find(StudentImage.class, tableView[x].getItems().get(i).getId());
                            Fees_Student fs = em.find(Fees_Student.class, tableView[x].getItems().get(i).getId());

                            em.remove(stud);
                            em.remove(sti);
                            if (fs != null) {
                                fsm.removeFeesStudent(em, tableView[x].getItems().get(i).getId());
                                //  System.out.println(studentsInThisClass.get(i).getId());
                            }
                        }
                        pr.setComports(pr.getComports());
                        pr.setPromotedClassName(path);
                        em.getTransaction().commit();
                    } catch (IOException e) {
                        //exception handling left as an exercise for the reader
                    }
                }
            }
        }

        BugLog.Error_alert("Class Promotion Successful \n Please restart program", "", null);

    }

    public ObservableList<Long> fetchClasses(EntityManager em, ObservableList<Long> getClassSort) {

        ClassManager cm = new ClassManager();

        List<Class> classlist = cm.findAllClasses(em);
        getClassSort.clear();
        classlist.stream().forEach((classlist1) -> {
            getClassSort.add(classlist1.getId());
        });

        return getClassSort.sorted();
    }

}
