/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Elorm
 */
@Entity
public class Fees_Class implements Serializable {

    //class id = Fees_Class//
    @Id
    private Long id;

    double classFees;

    public Fees_Class() {
    }

    public Fees_Class(Long id, double classFees) {
        this.id = id;
        this.classFees = classFees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getClassFees() {
        return classFees;
    }

    public void setClassFees(double classFees) {
        this.classFees = classFees;
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
        if (!(object instanceof Fees_Class)) {
            return false;
        }
        Fees_Class other = (Fees_Class) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tladatabase.Entity.Fees_Class[ id=" + id + " ]";
    }

}
