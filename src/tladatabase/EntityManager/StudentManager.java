/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tladatabase.Entity.Student;

/**
 *
 * @author Elorm
 */
public class StudentManager {

    public void removeStudent(EntityManager em, String id) {
        Student student = findStudent(em, id);
        if (student != null) {

            em.remove(student);
        }
    }

    public Student findStudent(EntityManager em, String id) {

        return em.find(Student.class, id);
    }

    public List<Student> findAllStudents(EntityManager em) {

        TypedQuery<Student> query = em.createQuery("SELECT e FROM Student e", Student.class);

        return query.getResultList();
    }

}
