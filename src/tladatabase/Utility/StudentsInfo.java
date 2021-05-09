/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import tladatabase.Entity.Student;
import tladatabase.Entity.Class;

/**
 *
 * @author Elorm
 */
public class StudentsInfo {

    public void StudentsInfo(EntityManager em, String id, Text nameText, Text sexText, Text classText, Text dobText, Text citizenshipText, Text hometownText, Text houseaddressText, Text fatherNameText, Text motherNameText, Text phonenumbersText, ImageView studentImage) throws IOException {

        Student student = em.find(Student.class, id);

        nameText.setText(student.getLastName() + " " + student.getFirstName() + " " + student.getOtherName());
        sexText.setText(student.getSex());

        Class cl = em.find(Class.class, student.getClassId());
        classText.setText(cl.getClassName());
        dobText.setText(student.getDob().toString());
        citizenshipText.setText(student.getCitizenship());
        hometownText.setText(student.getHomeTown());
        houseaddressText.setText(student.getHouseAdd());
        fatherNameText.setText(student.getFatherName());
        motherNameText.setText(student.getMotherName());
        phonenumbersText.setText(student.getPhoneNo());

        ImageBytesConversion.isFromBytestoImage(em, studentImage, id);
    }

}
