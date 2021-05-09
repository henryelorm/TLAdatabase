/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Elorm
 */
@Entity
@Table(name = "Program_Records")
public class ProgramRecords implements Serializable {

    @Id
    private Long id;

    private String comports;

    private String promotedClassName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComports() {
        return comports;
    }

    public void setComports(String comports) {
        this.comports = comports;
    }

    public String getPromotedClassName() {
        return promotedClassName;
    }

    public void setPromotedClassName(String promotedClassName) {
        this.promotedClassName = promotedClassName;
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
        if (!(object instanceof ProgramRecords)) {
            return false;
        }
        ProgramRecords other = (ProgramRecords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tladatabase.Entity.ProgramRecords[ id=" + id + " ]";
    }

}
