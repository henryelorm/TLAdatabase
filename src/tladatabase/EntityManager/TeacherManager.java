/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Elorm
 */
public class TeacherManager {

   public void removeTeacher(EntityManager em, Long id) {
        tladatabase.Entity.Teacher cl = findTeacher(em, id);
        if (cl != null) {
            em.remove(cl);
        }
    }

    public tladatabase.Entity.Teacher findTeacher(EntityManager em, Long id) {
        return em.find(tladatabase.Entity.Teacher.class, id);
    }

    public List<tladatabase.Entity.Teacher> findAllTeachers(EntityManager em) {

        TypedQuery<tladatabase.Entity.Teacher> query = em.createQuery("SELECT e FROM Teacher e", tladatabase.Entity.Teacher.class);

        return query.getResultList();
    }

}
