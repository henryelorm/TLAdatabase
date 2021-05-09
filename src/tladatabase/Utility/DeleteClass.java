/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.List;
import javax.persistence.EntityManager;
import tladatabase.Entity.Class;
import tladatabase.Entity.Fees_Class;
import tladatabase.Entity.Fees_Student;
import tladatabase.Entity.Student;
import tladatabase.EntityManager.ClassManager;
import tladatabase.EntityManager.Fees_ClassManager;
import tladatabase.EntityManager.Fees_StudentManager;
import tladatabase.EntityManager.ImageManager;
import tladatabase.EntityManager.StudentManager;

/**
 *
 * @author Elorm
 */
public class DeleteClass {

    public DeleteClass(EntityManager em, long clasId) {

        ClassManager cm = new ClassManager();
        StudentManager sm = new StudentManager();
        Fees_ClassManager fcm = new Fees_ClassManager();
        ImageManager im = new ImageManager();
        Fees_StudentManager fsm = new Fees_StudentManager();

        List<Student> studentsInThisClass = sm.findAllStudents(em);

        if (studentsInThisClass != null) {

            for (int i = 0; i < studentsInThisClass.size(); ++i) {

                if (studentsInThisClass.get(i).getClassId().equals(clasId)) {
                    Fees_Student fs = em.find(Fees_Student.class, studentsInThisClass.get(i).getId());

                    em.getTransaction().begin();
                    im.removeImage(em, studentsInThisClass.get(i).getId());
                    sm.removeStudent(em, studentsInThisClass.get(i).getId());
                    if (fs != null) {
                        fsm.removeFeesStudent(em, studentsInThisClass.get(i).getId());
                      //  System.out.println(studentsInThisClass.get(i).getId());
                    }
                    em.getTransaction().commit();
                }

            }
        }

        em.getTransaction().begin();
        cm.removeClass(em, clasId);
        removeFeesClass(em, fcm, clasId);
        em.getTransaction().commit();

    }

    private void removeFeesClass(EntityManager em, Fees_ClassManager fcm, Long id) {

        List<Fees_Class> ls = fcm.findAllFeesClass(em);
        int size = ls.size();
        if (!ls.isEmpty()) {
            for (int i = 0; i < size; i++) {

                if (ls.get(i).getId().equals(id)) {

                    fcm.removeFeesClass(em, ls.get(i).getId());
                }

            }
        }
    }
}
