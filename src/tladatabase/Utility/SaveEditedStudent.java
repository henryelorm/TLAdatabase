/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import javax.persistence.EntityManager;
import tladatabase.Entity.Student;
import tladatabase.Entity.StudentImage;

/**
 *
 * @author Elorm
 */
public class SaveEditedStudent {

    public SaveEditedStudent(EntityManager em, Student student, StudentImage stm) throws IOException {

        Student st = em.find(Student.class, student.getId());

        em.getTransaction().begin();
        st.setFirstName(student.getFirstName());
        st.setLastName(student.getLastName());
        st.setOtherName(student.getOtherName());
        st.setSex(student.getSex());
        st.setDob(student.getDob());
        st.setCitizenship(student.getCitizenship());
        st.setHomeTown(student.getHomeTown());
        st.setHouseAdd(student.getHouseAdd());
        st.setFatherName(student.getFatherName());
        st.setMotherName(student.getMotherName());
        st.setPhoneNo(student.getPhoneNo());
        em.persist(st);
        // em.persist(stm);
        em.getTransaction().commit();
        setStudent(student);
    }

    private static Student student = new Student(null);

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student student) {
        SaveEditedStudent.student = student;
    }

}
