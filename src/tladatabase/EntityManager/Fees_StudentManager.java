/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tladatabase.Entity.Fees_Student;

/**
 *
 * @author Elorm
 */
public class Fees_StudentManager {

    public void removeFeesStudent(EntityManager em, String id) {
        Fees_Student fs = findFeesStudent(em, id);
        if (fs != null) {

            em.remove(fs);
        }
    }

    public Fees_Student findFeesStudent(EntityManager em, String id) {

        return em.find(Fees_Student.class, id);
    }

    public List<Fees_Student> findAllFeesStudent(EntityManager em) {

        TypedQuery<Fees_Student> query = em.createQuery("SELECT e FROM Fees_Student e", Fees_Student.class);

        return query.getResultList();
    }

}
