/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.appfuse.model.User;

/**
 *
 * @author devxpbox
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CHECK_TYPE")
@Table(name="T_CHECK_ROLL")
public abstract class CheckRoll implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="CHECK_DT")
    private Date checkDate;
    
    
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User checkUser;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public User getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(User checkUser) {
        this.checkUser = checkUser;
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
        if (!(object instanceof CheckRoll)) {
            return false;
        }
        CheckRoll other = (CheckRoll) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aprisma.opensource.timesheet.model.CheckRoll[ id=" + id + " ]";
    }
    
}
