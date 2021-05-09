/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import tladatabase.Controllers.MainPageController;
import tladatabase.Controllers.OptionsController;
import tladatabase.Controllers.TablesController;
import tladatabase.Entity.Student;
import tladatabase.EntityManager.StudentManager;
import tladatabase.Entity.Class;
import tladatabase.EntityManager.ClassManager;

/**
 *
 * @author Elorm
 */
public class FetchNumberForSMS {

    static ObservableList<String> numbersforSorting = FXCollections.observableArrayList();
    static ObservableList<String> numbers = FXCollections.observableArrayList();

    private static ObservableList<String> getPhoneNumbersForSorting(EntityManager em) {

        StudentManager stm = new StudentManager();
        ClassManager cm = new ClassManager();
        numbersforSorting.clear();
        List<Student> student = null;
        List<Student> aspStudent = null;
        ObservableList loadSpecClassStudents = FXCollections.observableArrayList();
        List<Class> theClass = cm.findAllClasses(em);

        if (MainPageController.getNumberForParentStatus() == 0) {
            // this is to sort d/f classes for number sending
            if (!"All Classes".equals(OptionsController.selectedClass)) {

                for (int i = 0; i < theClass.size(); i++) {

                    if (theClass.get(i).getClassName() == null ? OptionsController.selectedClass == null : theClass.get(i).getClassName().equals(OptionsController.selectedClass)) {
                        long classId = theClass.get(i).getId();
                        aspStudent = stm.findAllStudents(em);

                        for (int x = 0; x < aspStudent.size(); x++) {

                            if (aspStudent.get(x).getClassId().equals(classId)) {
                                loadSpecClassStudents.addAll(aspStudent.get(x));

                            }
                        }

                    }
                    student = loadSpecClassStudents;

                }
            } else {
                student = stm.findAllStudents(em);
            }

            SplitNumberForSMS(student);

        } else if (MainPageController.getNumberForParentStatus() == 1) {

            // student.clear();
            SplitNumberForSMS_for_SpecificStudent(TablesController.getGetNumberFromParent());

        }

        return numbersforSorting;

    }

    public static ObservableList<String> getPhoneNumbers(EntityManager em) {

        OptionStage ops = new OptionStage();
        numbers.clear();
        int size = getPhoneNumbersForSorting(em).size();
        for (int i = 0; i < size; i++) {
            if (!"".equals(getPhoneNumbersForSorting(em).get(i))) {
                numbers.addAll(getPhoneNumbersForSorting(em).get(i));
            }
        }

        System.err.println(numbers);
        return numbers;
    }

    //to sort and break all double numbers i.e the father and mother phone number
    private static void SplitNumberForSMS(List<Student> student) {

        for (int i = 0; i < student.size(); i++) {

            if (!student.get(i).getPhoneNo().equals("/")) {

                if (StringSplit.splitDashString(student.get(i).getPhoneNo()).length == 2) {

                    numbersforSorting.addAll(StringSplit.splitDashString(student.get(i).getPhoneNo())[0], StringSplit.splitDashString(student.get(i).getPhoneNo())[1]);

                } else if (StringSplit.splitDashString(student.get(i).getPhoneNo()).length != 2) {

                    numbersforSorting.addAll(StringSplit.splitDashString(student.get(i).getPhoneNo())[0]);

                }
            }

        }

    }

    private static void SplitNumberForSMS_for_SpecificStudent(String student) {

        if (!student.equals("/")) {

            if (StringSplit.splitDashString(student).length == 2) {

                numbersforSorting.addAll(StringSplit.splitDashString(student)[0],StringSplit.splitDashString(student)[1]);
            } else if (StringSplit.splitDashString(student).length != 2) {
                 numbersforSorting.addAll(StringSplit.splitDashString(student)[0]);
            }
        }

    }

}
