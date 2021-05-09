/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tladatabase.Entity.StudentImage;

/**
 *
 * @author Elorm
 */
public class ImageManager {

    public void removeImage(EntityManager em, String id) {
        StudentImage studentimage = findStudentImage(em, id);
        if (studentimage != null) {
            em.remove(studentimage);
        }
    }

    public StudentImage findStudentImage(EntityManager em, String id) {

        return em.find(StudentImage.class, id);
    }

    public List<StudentImage> findAllImages(EntityManager em) {

        TypedQuery<StudentImage> query = em.createQuery("SELECT e FROM StudentImage e", StudentImage.class);

        return query.getResultList();
    }

}
