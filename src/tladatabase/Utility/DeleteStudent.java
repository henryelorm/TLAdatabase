/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javax.persistence.EntityManager;
import tladatabase.Entity.Fees_Student;
import tladatabase.Entity.Student;
import tladatabase.EntityManager.Fees_StudentManager;
import tladatabase.EntityManager.ImageManager;
import tladatabase.EntityManager.StudentManager;

/**
 *
 * @author Elorm
 */
public class DeleteStudent {

    public DeleteStudent(EntityManager em, Student st) {

        StudentManager stm = new StudentManager();
        ImageManager im = new ImageManager();
        Fees_StudentManager fsm = new Fees_StudentManager();
        Fees_Student fs = em.find(Fees_Student.class, st.getId());

        em.getTransaction().begin();
        stm.removeStudent(em, st.getId());
        im.removeImage(em, st.getId());

        if (fs != null) {
            fsm.removeFeesStudent(em, st.getId());
        }
        em.getTransaction().commit();

    }

}
