/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Elorm
 */
@Entity
@Table(name = "Students")
public class Student implements Serializable {

    @Id
    private String id;

    private String lastName;

    private String firstName;

    private String otherName;

    private String sex;

    private Long classId;

    private Date dob;

    private String fatherName;

    private String motherName;

    private String phoneNo;

    private String citizenship;

    private String homeTown;

    private String houseAdd;

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String lastName, String firstName, String otherName, String sex, Long classId, Date dob, String fatherName, String motherName, String phoneNo, String citizenship, String homeTown, String houseAdd) {

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.otherName = otherName;
        this.sex = sex;
        this.classId = classId;
        this.dob = dob;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.phoneNo = phoneNo;
        this.citizenship = citizenship;
        this.homeTown = homeTown;
        this.houseAdd = houseAdd;

    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getHouseAdd() {
        return houseAdd;
    }

    public void setHouseAdd(String houseAdd) {
        this.houseAdd = houseAdd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tladatabase.Entity.Student[ id=" + id + " ]";
    }

}
